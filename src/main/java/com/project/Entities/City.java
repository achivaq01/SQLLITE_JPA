package com.project.Entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "City")
public class City implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id", unique = true, nullable = false)
    private long cityId;
    
    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private int postalCode;

    public City() {
        super();
    }

    public City(String name, String country, int postalCode) {
        super();
        this.name = name;
        this.country = country;
        this.postalCode = postalCode;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
