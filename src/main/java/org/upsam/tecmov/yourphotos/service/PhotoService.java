package org.upsam.tecmov.yourphotos.service;

import java.io.IOException;
import java.util.List;

import org.upsam.tecmov.yourphotos.controller.form.PhotoForm;
import org.upsam.tecmov.yourphotos.controller.view.PhotoView;

public interface PhotoService {

	boolean saveImage(PhotoForm form) throws IOException;
	
	List<PhotoView> listAll();

	PhotoView getImage(Long id);
}
