package com.example.collections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeMaxSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeMinSalary(@RequestParam int departmentId) {
        return departmentService.getEmployeeMinSalary(departmentId);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> getEmployee(@RequestParam int departmentId) {
        return departmentService.getEmployee(departmentId);
    }

    @GetMapping("/all")
    public Collection<Employee> getAllEmployee() {
        return departmentService.getAllEmployee();
    }
}
