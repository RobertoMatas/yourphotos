package org.upsam.tecmov.yourphotos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.service.PoblacionService;

@Controller
public class PoblacionController {
	/**
	 * 
	 */
	private static final String PAGE_SIZE = "10";
	/**
	 * Servicio de poblaciones
	 */
	private PoblacionService poblacionService;
	
	@Autowired
	public PoblacionController(PoblacionService poblacionService) {
		this.poblacionService = poblacionService;
	}
	
	@RequestMapping(value = "/poblaciones", method = RequestMethod.GET)
	@ResponseBody
	public Page<Poblacion> getPoblaciones(
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size) {
		return poblacionService.findAll(page, size);
	}
	
	@RequestMapping(value = "/poblaciones/buscador", method = RequestMethod.GET)
	@ResponseBody
	public Page<Poblacion> getPoblacionesLike(
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
			@RequestParam String nameLike) {
		return poblacionService.findByPoblacionSeo(nameLike, page, size);
	}
}
