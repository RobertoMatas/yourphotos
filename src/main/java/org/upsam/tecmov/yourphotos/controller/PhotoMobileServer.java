package org.upsam.tecmov.yourphotos.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.service.SuggestionService;
import org.upsam.tecmov.yourphotos.service.ex.ServiceException;

@Controller
public class PhotoMobileServer {
	protected static Logger logger = Logger.getLogger(PhotoMobileServer.class);
	/**
	 * Validador JSR-303
	 */
	private Validator validator;	
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
	public PhotoMobileServer(GoogleRestClient client, PoblacionService poblacionService, SuggestionService suggestionService, Validator validator) {
		super();
		this.client = client;
		this.poblacionService = poblacionService;
		this.suggestionService = suggestionService;
		this.validator = validator;
	}

	@RequestMapping("/iamhere")
	@ResponseBody
	public Poblacion photoTaken(LocationForm form) {
		LocationComponents lc = client.getLocationComponents(form);
		return poblacionService.findByZipCodeAndPoblacion(lc.zipCode, lc.locality);
	}
	
	@RequestMapping("/suggest")
	public void suggest(SuggestionForm form, BindingResult result, HttpServletResponse response) {
		validator.validate(form, result);
		if (! result.hasErrors()) {
			try {
				suggestionService.registerSuggestion(form);
				response.setStatus(HttpServletResponse.SC_OK);
				
			} catch (ServiceException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				logger.warn("No se pudo insetar la sugerencia", e);
			}
			
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
