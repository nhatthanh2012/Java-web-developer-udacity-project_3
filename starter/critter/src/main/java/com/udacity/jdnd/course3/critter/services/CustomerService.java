package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Customer Service
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * @param  customer
     * Description: create new Customer
     */
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    /**
     * no params
     * Description: get list Customer
     */
    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId){
        return customerRepository.getOne(customerId);
    }
}
