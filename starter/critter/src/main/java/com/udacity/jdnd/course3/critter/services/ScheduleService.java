package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Schedule Service
 */
@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Schedule> getAllSchedules() {
        // query get all Schedules
        List<Schedule> schedulesList = scheduleRepository.findAll();
        return schedulesList;
    }

    public Schedule createSchedule(Schedule schedule) {
        schedule.setPets(schedule.getPets());
        schedule.setEmployees(schedule.getEmployees());
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllByPet(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.getAllByPetsContains(pet);
    }

    public List<Schedule> findAllByEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.getAllByEmployeesContains(employee);
    }

    public List<Schedule> findAllByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        if(customer != null) {
            return scheduleRepository.getAllByPetsIn(customer.getPets());
        }
        return new ArrayList<>();
    }
}
