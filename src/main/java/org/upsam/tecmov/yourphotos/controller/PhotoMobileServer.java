package org.upsam.tecmov.yourphotos.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.controller.form.ManualSuggestionForm;
import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.controller.view.CenterView;
import org.upsam.tecmov.yourphotos.controller.view.InfoOrder;
import org.upsam.tecmov.yourphotos.controller.view.MapsView;
import org.upsam.tecmov.yourphotos.controller.view.PhotoOfTheWeekView;
import org.upsam.tecmov.yourphotos.controller.view.PoblacionWithDetailsView;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;
import org.upsam.tecmov.yourphotos.service.PhotoService;
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
	 * Servicio de gestión de las fotos
	 */
	private PhotoService photoService;

	/**
	 * @param client
	 */
	@Autowired
	public PhotoMobileServer(GoogleRestClient client, PoblacionService poblacionService, SuggestionService suggestionService,
			PhotoService photoService, Validator validator) {
		super();
		this.client = client;
		this.poblacionService = poblacionService;
		this.suggestionService = suggestionService;
		this.photoService = photoService;
		this.validator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/iamhere")
	@ResponseBody
	public Poblacion photoTaken(LocationForm form) {
		LocationComponents lc = client.getLocationComponents(form);
		return poblacionService.findByZipCodeAndPoblacion(lc.zipCode, lc.locality);
	}

	@RequestMapping("/suggest")
	public void suggest(SuggestionForm form, BindingResult result, HttpServletResponse response) {
		registerSuggestion(form, result, response);
	}

	@RequestMapping("/suggest/manual")
	public void manualSuggest(ManualSuggestionForm form, BindingResult result, HttpServletResponse response) {
		registerSuggestion(form, result, response);
	}

	private void registerSuggestion(SuggestionForm form, BindingResult result, HttpServletResponse response) {
		validator.validate(form, result);
		if (!result.hasErrors()) {
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

	@RequestMapping("/maps/static")
	public void getMapStaticWithPoblaciones(LocationForm form, HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		Page<PoblacionWithDetailsView> page = poblacionService.findByPoblacionWithSuggestions(form, null, null, InfoOrder.categoria);
		InputStream is = client.getMap(form, page.getContent());
		if (is != null) {
			FileCopyUtils.copy(is, response.getOutputStream());
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/maps")
	@ResponseBody
	public MapsView getMapWithPoblaciones(LocationForm form, Model model) {
		Page<PoblacionWithDetailsView> page = poblacionService.findByPoblacionWithSuggestions(form, null, null, InfoOrder.categoria);
		return new MapsView(new CenterView(form.getLat(), form.getLng()), page.getContent());
	}

	@RequestMapping("/maps/view")
	public String getMapWithPoblacionesView(LocationForm form, Model model) {
		model.addAttribute("centerLatitude", form.getLat());
		model.addAttribute("centerLongitude", form.getLng());
		return "map";
	}

	@RequestMapping("/photo-of-the-week")
	@ResponseBody
	public PhotoOfTheWeekView getPhotoOfTheWeek() {
		return photoService.getPhotoOfTheWeek();
	}
}
