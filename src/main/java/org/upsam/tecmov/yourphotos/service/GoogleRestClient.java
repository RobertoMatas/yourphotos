package org.upsam.tecmov.yourphotos.service;

import org.upsam.tecmov.yourphotos.controller.form.LocationForm;

public interface GoogleRestClient {

	LocationComponents getLocationComponents(LocationForm coordenadas);
}
