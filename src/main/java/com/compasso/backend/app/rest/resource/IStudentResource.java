package com.compasso.backend.app.rest.resource;

import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import com.compasso.backend.app.rest.dto.StudentDTO;
import com.compasso.backend.app.rest.dto.StudentDocumentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentResource {

    ResponseEntity<StudentDTO> confirm(StudentDTO studentDTO) throws Exception;

    List<StudentDTO> listAll() throws Exception;

    List<StudentDocumentDTO> addDocumentsToStudent(Long idStudent, List<StudentDocumentDTO> documents) throws Exception;

    PageDTO<StudentDTO> listAllWithPagination(PageRequestDTO pageRequestDTO) throws Exception;

    public PageDTO<StudentDTO> listAllByNameContainingWithPagination(String name, PageRequestDTO pageRequestDTO) throws Exception;

}
