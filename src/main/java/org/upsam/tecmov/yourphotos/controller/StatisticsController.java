package org.upsam.tecmov.yourphotos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.controller.view.statistics.Statistics;
import org.upsam.tecmov.yourphotos.service.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	/**
	 * Servicio de gestión de Stadísticas
	 */
	private StatisticsService statisticsService;

	/**
	 * @param statisticsService
	 */
	@Autowired
	public StatisticsController(StatisticsService statisticsService) {
		super();
		this.statisticsService = statisticsService;
	}

	@RequestMapping("/show")
	public String showStatistics() {
		return "statistics";
	}
	
	@RequestMapping("/show-table")
	public String showStatisticsTable() {
		return "statisticsTable";
	}

	@RequestMapping("/poblaciones-sugerencias")
	@ResponseBody
	public Statistics showStatisticsPoblacionesSugerencias() {
		return statisticsService.getPoblacionesYsugerencias();
	}
	
	@RequestMapping("/geo-estadistica")
	@ResponseBody
	public Statistics showGeoStatistics() {
		return statisticsService.getGeoEstadistica();
	}
	
	@RequestMapping("/table-estadistica")
	@ResponseBody
	public Statistics showTableStatistics() {
		return statisticsService.getTableStatistics();
	}
}
