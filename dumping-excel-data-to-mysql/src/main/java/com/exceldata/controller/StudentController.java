package com.exceldata.controller;

import com.exceldata.entities.Student;
import com.exceldata.helper.ExcelHelper;
import com.exceldata.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if (ExcelHelper.checkExcelFormat(file)){
            //true

            this.studentService.save(file);
            return ResponseEntity.ok(Map.of("Message","File is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please do upload EXCEL file");
    }

    @GetMapping("/getStudents")
    public List<Student> getAllStudents(){
        return this.studentService.getAllStudents();
    }
}
