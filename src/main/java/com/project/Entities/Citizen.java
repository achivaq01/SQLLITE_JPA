package com.project.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Citizen")
public class Citizen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "citizen_id", unique = true, nullable = false)
    private long id;

    @Column(name = "city_id", nullable = false)
    private long cityId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    public Citizen() {
        super();
    }

    public Citizen(long cityId, String firstName, String lastName, int age) {
        super();
        this.cityId = cityId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", cityId=" + getCityId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
