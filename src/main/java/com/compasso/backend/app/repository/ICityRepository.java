package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends PagingAndSortingRepository<CityEntity, Long> {

    @EntityGraph(attributePaths = {"federativeUnit"}, type = EntityGraph.EntityGraphType.LOAD)
    CityEntity findFetchById(Long idCity);

    Page<CityEntity> findByNameContainingIgnoreCase(String name, Pageable pageRequest);

    Page<CityEntity> findByFederativeUnit(Long idFederativeUnit, Pageable pageRequest);

    Page<CityEntity> findByFederativeUnitInitialsContainingIgnoreCase(String initials, Pageable pageRequest);
}
