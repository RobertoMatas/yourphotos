package org.upsam.tecmov.yourphotos.service;

import org.upsam.tecmov.yourphotos.controller.view.statistics.Statistics;

public interface StatisticsService {
	
	Statistics getPoblacionesYsugerencias();

	Statistics getGeoEstadistica();
	
	Statistics getTableStatistics();
}
