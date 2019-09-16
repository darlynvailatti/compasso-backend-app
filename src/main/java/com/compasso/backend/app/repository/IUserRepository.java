package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);
	
}
