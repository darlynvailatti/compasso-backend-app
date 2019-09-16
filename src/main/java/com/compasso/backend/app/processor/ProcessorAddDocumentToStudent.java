package com.compasso.backend.app.processor;

import com.compasso.backend.app.domain.entity.StudentDocumentEntity;
import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.exception.BusinessLogicException;
import com.compasso.backend.app.pattern.processor.AbstractProcessor;
import com.compasso.backend.app.processor.dto.AddDocumentToStudent;
import com.compasso.backend.app.repository.IStudentDocumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ProcessorAddDocumentToStudent
    extends AbstractProcessor<AddDocumentToStudent, AddDocumentToStudent.Return> {

    private static final Logger log = LoggerFactory.getLogger(ProcessorAddDocumentToStudent.class);
    
    @Autowired
    private IStudentDocumentoRepository studentDocumentRepository;

    private List<StudentDocumentEntity> savedDocuments;
    
    @Override
    protected void executionImplementation() throws BusinessLogicException {
    	
    	savedDocuments = new ArrayList<>();
    	
    	StudentEntity student = input.getStudent();
    	for (StudentDocumentEntity studentDocumentEntity : input.getDocuments()) {
    		studentDocumentEntity.setStudent(student);
			log.info("Document: {}", studentDocumentEntity);
			studentDocumentRepository.save(studentDocumentEntity);
			savedDocuments.add(studentDocumentEntity);
		}
    }

    @Override
    protected void executionReturn() {
        output = new AddDocumentToStudent.Return(savedDocuments);
    }

    @Override
    protected String getName() {
        return ProcessorAddDocumentToStudent.class.getSimpleName();
    }
}
