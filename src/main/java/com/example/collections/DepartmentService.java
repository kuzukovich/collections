package com.example.collections;

import java.util.Collection;


public interface DepartmentService {
    Employee getEmployeeMaxSalary(int departmentId);

    Employee getEmployeeMinSalary(int departmentId);

    Collection<Employee> getEmployee(int departmentId);

    Collection<Employee> getAllEmployee();
}
