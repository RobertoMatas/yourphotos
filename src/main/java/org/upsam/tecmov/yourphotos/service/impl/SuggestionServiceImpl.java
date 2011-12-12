package org.upsam.tecmov.yourphotos.service.impl;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.suggestion.Suggestion;
import org.upsam.tecmov.yourphotos.domain.suggestion.SuggestionRepository;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.service.SuggestionService;

import uk.me.jstott.coordconv.LatitudeLongitude;
import uk.me.jstott.sun.Sun;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	/**
	 * 
	 */
	private SuggestionRepository suggestionRepository;
	/**
	 * 
	 */
	private PoblacionService poblacionService;
	/**
	 * 
	 */
	private GoogleRestClient googleRestClient;

	/**
	 * @param suggestionRepository
	 * @param poblacionService
	 * @param googleRestClient
	 */
	@Autowired
	public SuggestionServiceImpl(SuggestionRepository suggestionRepository,
			PoblacionService poblacionService, GoogleRestClient googleRestClient) {
		super();
		this.suggestionRepository = suggestionRepository;
		this.poblacionService = poblacionService;
		this.googleRestClient = googleRestClient;
	}

	@Override
	@Transactional
	public void registerSuggestion(SuggestionForm form) {
		LocationComponents lc = googleRestClient.getLocationComponents(form);
		Poblacion poblacion = poblacionService.findByZipCodeAndPoblacion(lc.zipCode, lc.locality);
		Suggestion suggestion = new Suggestion();
		Calendar now = Calendar.getInstance();
		suggestion.setDate(now.getTime());
		suggestion.setLatitude(form.getLat());
		suggestion.setLongitude(form.getLng());
		suggestion.setPhotoPlace(form.getPhotoPlace());
		// TODO: calcular el tipo (amanecer/diurna, etc) mediante la clase:
		// Sun.sunriseTime(cal, ll, timeZone, dst)
		System.out.println("Amanecer: " + Sun.sunriseTime(now, new LatitudeLongitude(Double.parseDouble(form.getLat()), Double.parseDouble(form.getLng())), TimeZone.getDefault(), true));
		System.out.println("Atardecer: " + Sun.sunsetTime(now, new LatitudeLongitude(Double.parseDouble(form.getLat()), Double.parseDouble(form.getLng())), TimeZone.getDefault(), true));
		System.out.println("Anochecer: " + Sun.eveningCivilTwilightTime(now, new LatitudeLongitude(Double.parseDouble(form.getLat()), Double.parseDouble(form.getLng())), TimeZone.getDefault(), true));
		suggestion.setPhotoType(null); // TODO
		suggestion.setPoblacion(poblacion);
		suggestionRepository.save(suggestion);
	}

}
