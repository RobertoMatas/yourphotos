package org.upsam.tecmov.yourphotos.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.upsam.tecmov.yourphotos.controller.form.PhotoForm;
import org.upsam.tecmov.yourphotos.controller.view.PhotoView;
import org.upsam.tecmov.yourphotos.service.PhotoService;

@Controller
@RequestMapping("/photos")
@SessionAttributes("photo")
public class PhotoController {
	/**
	 * 
	 */
	private static final String UPLOAD_FORM_VIEW = "uploadForm";
	/**
	 * 
	 */
	private final Integer PAGE_SIZE = 2;

	/**
	 * Servicio de fotos
	 */
	private PhotoService photoService;

	@ModelAttribute("photo")
	public PhotoForm modelAttribute() {
		return new PhotoForm();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showForm() {
		return UPLOAD_FORM_VIEW;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String handleFormUpload(@Valid @ModelAttribute("photo") PhotoForm photo, BindingResult result, SessionStatus status) throws IOException {
		if (!result.hasErrors()) {
			if (photoService.saveImage(photo)) {
				status.setComplete();
				return "redirect:/photos/show?page=0";
			}
		}
		return UPLOAD_FORM_VIEW;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String listPhotos(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
		model.addAttribute("photos", photoService.listAll(new PageRequest(page, PAGE_SIZE, new Sort(Direction.DESC, "date"))));
		return "photos";
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void getImage(@RequestParam Long id, HttpServletResponse response) throws IOException {
		PhotoView pv = photoService.getImage(id);
		response.setContentType(pv.getContentType());
		FileCopyUtils.copy(pv.getPhoto(), response.getOutputStream());
	}

	@ExceptionHandler({ IOException.class, MaxUploadSizeExceededException.class })
	public ModelAndView handleUploadException(Exception ex, HttpServletRequest request, HttpSession session) {
		PhotoForm model = (PhotoForm) WebUtils.getOrCreateSessionAttribute(session, "photo", PhotoForm.class);
		request.setAttribute("error", ex.getLocalizedMessage());
		return new ModelAndView(UPLOAD_FORM_VIEW, "photo", model);
	}

	/**
	 * @param photoService
	 *            the photoService to set
	 */
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
}
