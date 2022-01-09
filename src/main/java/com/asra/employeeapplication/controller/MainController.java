package com.asra.employeeapplication.controller;

import com.asra.employeeapplication.beans.Employee;
import com.asra.employeeapplication.database.DatabaseAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class MainController {
    CopyOnWriteArrayList<Employee> employeeList = new CopyOnWriteArrayList<>();

    private final DatabaseAccess database;

    public MainController(DatabaseAccess database) {
        this.database = database;
    }


    @GetMapping("/")
    public String goIndex() {

        System.out.print(employeeList.size());
        return "index";
    }


    @GetMapping("/addANewEmployee")
    public String addEmployeePage(Model model) {
        //   Employee employee= new Employee();
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeList", employeeList);
        return "addEmployee";

    }

    @PostMapping("/addEmployee")
    public String addEmployee(Model model, @ModelAttribute Employee employee) {
        employeeList.add(employee);
        model.addAttribute("employeeList", employeeList);
        System.out.print(employee.getEmail());
        int returnValue = database.addEmployee(employee);
        System.out.println("return value is: " + returnValue);
        return "index";

    }

    @GetMapping("/viewEmployee")
    public String viewEmployee(Model model) {
        model.addAttribute("allEmployees", database.getAllEmployee());
        return "viewEmployee";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee employee = database.getEmployee(id);
        model.addAttribute("employee", employee);
        return "editEmployee";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        database.deleteEmployee(id);
        model.addAttribute("allEmployees", database.getAllEmployee());
        return "viewEmployee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        int returnValue = database.updateEmployee(employee);
        model.addAttribute("allEmployees", database.getAllEmployee());
        return "viewEmployee";

    }
}
