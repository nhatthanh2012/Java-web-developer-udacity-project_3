package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Employee Service
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    public Employee getEmployeeById(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        // check exist or exception
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new UnsupportedOperationException();
        }
    }
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        employee.setDaysAvailable(daysAvailable);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<Employee> employeesMatchDate = employeeRepository.findAllByDaysAvailable(date.getDayOfWeek());
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employeesMatchDate) {
            if (employee.getSkills().containsAll(skills)) {
                result.add(employee);
            }
        }
        return result;
    }
}
