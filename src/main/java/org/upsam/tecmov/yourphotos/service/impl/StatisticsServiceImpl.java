package org.upsam.tecmov.yourphotos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.view.statistics.Row;
import org.upsam.tecmov.yourphotos.controller.view.statistics.Statistics;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.poblacion.PoblacionRepository;
import org.upsam.tecmov.yourphotos.domain.poblacion.Provincia;
import org.upsam.tecmov.yourphotos.service.StatisticsService;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

	private PoblacionRepository poblacionRepository;

	/**
	 * @param poblacionRepository
	 */
	@Autowired
	public StatisticsServiceImpl(PoblacionRepository poblacionRepository) {
		super();
		this.poblacionRepository = poblacionRepository;
	}

	@Override
	public Statistics getPoblacionesYsugerencias() {
		List<Poblacion> poblaciones = poblacionRepository
				.findByNumSuggestionsGreaterThanAndCategoryGreaterThanOrderByLastSuggestionDesc(0,
						-1);
		Statistics statistics = new Statistics();
		statistics.addColumn("Población");
		statistics.addColumn("Nº de Sugerencias");
		if (poblaciones != null) {
			for (Poblacion poblacion : poblaciones) {
				statistics.addRow(toRow(poblacion));
			}
		}
		return statistics;
	}

	private Row toRow(Poblacion poblacion) {
		Row row = null;
		if (poblacion != null) {
			row = new Row(poblacion.getPoblacion(), poblacion.getNumSuggestions());
		}
		return row;
	}

	@Override
	public Statistics getGeoEstadistica() {
		List<Poblacion> poblaciones = poblacionRepository
				.findByNumSuggestionsGreaterThanAndCategoryGreaterThanOrderByLastSuggestionDesc(0,
						-1);
		Map<Provincia, Integer> provincias = new HashMap<Provincia, Integer>();
		Provincia provincia = null;
		Integer numSuggestions = 0;
		Statistics statistics = new Statistics();
		statistics.addColumn("Provincia");
		statistics.addColumn("Nº de Sugerencias");
		if (poblaciones != null) {
			for (Poblacion poblacion : poblaciones) {
				provincia = poblacion.getProvincia();
				if (!provincias.containsKey(provincia)) {
					provincias.put(provincia, 0);
				}
				numSuggestions = provincias.get(provincia);
				numSuggestions += poblacion.getNumSuggestions();
				provincias.put(provincia, numSuggestions);
			}
		}
		Set<Provincia> setProvincias = provincias.keySet();
		for (Provincia p : setProvincias) {
			statistics.addRow(toRow(p, provincias.get(p)));
		}
		return statistics;
	}

	private Row toRow(Provincia provincia, Integer numSuggestions) {
		Row row = null;
		if (provincia != null) {
			row = new Row(provincia.getNombre(), numSuggestions);
		}
		return row;
	}

	@Override
	public Statistics getTableStatistics() {
		List<Poblacion> poblaciones = poblacionRepository
				.findByNumSuggestionsGreaterThanAndCategoryGreaterThanOrderByLastSuggestionDesc(0,
						-1);
		Statistics statistics = new Statistics();
		statistics.addColumn("Población");
		statistics.addColumn("Nº de Sugerencias");
		statistics.addColumn("Categoría");
		statistics.addColumn("Provincia");
		statistics.addColumn("Comunidad");
		for (Poblacion poblacion : poblaciones) {
			statistics.addRow(new Row(poblacion.getPoblacion(), poblacion.getNumSuggestions(),
					poblacion.getCategory(), poblacion.getProvincia().getNombre(), poblacion
							.getProvincia().getComunidad().getNombre()));
		}
		return statistics;
	}

}
