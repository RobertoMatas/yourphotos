package org.upsam.tecmov.yourphotos.service;

import org.upsam.tecmov.yourphotos.controller.form.SuggestionForm;
import org.upsam.tecmov.yourphotos.service.ex.ServiceException;

public interface SuggestionService {

	void registerSuggestion(SuggestionForm form) throws ServiceException;
}
