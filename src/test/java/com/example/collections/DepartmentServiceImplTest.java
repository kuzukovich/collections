package com.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.collections.EmployeeServiceImplTestConstants.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeServiceMock.getAll())
                .thenReturn(Set.of(
                        new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1),
                        new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V1),
                        new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V2)));
    }

    @Test
    void getEmployeeMaxSalary() {
        Employee actual = out.getEmployeeMaxSalary(DEPARTMENTID_V1);
        Employee expected = new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V1);
        assertEquals(actual, expected);
    }

    @Test
    void getEmployeeMaxSalaryWithoutDepartment() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeMaxSalary(DEPARTMENTID_V3));
    }


    @Test
    void getEmployeeMinSalary() {
        Employee actual = out.getEmployeeMinSalary(DEPARTMENTID_V1);
        Employee expected = new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1);
        assertEquals(actual, expected);
    }


    @Test
    void getEmployeeMinSalaryWithoutDepartment() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeMinSalary(DEPARTMENTID_V3));
    }

    @Test
    void getEmployee() {
        Collection<Employee> actual = out.getEmployee(DEPARTMENTID_V1);
        List<Employee> expected = List.of(
                new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1),
                new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V1));
        assertEquals(actual.size(), expected.size());
        assertTrue(expected.containsAll(actual));
    }

    @Test
    void getEmployeeWithoutDepartment() {
        Collection<Employee> actual = out.getEmployee(DEPARTMENTID_V3);
        List<Employee> expected = Collections.EMPTY_LIST;
        assertEquals(actual.size(), expected.size());
        assertTrue(expected.containsAll(actual));
    }

    @Test
    void getAllEmployee() {
        Collection<Employee> actual = out.getAllEmployee();
        //Не получилось отсортировать сотрудников по подразделению, используя метод Collections.sort.Не понимаю какую сигнатуру указать.
        // Помогите пожалуйста,несмотря на то, что тест успешный, я понимаю что он не совсем корректный.
        //List<Employee> expected= Collections.sort()
        List<Employee> expected = List.of(
                new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V2),
                new Employee(FIRSTNAME_V1, LASTNAME_V1, SALARY_V1, DEPARTMENTID_V1),
                new Employee(FIRSTNAME_V2, LASTNAME_V2, SALARY_V2, DEPARTMENTID_V1));
    }
}