package com.example.collections;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Set<Employee> employees;

    public EmployeeServiceImpl() {
        employees = new LinkedHashSet<>();
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        boolean notContains = !employees.add(employee);
        if (notContains) {
            throw new EmployeeNotExistsException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        boolean notContains = !employees.remove(employee);
        if (notContains) {
            throw new EmployeeNotExistsException();
        }
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        boolean notContains = !employees.contains(employee);
        if (notContains) {
            throw new EmployeeNotExistsException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return employees;
    }

}
