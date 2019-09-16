package com.compasso.backend.app.repository;

import com.compasso.backend.app.domain.entity.StudentDocumentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDocumentoRepository extends PagingAndSortingRepository<StudentDocumentEntity, Long> {

}
