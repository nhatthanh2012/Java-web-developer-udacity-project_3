package com.udacity.jdnd.course3.critter.entities;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
/**
 * Created by ThanhTLN - 20231104
 * Description: create customer entity
 */
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Nationalized
    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column
    @Nationalized
    private String notes;

    @OneToMany(mappedBy = "customer", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {
    }
    public Customer(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        //this.pets = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
