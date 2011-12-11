package org.upsam.tecmov.yourphotos.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.upsam.tecmov.yourphotos.controller.form.PhotoForm;
import org.upsam.tecmov.yourphotos.controller.view.PhotoView;

public interface PhotoService {

	boolean saveImage(PhotoForm form) throws IOException;
	
	Page<PhotoView> listAll(Pageable pageable);

	PhotoView getImage(Long id);
}
