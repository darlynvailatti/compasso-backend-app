package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.FederativeUnitEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFedarativeUnitRepository extends PagingAndSortingRepository<FederativeUnitEntity, Long> {
}
