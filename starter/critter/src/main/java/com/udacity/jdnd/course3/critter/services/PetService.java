package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by ThanhTLN on 2023/04/11.
 * Description: Pet Service
 */
@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;
    public Pet savePet(Pet pet, long ownerId) {
        // save pet
        Pet savedPet =  petRepository.save(pet);
        // get customer through pet
        Customer customer = savedPet.getCustomer();
        // get list pet by customer
        List<Pet> customerPets = customer.getPets();
        // check null
        if(customerPets == null){
            customerPets = new ArrayList<>();
        }
        // add pet to customer
        customerPets.add(savedPet);
        customer.setPets(customerPets);
        customerRepository.save(customer);
        return savedPet;
    }

    public Pet getPetById(long petId) {
        // query pet by id
        Optional<Pet> pet = petRepository.findById(petId);
        // check exist or exception
        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public List<Pet> getAllPet() {
        // query pet by id
        return petRepository.findAll();
    }

    // commnet out by ThanhTln
    public List<Pet> getPetsByOwner(Long ownerId) {
        // get customer by id
        //Optional<Customer> customer = customerRepository.findById(ownerId);
        // get pet by customer
       // return customer.get().getPets();

//        Optional<Customer> customer = customerRepository.findById(ownerId);
//        if (customer.isPresent()) {
//            return petRepository.getPetsByCustomer(customer.get());
//        } else {
//            throw new UnsupportedOperationException();
//        }
        return petRepository.findAllByCustomerId(ownerId);
    }

//    public List<Pet> getPetsByOwner(Long id) {
//        return petRepository.findAllByCustomerId(id);
//    }
}
