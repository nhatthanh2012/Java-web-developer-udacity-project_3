package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(convertScheduleDtoToSchedule(scheduleDTO));
        return convertScheduleToScheduleDto(schedule);
//        Schedule newSchedule = new Schedule();
//        BeanUtils.copyProperties(scheduleDTO, newSchedule);
//        List < Long > employeeIds = scheduleDTO.getEmployeeIds();
//        List < Long > petIds = scheduleDTO.getPetIds();
//        List < Employee > employeeList = new ArrayList < > ();
//        List < Pet > petList = new ArrayList < > ();
//        if (employeeIds != null) {
//            for (Long id: employeeIds) {
//                employeeList.add(employeeService.findById(id));
//            }
//        }
//        if (petIds != null) {
//            for (Long id: petIds) {
//                petList.add(petService.findById(id));
//            }
//        }
//        newSchedule.setEmployeeIds(employeeList);
//        newSchedule.setPetIds(petList);
//        newSchedule.setActivities(scheduleDTO.getActivities());
//        scheduleService.createSchedule(newSchedule);
//        scheduleDTO.setId(newSchedule.getId());
//        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedulesList = scheduleService.getAllSchedules();
        List<ScheduleDTO> schedulesDtoList = new ArrayList<>();
        schedulesList.forEach(schedule -> schedulesDtoList.add(convertScheduleToScheduleDto(schedule)));

        return schedulesDtoList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleForPetList = scheduleService.findAllByPet(petId);
        List<ScheduleDTO> scheduleDTOForPetList = new ArrayList<>();
        scheduleForPetList.forEach(schedule -> scheduleDTOForPetList.add(convertScheduleToScheduleDto(schedule)));

        return scheduleDTOForPetList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleForEmployeeList = scheduleService.findAllByEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOForEmployeeList = new ArrayList<>();
        scheduleForEmployeeList.forEach(schedule -> scheduleDTOForEmployeeList.add(convertScheduleToScheduleDto(schedule)));

        return scheduleDTOForEmployeeList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleForCustomerList = scheduleService.findAllByCustomer(customerId);
        List<ScheduleDTO> scheduleDTOForCustomerList = new ArrayList<>();
        scheduleForCustomerList.forEach(schedule -> scheduleDTOForCustomerList.add(convertScheduleToScheduleDto(schedule)));

        return scheduleDTOForCustomerList;
    }

    private Schedule convertScheduleDtoToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Employee> employees = employeeRepository.findAllById(scheduleDTO.getEmployeeIds());
        List<Pet> pets = petRepository.findAllById(scheduleDTO.getPetIds());

        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return schedule;
    }

    private ScheduleDTO convertScheduleToScheduleDto(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();
        schedule.getPets().forEach(pet -> petIds.add(pet.getId()));
        schedule.getEmployees().forEach(employee -> employeeIds.add(employee.getId()));
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;
    }
}
