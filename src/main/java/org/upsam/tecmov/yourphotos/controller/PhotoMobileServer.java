package org.upsam.tecmov.yourphotos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.service.SuggestionService;

@Controller
public class PhotoMobileServer {
	/**
	 * Cliente Rest para acceso a los servicios de geolocalización de google
	 */
	private GoogleRestClient client;
	/**
	 * Servicio para gestión de poblaciones
	 */
	private PoblacionService poblacionService;
	/**
	 * Servicio para gestionar las sugerencias de los usuarios
	 */
	private SuggestionService suggestionService;

	/**
	 * @param client
	 */
	@Autowired
	public PhotoMobileServer(GoogleRestClient client, PoblacionService poblacionService, SuggestionService suggestionService) {
		super();
		this.client = client;
		this.poblacionService = poblacionService;
		this.suggestionService = suggestionService;
	}

	@RequestMapping("/iamhere")
	@ResponseBody
	public Poblacion photoTaken(LocationForm form) {
		LocationComponents lc = client.getLocationComponents(form);
		return poblacionService.findByZipCodeAndPoblacion(lc.zipCode, lc.locality);
	}
	
	@RequestMapping("/suggest")
	public void suggest(@Valid SuggestionForm form, BindingResult result) {
		if (! result.hasErrors()) {
			suggestionService.registerSuggestion(form);
		}
	}
}
