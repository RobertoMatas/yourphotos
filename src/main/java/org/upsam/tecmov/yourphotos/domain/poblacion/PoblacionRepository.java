package org.upsam.tecmov.yourphotos.domain.poblacion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PoblacionRepository extends PagingAndSortingRepository<Poblacion, Long> {

	Page<Poblacion> findByPoblacionSeoLike(String poblacionSeo, Pageable pageable);
	
	List<Poblacion> findByCodPostalAndPoblacionSeoLike(String codPostal, String poblacionSeo);
	
	List<Poblacion> findByCodPostal(String codPostal);
	
	Page<Poblacion> findByNumSuggestionsGreaterThanAndCategoryGreaterThan(Integer numSuggestions, Integer category, Pageable pageable);
}
