package com.exceldata.service;

import com.exceldata.entities.Student;
import com.exceldata.helper.ExcelHelper;
import com.exceldata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    public void save(MultipartFile file){
        try {
            List<Student> students = ExcelHelper.convertExcelToListOfStudents(file.getInputStream());
            this.studentRepository.saveAll(students);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Student> getAllStudents(){
        return this.studentRepository.findAll();
    }
}
