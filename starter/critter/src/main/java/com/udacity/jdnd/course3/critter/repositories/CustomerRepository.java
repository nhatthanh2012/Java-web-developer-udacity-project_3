package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Schedule JPA repository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
