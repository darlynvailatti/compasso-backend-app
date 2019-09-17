package com.compasso.backend.app.rest.resource.impl;

import com.compasso.backend.app.domain.entity.StudentDocumentEntity;
import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.rest.pattern.AbstractRestResource;
import com.compasso.backend.app.rest.converter.architecture.RestConverterPageRequest;
import com.compasso.backend.app.rest.converter.RestConverterStudent;
import com.compasso.backend.app.rest.converter.RestConverterStudentDocument;
import com.compasso.backend.app.rest.dto.architecture.PageDTO;
import com.compasso.backend.app.rest.dto.architecture.PageRequestDTO;
import com.compasso.backend.app.rest.dto.StudentDTO;
import com.compasso.backend.app.rest.dto.StudentDocumentDTO;
import com.compasso.backend.app.rest.resource.IStudentResource;
import com.compasso.backend.app.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentResourceImpl extends AbstractRestResource
	implements IStudentResource {

    private static final Logger log = LoggerFactory.getLogger(StudentResourceImpl.class);

    @Autowired
    private IStudentService studentService;
    
    @Autowired
    private RestConverterStudent restConverterStudent;

    @Autowired
    private RestConverterPageRequest restConverterPageRequest;
    
    @Autowired
    private RestConverterStudentDocument restConverterStudentDocument;

    @Override
    @PostMapping
    public ResponseEntity<StudentDTO> confirm(@RequestBody StudentDTO studentDTO) throws Exception{
        StudentEntity entity = restConverterStudent.convertToEntity(studentDTO);
        StudentEntity createdStudent = studentService.confirm(entity);
        studentDTO = restConverterStudent.convertToDTO(createdStudent);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @Override
    @PostMapping("all/by/pagination")
    public @ResponseBody
    PageDTO<StudentDTO> listAllWithPagination(@RequestBody PageRequestDTO pageRequestDTO) throws Exception{
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<StudentEntity> page = studentService.listWithPagination(pageRequest);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for (StudentEntity studentEntity : page.getContent()) {
            StudentDTO studentDTO = restConverterStudent.convertToDTO(studentEntity);
            studentsDTO.add(studentDTO);
        }

        return new PageDTO<>(page.getTotalElements(), studentsDTO);
    }

    @Override
    @PostMapping("/by/name/pagination")
    public @ResponseBody
    PageDTO<StudentDTO> listAllByNameContainingWithPagination(@RequestParam String name, @RequestBody PageRequestDTO pageRequestDTO) throws Exception{
        System.out.println("Name search for:" + name);
        PageRequest pageRequest = restConverterPageRequest.convertToEntity(pageRequestDTO);
        Page<StudentEntity> page = studentService.listByNameContainingWithPagination(name,pageRequest);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for (StudentEntity studentEntity : page.getContent()) {
            StudentDTO studentDTO = restConverterStudent.convertToDTO(studentEntity);
            studentsDTO.add(studentDTO);
        }

        return new PageDTO<>(page.getTotalElements(), studentsDTO);
    }

    @Override
    @GetMapping
    public @ResponseBody
    List<StudentDTO> listAll() throws BusinessLogicException {
        List<StudentEntity> studentEntities = studentService.listAll();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
        	try {
        		StudentDTO studentDTO = restConverterStudent.convertToDTO(studentEntity);
        		studentsDTO.add(studentDTO);
			} catch (Exception e) {
				throw new BusinessLogicException(e);
			}
        }
        return studentsDTO;
    }
    
    @GetMapping("{idStudent}")
    public ResponseEntity<StudentDTO> get(@PathVariable("idStudent") Long idStudent) throws Exception {
    	StudentEntity student = studentService.find(idStudent);
    	StudentDTO dto = restConverterStudent.convertToDTO(student);
    	return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

}
