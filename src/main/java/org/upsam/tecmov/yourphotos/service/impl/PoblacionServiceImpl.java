package org.upsam.tecmov.yourphotos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;
import org.upsam.tecmov.yourphotos.domain.poblacion.PoblacionRepository;
import org.upsam.tecmov.yourphotos.service.PoblacionService;
import org.upsam.tecmov.yourphotos.utils.DefaultNormalizer;

@Service
@Transactional(readOnly = true)
public class PoblacionServiceImpl implements PoblacionService {
	/**
	 * 
	 */
	private PoblacionRepository poblacionRepository;

	@Autowired
	public PoblacionServiceImpl(PoblacionRepository poblacionRepository) {
		this.poblacionRepository = poblacionRepository;
	}

	@Override
	public Page<Poblacion> findByPoblacionSeo(String nameLike, Integer page,
			Integer size) {
		return poblacionRepository.findByPoblacionSeoLike("%"
				+ DefaultNormalizer.normalize(nameLike) + "%", new PageRequest(
				page, size, new Sort("provincia.nombreSeo", "poblacionSeo")));
	}

	@Override
	public Page<Poblacion> findAll(Integer page, Integer size) {
		return poblacionRepository.findAll(new PageRequest(page, size,
				new Sort("provincia.nombreSeo", "poblacionSeo")));
	}

}
