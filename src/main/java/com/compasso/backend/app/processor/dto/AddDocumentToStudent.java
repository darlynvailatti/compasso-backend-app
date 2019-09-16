package com.compasso.backend.app.processor.dto;


import com.compasso.backend.app.domain.entity.StudentDocumentEntity;
import com.compasso.backend.app.domain.entity.StudentEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessorDTO;

import java.util.List;

public class AddDocumentToStudent extends AbstractProcessorDTO {

    private StudentEntity student;

    private List<StudentDocumentEntity> documents;

    public AddDocumentToStudent(StudentEntity student, List<StudentDocumentEntity> documents) {
        this.student = student;
        this.documents = documents;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public List<StudentDocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<StudentDocumentEntity> documents) {
        this.documents = documents;
    }


    public static class Return extends AbstractProcessorDTO {

        private List<StudentDocumentEntity> documents;

        public Return(List<StudentDocumentEntity> documents) {
            this.documents = documents;
        }

        public List<StudentDocumentEntity> getDocuments() {
            return documents;
        }

        public void setDocuments(List<StudentDocumentEntity> documents) {
            this.documents = documents;
        }
    }
}
