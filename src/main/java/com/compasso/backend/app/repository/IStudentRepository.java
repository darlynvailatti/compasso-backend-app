package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IStudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {

    Page<StudentEntity> findByNameContainingIgnoreCase(String name, Pageable pageRequest);

}
