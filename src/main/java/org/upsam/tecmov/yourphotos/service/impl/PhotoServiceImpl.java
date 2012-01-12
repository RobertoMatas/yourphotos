package org.upsam.tecmov.yourphotos.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.controller.form.PhotoForm;
import org.upsam.tecmov.yourphotos.controller.view.PhotoOfTheWeekView;
import org.upsam.tecmov.yourphotos.controller.view.PhotoView;
import org.upsam.tecmov.yourphotos.domain.photo.Photo;
import org.upsam.tecmov.yourphotos.domain.photo.PhotoRepository;
import org.upsam.tecmov.yourphotos.service.PhotoService;

@Service
@Transactional(readOnly = true)
public class PhotoServiceImpl implements PhotoService {

	protected static Logger logger = Logger.getLogger(PhotoServiceImpl.class);

	/**
	 * Repositorio de fotos
	 */
	private PhotoRepository photoRepository;

	/**
	 * @param photoRepository
	 *            the photoRepository to set
	 */
	@Autowired
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}

	@Override
	@Transactional
	public boolean saveImage(PhotoForm form) throws IOException {
		byte[] bytes = null;
		boolean insert = false;
		if (form != null && !form.getPhoto().isEmpty()) {
			bytes = form.getPhoto().getBytes();
			Photo photo = new Photo();
			photo.setComment(form.getComment());
			photo.setPhoto(bytes);
			photo.setDate(new Date());
			photo.setContentType(form.getPhoto().getContentType());
			photoRepository.save(photo);
			insert = true;

		}
		return insert;
	}

	@Override
	public Page<PhotoView> listAll(Pageable pageable) {
		Page<Photo> page = photoRepository.findAll(pageable);
		List<PhotoView> view = new ArrayList<PhotoView>();
		PhotoView pview = null;
		for (Photo photo : page.getContent()) {
			pview = new PhotoView();
			pview.setId(photo.getId());
			pview.setComment(photo.getComment());
			pview.setDate(photo.getDate());
			pview.setContentType(photo.getContentType());
			view.add(pview);
		}
		return new PageImpl<PhotoView>(view, pageable, page.getTotalElements());
	}

	@Override
	public PhotoView getImage(Long id) {
		Photo photo = photoRepository.findOne(id);
		PhotoView pview = new PhotoView();
		pview.setPhoto(new ByteArrayInputStream(photo.getPhoto()));
		pview.setContentType(photo.getContentType());
		return pview;
	}

	@Override
	public PhotoOfTheWeekView getPhotoOfTheWeek() {
		Iterable<Photo> it = photoRepository.findAll(new PageRequest(0, 1, new Sort(Direction.DESC, "date")));
		return it != null ? toPhotoOfTheWeekView(it.iterator().next()) : null;
	}

	private PhotoOfTheWeekView toPhotoOfTheWeekView(Photo photo) {
		String relativeUrl = "/photos/image?id=" + photo.getId();
		PhotoOfTheWeekView view = new PhotoOfTheWeekView(relativeUrl, photo.getComment());
		logger.debug(view);
		return view;
	}

}
