package com.asra.employeeapplication.beans;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String title;

    private final String[] ALLDEPARTMENTS={"Human Resource","Accounting","IT","Sales", "Marketing"};
}
