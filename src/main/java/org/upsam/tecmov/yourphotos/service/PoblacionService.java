package org.upsam.tecmov.yourphotos.service;

import org.springframework.data.domain.Page;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.view.InfoOrder;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;

public interface PoblacionService {

	Page<Poblacion> findByPoblacionSeo(String nameLike, Integer page, Integer size);

	Page<Poblacion> findAll(Integer page, Integer size);
	
	Poblacion findByZipCodeAndPoblacion(String zipCode, String poblacion);
	
	Page<PoblacionWithDetailsView> findByPoblacionWithSuggestions(LocationForm coordenadas, Integer page, Integer size, InfoOrder order);
}
