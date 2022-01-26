package com.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static com.example.collections.EmployeeServiceImplTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceImplTest {

    private EmployeeServiceImpl out = new EmployeeServiceImpl();

    @BeforeEach
    public void unit() {
        this.out = new EmployeeServiceImpl();
    }

    @Test
    void addSimple() {
        Employee actual = out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        Employee expected = new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        assertEquals(actual, expected);
    }

    @Test
    void addDuplicate() {
        out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        assertThrows(EmployeeNotExistsException.class, () -> out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1));
    }

    @Test
    void addInvalid() {
        assertThrows(EmployeeInvalidException.class, () -> out.add(FIRSTNAME_V4, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1));
    }

    @Test
    void removeSimple() {
        out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        Employee actual = out.remove(FIRSTNAME_V1, LASTNAME_V1);
        Employee expected = new Employee(FIRSTNAME_V1, LASTNAME_V1);
        assertEquals(actual, expected);
    }

    @Test
    void removeAbsent() {
        assertThrows(EmployeeNotExistsException.class, () -> out.remove(FIRSTNAME_V1, LASTNAME_V1));
    }

    @Test
    void findSimple() {
        out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        Employee actual = out.find(FIRSTNAME_V1, LASTNAME_V1);
        Employee expected = new Employee(FIRSTNAME_V1, LASTNAME_V1);
        assertEquals(actual, expected);
    }

    @Test
    void findAbsent() {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(FIRSTNAME_V1, LASTNAME_V1));
    }

    @Test
    void getAll() {
        out.add(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        out.add(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V2);
        Collection<Employee> actual = out.getAll();
        Set<Employee> expected = Set.of(
                new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1),
                new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V2));
        assertEquals(actual.size(), expected.size());
        assertTrue(expected.containsAll(actual));
    }
}