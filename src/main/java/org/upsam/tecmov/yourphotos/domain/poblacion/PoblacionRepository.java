package org.upsam.tecmov.yourphotos.domain.poblacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PoblacionRepository extends PagingAndSortingRepository<Poblacion, Long> {

	Page<Poblacion> findByPoblacionSeoLike(String poblacion, Pageable pageable);
}
