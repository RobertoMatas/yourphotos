package org.upsam.tecmov.yourphotos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.service.PoblacionService;

@Controller
public class PoblacionController {

	/**
	 * 
	 */
	private static final String PAGE_SIZE = "10";
	/**
	 * Validador JSR-303
	 */
	private Validator validator;
	/**
	 * Servicio de poblaciones
	 */
	private PoblacionService poblacionService;

	@Autowired
	public PoblacionController(PoblacionService poblacionService, Validator validator) {
		this.poblacionService = poblacionService;
		this.validator = validator;
	}

	@RequestMapping(value = "/poblaciones", method = RequestMethod.GET)
	@ResponseBody
	public Page<PoblacionWithDetailsView> getPoblaciones(LocationForm form, BindingResult result, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size) {
		validator.validate(form, result);
		if (!result.hasErrors()) {
			return poblacionService.findByPoblacionWithSuggestions(form, page, size);
		}
		return null;
	}

	@RequestMapping(value = "/poblaciones/buscador", method = RequestMethod.GET)
	@ResponseBody
	public Page<Poblacion> getPoblacionesLike(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
			@RequestParam String nameLike) {
		return poblacionService.findByPoblacionSeo(nameLike, page, size);
	}
}
