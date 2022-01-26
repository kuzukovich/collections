package com.example.collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new LinkedHashMap<>();
    }

    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        checkFirstName(firstName);
        checkLastName(lastName);
        Employee employee = new Employee(upFirstName(firstName), upLastName(lastName), salary, departmentId);
        String key = createKey(upFirstName(firstName), upLastName(lastName));
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
        Employee targetEmployee = employees.get(key);
        if (targetEmployee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    private String createKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private String checkFirstName(String firstName) {
        if (isAlpha(firstName)) {
            return firstName;
        } else throw new EmployeeInvalidException();
    }

    private String checkLastName(String lastName) {
        if (isAlpha(lastName)) {
            return lastName;
        } else throw new EmployeeInvalidException();
    }

    private String upFirstName(String firstName) {
        return capitalize(firstName);
    }

    private String upLastName(String lastName) {
        return capitalize(lastName);
    }

    @Override
    public Collection<Employee> getAll() {
        return Set.copyOf(employees.values());
    }

}
