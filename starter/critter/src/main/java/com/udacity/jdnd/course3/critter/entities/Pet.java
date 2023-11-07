package com.udacity.jdnd.course3.critter.entities;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pet")
/**
 * Created by ThanhTLN 2023/11/04
 * description: create entity for pet table
 */
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Nationalized
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    private LocalDate birthDate;

    @Nationalized
    private String notes;

    public Pet(PetType type, String name, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet() {

    }

    public long getId() {
        return id;
    }

    public PetType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
