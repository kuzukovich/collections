package com.example.collections;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new LinkedHashMap<>();
    }

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = createKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeNotExistsException();
        }
        employees.put(key, employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = createKey(firstName, lastName);
        Employee result = employees.remove(key);
        if (result == null) {
            throw new EmployeeNotExistsException();
        }
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = createKey(firstName, lastName);
        Employee show = employees.get(key);
        if (show == null) {
            throw new EmployeeNotExistsException();
        }
        return employee;
    }

    private String createKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

}
