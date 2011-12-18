package org.upsam.tecmov.yourphotos.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.suggestion.PhotoType;
import org.upsam.tecmov.yourphotos.domain.suggestion.Suggestion;
import org.upsam.tecmov.yourphotos.domain.suggestion.SuggestionRepository;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;
import org.upsam.tecmov.yourphotos.service.LocationComponents;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.service.SuggestionService;
import org.upsam.tecmov.yourphotos.service.ex.ServiceException;

import uk.me.jstott.coordconv.LatitudeLongitude;
import uk.me.jstott.sun.Sun;
import uk.me.jstott.sun.Time;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	private static final TimeZone TIMEZONE_DEFAULT = TimeZone.getDefault();
	protected static Logger logger = Logger.getLogger(SuggestionServiceImpl.class);
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
	public SuggestionServiceImpl(SuggestionRepository suggestionRepository, PoblacionService poblacionService, GoogleRestClient googleRestClient) {
		super();
		this.suggestionRepository = suggestionRepository;
		this.poblacionService = poblacionService;
		this.googleRestClient = googleRestClient;
	}

	@Override
	@Transactional
	public void registerSuggestion(SuggestionForm form) throws ServiceException {
		LocationComponents lc = googleRestClient.getLocationComponents(form);
		Poblacion poblacion = poblacionService.findByZipCodeAndPoblacion(lc.zipCode, lc.locality);
		if (poblacion == null) {
			throw new ServiceException("No es posible registrar la sugerencia. No existe población para " + lc);
		}
		Suggestion suggestion = new Suggestion();
		Calendar now = Calendar.getInstance();
		suggestion.setDate(now.getTime());
		suggestion.setLatitude(form.getLat());
		suggestion.setLongitude(form.getLng());
		suggestion.setPhotoPlace(form.getPhotoPlace());
		suggestion.setPhotoType(getPhotoType(form));
		suggestion.setPoblacion(poblacion);
		poblacion.addSuggestion(suggestion);
		logger.debug("Población encontrada: " + poblacion);
		suggestionRepository.save(suggestion);
	}

	private PhotoType getPhotoType(SuggestionForm form) {
		PhotoType type = PhotoType.OTRA;
		Calendar now = Calendar.getInstance();
		logger.debug("TimeZone default is: " + TIMEZONE_DEFAULT);
		LatitudeLongitude latlng = new LatitudeLongitude(Double.parseDouble(form.getLat()), Double.parseDouble(form.getLng()));
		Time[] hoursOfEvents = { Sun.sunriseTime(now, latlng, TIMEZONE_DEFAULT, true), Sun.sunsetTime(now, latlng, TIMEZONE_DEFAULT, true),
				Sun.eveningCivilTwilightTime(now, latlng, TIMEZONE_DEFAULT, true) };
		logger.debug(Arrays.asList(hoursOfEvents));
		Calendar calInf = null;
		Calendar calSup = null;
		for (int i = 0; i < hoursOfEvents.length; i++) {
			calInf = toDatePlusMinutes(hoursOfEvents[i], -30);
			calSup = toDatePlusMinutes(hoursOfEvents[i], 30);
			logger.debug("now:" + now.getTime() + ": > calInf:" + calInf.getTime() + ": && < calSup:" + calSup.getTime() + ":");
			if (now.after(calInf) && now.before(calSup)) {
				logger.debug("PhotoType calculado -> " + PhotoType.values()[i]);
				return PhotoType.values()[i];
			}
		}
		return type;
	}

	private Calendar toDatePlusMinutes(Time amanecer, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, (int) Math.round(amanecer.getSeconds()));
		cal.set(Calendar.MINUTE, amanecer.getMinutes() + minutes);
		cal.set(Calendar.HOUR_OF_DAY, amanecer.getHours());
		return cal;
	}

}
