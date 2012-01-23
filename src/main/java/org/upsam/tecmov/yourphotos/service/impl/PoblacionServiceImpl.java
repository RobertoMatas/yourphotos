package org.upsam.tecmov.yourphotos.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.view.DetailsView;
import org.upsam.tecmov.yourphotos.controller.view.InfoOrder;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;
import org.upsam.tecmov.yourphotos.controller.view.comparator.CategoriaComparator;
import org.upsam.tecmov.yourphotos.controller.view.comparator.DistanciaComparator;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.poblacion.PoblacionRepository;
import org.upsam.tecmov.yourphotos.domain.suggestion.Suggestion;
import org.upsam.tecmov.yourphotos.domain.suggestion.SuggestionRepository;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.utils.DefaultNormalizer;

@Service
@Transactional(readOnly = true)
public class PoblacionServiceImpl implements PoblacionService {

	private static final CategoriaComparator CAT_COMPARATOR = new CategoriaComparator();

	private static final Sort SORT_DEFAULT = new Sort("provincia.nombreSeo", "poblacionSeo");

	private static final Comparator<? super PoblacionWithDetailsView> DIST_COMPARATOR = new DistanciaComparator();

	protected static Logger logger = Logger.getLogger(PoblacionServiceImpl.class);

	/**
	 * 
	 */
	private PoblacionRepository poblacionRepository;
	/**
	 * 
	 */
	private SuggestionRepository suggestionRepository;
	/**
	 * 
	 */
	private GoogleRestClient googleRestClient;

	/**
	 * 
	 * @param poblacionRepository
	 * @param suggestionRepository
	 */
	@Autowired
	public PoblacionServiceImpl(PoblacionRepository poblacionRepository,
			SuggestionRepository suggestionRepository, GoogleRestClient googleRestClient) {
		this.poblacionRepository = poblacionRepository;
		this.suggestionRepository = suggestionRepository;
		this.googleRestClient = googleRestClient;
	}

	@Override
	public Page<Poblacion> findByPoblacionSeo(String nameLike, Integer page, Integer size) {
		return poblacionRepository.findByPoblacionSeoLike(
				"%" + DefaultNormalizer.normalize(nameLike) + "%", new PageRequest(page, size,
						SORT_DEFAULT));
	}

	@Override
	public Page<Poblacion> findAll(Integer page, Integer size) {
		return poblacionRepository.findAll(new PageRequest(page, size, SORT_DEFAULT));
	}

	@Override
	public Poblacion findByZipCodeAndPoblacion(String zipCode, String poblacion) {
		logger.debug(" -- findByZipCodeAndPoblacion(" + zipCode + ", " + poblacion + ")");
		// primero buscamos por código postal. Si sólo obtenemos un resultado lo
		// devolvemos
		List<Poblacion> poblaciones1 = poblacionRepository.findByCodPostal(zipCode);
		if (poblaciones1 != null && poblaciones1.size() == 1) {
			return poblaciones1.get(0);
		}

		// si hay varios resultados intentamos una búsqueda añadiendo el filtro
		// por nombre de población
		List<Poblacion> poblaciones2 = poblacionRepository.findByCodPostalAndPoblacionSeoLike(
				zipCode, "%" + DefaultNormalizer.normalize(poblacion) + "%");
		if (poblaciones2 != null && !poblaciones2.isEmpty()) {
			if (poblaciones2.size() > 1) {
				logger.warn("Existen varias poblaciones -> " + poblaciones2);
			}
			return poblaciones2.get(0);
		}

		// si no obtenemos resultados intentamos devolver alguno de los
		// devueltos en la primera búsqueda
		return poblaciones1 != null && !poblaciones1.isEmpty() ? poblaciones1.get(0) : null;
	}

	@Override
	public Page<PoblacionWithDetailsView> findByPoblacionWithSuggestions(LocationForm coordenadas,
			Integer page, Integer size, InfoOrder order) {
		List<Poblacion> poblaciones = null;
		PageRequest pageable = null;
		if (order.equals(InfoOrder.tiempo)) {
			poblaciones = poblacionRepository.findByNumSuggestionsGreaterThanAndCategoryGreaterThanOrderByLastSuggestionDesc(0, coordenadas.getCategory());
		} else {
			poblaciones = poblacionRepository.findByNumSuggestionsGreaterThanAndCategoryGreaterThan(0, coordenadas.getCategory());
		}
		List<PoblacionWithDetailsView> view = toView(coordenadas, poblaciones);
		reorder(order, view);
		int fromIndex = page != null && size != null ? page * size : 0;
		int toIndex = (page == null && size == null) || (fromIndex + size) > view.size() ? view.size() : fromIndex + size;
		pageable = (page == null && size == null) ? null : new PageRequest(page, size);
		return new PageImpl<PoblacionWithDetailsView>(view.subList(fromIndex, toIndex), pageable, view.size());
		
	}

	private List<PoblacionWithDetailsView> toView(LocationForm coordenadas,
			List<Poblacion> listPoblaciones) {
		List<PoblacionWithDetailsView> view = new ArrayList<PoblacionWithDetailsView>(
				listPoblaciones.size());
		Integer distancia = coordenadas.getDistancia() != null ? coordenadas.getDistancia() : 0;
		PoblacionWithDetailsView viewItem = null;
		for (Poblacion poblacion : listPoblaciones) {
			viewItem = toView(coordenadas, poblacion);
			// si aplica el filtro por distancia, descartamos las poblaciones que no cumplan dicho filtro
			if (distancia != 0 && viewItem.getDistancia() < distancia) {
				view.add(viewItem);
			} else if (distancia == 0) {
				view.add(viewItem);
			}
		}
		return view;
	}

	private void reorder(InfoOrder order, List<PoblacionWithDetailsView> view) {
		if (!order.equals(InfoOrder.tiempo)) {
			switch (order) {
			case categoria:
				Collections.sort(view, Collections.reverseOrder(CAT_COMPARATOR));
				break;
			case distancia:
				Collections.sort(view, DIST_COMPARATOR);
				break;

			default:
				break;
			}
		}
	}

	private Suggestion getSuggestion(Poblacion poblacion, boolean first) {
		Direction direction = Direction.DESC;
		if (first) {
			direction = Direction.ASC;
		}
		Page<Suggestion> suggestions = suggestionRepository.findByPoblacion(poblacion,
				new PageRequest(0, 1, new Sort(direction, "date")));
		return suggestions.getContent().get(0);
	}

	private PoblacionWithDetailsView toView(LocationForm coordenadas, Poblacion poblacion) {
		Suggestion first = getSuggestion(poblacion, true);
		Suggestion last = getSuggestion(poblacion, false);
		Integer distance = googleRestClient.getDistance(coordenadas,
				new LocationForm(poblacion.getLatitud(), poblacion.getLongitud()));
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return new PoblacionWithDetailsView(poblacion.getPoblacion(), poblacion.getProvincia()
				.getNombre(), distance, poblacion.getCategory(), poblacion.getLatitud(),
				poblacion.getLongitud(), new DetailsView(poblacion.getNumSuggestions(),
						df.format(first.getDate()), df.format(last.getDate()), first.getPhotoType()
								.toString(), last.getPhotoType().toString()));
	}
}
