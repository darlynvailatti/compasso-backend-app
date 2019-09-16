package com.compasso.backend.app.service.impl;

import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.processor.ProcessorAddDocumentToStudent;
import com.compasso.backend.app.processor.dto.AddDocumentToStudent;
import com.compasso.backend.app.repository.IStudentRepository;
import com.compasso.backend.app.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ProcessorAddDocumentToStudent processorAddDocumentoToStudent;

    @Override
    public StudentEntity find(Long id) throws BusinessLogicException {
        return studentRepository.findById(id).get();
    }

    @Override
    public Page<StudentEntity> listWithPagination(Pageable pagination) throws BusinessLogicException {
        return studentRepository.findAll(pagination);
    }

    @Override
    public Page<StudentEntity> listByNameContainingWithPagination(String name, Pageable pagination) throws BusinessLogicException {
        return studentRepository.findByNameContainingIgnoreCase(name, pagination);
    }

    @Override
    public List<StudentEntity> listAll() throws BusinessLogicException {
        Spliterator<StudentEntity> spliterator = studentRepository.findAll().spliterator();
        List<StudentEntity> students = new ArrayList<>();
        spliterator.forEachRemaining(students::add);
        return students;
    }

    @Override
    public void delete(Long id) throws BusinessLogicException {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentEntity confirm(StudentEntity entity) throws BusinessLogicException {
        return studentRepository.save(entity);
    }

    @Override
    public AddDocumentToStudent.Return addDocumentsToStudent(AddDocumentToStudent add) throws BusinessLogicException {
        return processorAddDocumentoToStudent.execute(add);
    }


}
