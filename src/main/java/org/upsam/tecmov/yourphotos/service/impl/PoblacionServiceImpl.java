package org.upsam.tecmov.yourphotos.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.poblacion.PoblacionRepository;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.utils.DefaultNormalizer;

@Service
@Transactional(readOnly = true)
public class PoblacionServiceImpl implements PoblacionService {
	
	protected static Logger logger = Logger.getLogger(PoblacionServiceImpl.class); 
	
	/**
	 * 
	 */
	private PoblacionRepository poblacionRepository;

	@Autowired
	public PoblacionServiceImpl(PoblacionRepository poblacionRepository) {
		this.poblacionRepository = poblacionRepository;
	}

	@Override
	public Page<Poblacion> findByPoblacionSeo(String nameLike, Integer page,
			Integer size) {
		return poblacionRepository.findByPoblacionSeoLike("%"
				+ DefaultNormalizer.normalize(nameLike) + "%", new PageRequest(
				page, size, new Sort("provincia.nombreSeo", "poblacionSeo")));
	}

	@Override
	public Page<Poblacion> findAll(Integer page, Integer size) {
		return poblacionRepository.findAll(new PageRequest(page, size,
				new Sort("provincia.nombreSeo", "poblacionSeo")));
	}

	@Override
	public Poblacion findByZipCodeAndPoblacion(String zipCode, String poblacion) {
		logger.debug(" -- findByZipCodeAndPoblacion(" + zipCode + ", " + poblacion + ")");
		// primero buscamos por código postal. Si sólo obtenemos un resultado lo devolvemos
		List<Poblacion> poblaciones1 = poblacionRepository.findByCodPostal(zipCode);
		if (poblaciones1 != null && poblaciones1.size() == 1) {
			return poblaciones1.get(0);
		}
		
		// si hay varios resultados intentamos una búsqueda añadiendo el filtro por nombre de población
		List<Poblacion> poblaciones2 = poblacionRepository
				.findByCodPostalAndPoblacionSeoLike(zipCode, "%"
						+ DefaultNormalizer.normalize(poblacion) + "%");
		if (poblaciones2 != null && ! poblaciones2.isEmpty()) {
			if (poblaciones2.size() > 1) {
				logger.warn("Existen varias poblaciones -> " + poblaciones2);				
			}
			return poblaciones2.get(0);
		}
		
		// si no obtenemos resultados intentamos devolver alguno de los devueltos en la primera búsqueda
		return poblaciones1 != null && !poblaciones1.isEmpty() ? poblaciones1.get(0) : null;
	}
}
