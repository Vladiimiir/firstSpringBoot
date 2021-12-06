package com.test.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "city")
    @NotNull
    private String city;


    @Column(name = "street")
    @NotNull
    private String street;


    @Column(name = "buildingNumber")
    @NotNull
    private String buildingNumber;

    @JsonBackReference
    @OneToMany(mappedBy = "address",fetch = FetchType.EAGER)
    private List<User> users;

    public Address() {
    }

    public Address(int id) {
        this.id = id;
    }

    public Address(String city, String street, String buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    public Address(String city, String street, String buildingNumber, List<User> users) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.users = users;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return city.equals(address.city) && street.equals(address.street) && buildingNumber.equals(address.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, buildingNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
//                ", users=" + users +
                '}';
    }
}
