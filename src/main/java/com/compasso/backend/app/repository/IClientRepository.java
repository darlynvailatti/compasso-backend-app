package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

    ClientEntity findFetchById(Long id);

    Page<ClientEntity> findByFullNameContainingIgnoreCase(String name, Pageable pageRequest);

}
