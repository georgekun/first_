package ru.jorj.spring.back.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.jorj.spring.back.dao.StudentDAO;
import ru.jorj.spring.back.models.Student;



import java.sql.SQLException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081") //политика cors, таким образом axios имеет доступ к данным
@RequestMapping
public class StudentControllers {
    private StudentDAO studentDao;

    @Autowired
    public StudentControllers(StudentDAO studentDao) {
        this.studentDao = studentDao;
    }


    @GetMapping("/students")
    public List<Student> allStudent() throws SQLException, JsonProcessingException {

        return studentDao.allStudents();
    }


    @PostMapping("/post")
    public String insertStudentInDataBase(@RequestParam String name,
                                          @RequestParam Integer age,
                                          @RequestParam String email) throws SQLException {
        Student stud = new Student(studentDao.getSize() + 1, age, name, email);
        studentDao.setToDataBase(stud);
        return "good";
    }

    @PostMapping("/delete")
    public String deleteStudentInDataBase(@RequestParam Integer id) throws SQLException {
        studentDao.deleteToDataBase(id);
        return "good";
    }
}
