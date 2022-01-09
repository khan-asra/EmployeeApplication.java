package com.asra.employeeapplication.database;

import com.asra.employeeapplication.beans.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {
    private final NamedParameterJdbcTemplate jdbc;
    public DatabaseAccess(NamedParameterJdbcTemplate jdbc) {
        this.jdbc=jdbc;
    }


    public int addEmployee(Employee employee) {
        MapSqlParameterSource namedParameters =new MapSqlParameterSource();
        String query ="INSERT INTO employee (firstname, lastName, email, phone, department,title) " +
                "VALUES (:firstName, :lastName, :email, :phone, :department, :title)";

        namedParameters
                .addValue("firstName",employee.getFirstName())
                .addValue("lastName",employee.getLastName())
                .addValue("email",employee.getEmail())
                .addValue("phone",employee.getPhone())
                .addValue("department",employee.getDepartment() )
                .addValue("title",employee.getTitle());
        int returnValue =jdbc.update(query,namedParameters);
        return returnValue;

    }

    public Employee getEmployee(Long id) {
        MapSqlParameterSource namedParameters =new MapSqlParameterSource();
        String query = "SELECT * FROM employee where id= :id";
        namedParameters.addValue("id", id );


        BeanPropertyRowMapper<Employee> Mapper =new
                BeanPropertyRowMapper<Employee>(Employee.class);
        List<Employee> employee =jdbc.query(query, namedParameters,
                Mapper);


        if (employee.isEmpty()) {
            System.out.println("from  the data base");
            return  null;
        }
        else {
            return employee.get(0);
        }
    }

    public int updateEmployee(Employee employee) {
        MapSqlParameterSource namedParameters =new MapSqlParameterSource();
        String query ="UPDATE  employee SET firstName = :firstName, lastName = :lastName," +
                " email = :email, phone = :phone, department = :department, title = :title " +
                "WHERE id = :id";
        namedParameters
                .addValue("id",employee.getId())
                .addValue("firstName",employee.getFirstName())
                .addValue("lastName",employee.getLastName())
                .addValue("email",employee.getEmail())
                .addValue("phone",employee.getPhone())
                .addValue("department",employee.getDepartment() )
                .addValue("title",employee.getTitle());

        int returnValue =jdbc.update(query,namedParameters);
        return returnValue;


    }

    public int deleteEmployee(Long id) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="DELETE FROM employee WHERE id = :id";
        namedParameters.addValue("id",id);
        int returnValue = jdbc.update(query, namedParameters);
        return returnValue;
    }

    public List<Employee> getAllEmployee() {


        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        String query = "SELECT * from employee";
        BeanPropertyRowMapper<Employee> bookMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
        List<Employee> employee = jdbc.query(query, namedParameter, bookMapper);
        return employee;

    }
}
