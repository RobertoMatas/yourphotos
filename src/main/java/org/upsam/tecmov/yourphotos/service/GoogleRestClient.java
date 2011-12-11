package org.upsam.tecmov.yourphotos.service;

import org.upsam.tecmov.yourphotos.controller.form.LocationForm;

public interface GoogleRestClient {

	String getPostalCode(LocationForm coordenadas);
}
