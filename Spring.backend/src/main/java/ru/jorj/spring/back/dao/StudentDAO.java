package ru.jorj.spring.back.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import ru.jorj.spring.back.connection.ConnectionFirstDb;
import ru.jorj.spring.back.models.Student;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDAO {
    private Statement statement;

    {
        try {
            statement = ConnectionFirstDb.connectToDataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSize() throws SQLException {
        String query = "SELECT COUNT(id) from person";
        ResultSet result = statement.executeQuery(query);
        result.next();
        return result.getInt(1);
    }

    public List<Student> allStudents() throws SQLException, JsonProcessingException {
        List<Student> studentList = new ArrayList<>();
        String SQL_request = "SELECT * FROM person";
        ResultSet setStudent = statement.executeQuery(SQL_request);

        while (setStudent.next()) {
            Student this_student = new Student();
            this_student.setId(setStudent.getInt("id"));
            this_student.setAge(setStudent.getInt("age"));
            this_student.setName(setStudent.getString("name"));
            this_student.setEmail(setStudent.getString("email"));
            studentList.add(this_student);
        }
        System.out.println();
        return studentList;
    }


    public boolean setToDataBase(Student stud) throws SQLException {
        char c = '"';
        String value = "insert into person value("+String.valueOf(stud.getId())+"," +c+ stud.getName()+c + ","
                +String.valueOf(stud.getAge()) + "," + c+stud.getEmail() +c+ ")";

        System.out.println(value);
        return statement.execute(value);
    }


    public void deleteToDataBase(Integer id) throws SQLException {
        String query = "DELETE from Person where id = " + String.valueOf(id);
        statement.execute(query);
    }
}
