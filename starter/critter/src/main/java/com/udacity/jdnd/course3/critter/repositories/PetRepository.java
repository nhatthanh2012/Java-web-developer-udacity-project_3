package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Pet JPA repository
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCustomerId(Long id);
}
