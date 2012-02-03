package org.upsam.tecmov.yourphotos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.view.statistics.Row;
import org.upsam.tecmov.yourphotos.controller.view.statistics.Statistics;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.poblacion.PoblacionRepository;
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
		List<Poblacion> poblaciones = poblacionRepository.findByNumSuggestionsGreaterThanAndCategoryGreaterThanOrderByLastSuggestionDesc(0, -1);
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

}
