package org.upsam.tecmov.yourphotos.domain.suggestion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.upsam.tecmov.yourphotos.domain.poblacion.Poblacion;

public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, Long> {

	Page<Suggestion> findByPoblacion(Poblacion poblacion, Pageable pageable);
}
