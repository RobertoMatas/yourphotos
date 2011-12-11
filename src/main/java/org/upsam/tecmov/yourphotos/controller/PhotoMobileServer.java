package org.upsam.tecmov.yourphotos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.upsam.tecmov.yourphotos.controller.form.LocationForm;
import org.upsam.tecmov.yourphotos.service.GoogleRestClient;

@Controller
public class PhotoMobileServer {
	/**
	 * Cliente Rest para acceso a los servicios de geolocalización de google
	 */
	private GoogleRestClient client;

	/**
	 * @param client
	 */
	@Autowired
	public PhotoMobileServer(GoogleRestClient client) {
		super();
		this.client = client;
	}

	@RequestMapping("/iamhere")
	public void photoTaken(LocationForm form) {
		System.out.println(client.getPostalCode(form));
	}
}
