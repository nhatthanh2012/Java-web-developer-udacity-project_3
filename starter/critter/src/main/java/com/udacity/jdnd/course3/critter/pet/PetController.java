package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    // ThanhTLN update on 2023/11/04
    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        // convert petDTO to pet Entity
        Pet pet = convertPetDTOToPet(petDTO);
        // save DB
        petService.savePet(pet, petDTO.getOwnerId());
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        PetDTO petDTO = convertPetToPetDTO(pet);
        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        // get all pet exist
        List<Pet> petList = petService.getAllPet();
        List<PetDTO> PetDTOList = new ArrayList<>();
        for (Pet pet: petList) {
            PetDTOList.add(convertPetToPetDTO(pet));
        }
        return PetDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOList = new ArrayList<>();
        // get pet list
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        // convert each pet to petDTO
        petList.forEach(pet -> petDTOList.add(convertPetToPetDTO(pet)));
        return petDTOList;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "ownerId");
        Long customerId = petDTO.getOwnerId();
        Customer customer = customerService.getCustomerById(customerId);
        pet.setCustomer(customer);

        return pet;

    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
}
