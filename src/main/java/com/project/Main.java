package com.project;

import com.project.Entities.Citizen;
import com.project.Entities.City;

import java.io.File;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";

        File dir = new File(basePath);
        if (!dir.exists()){
            if(!dir.mkdirs()) {
                System.out.println("Error creating 'data' folder.");
            }
        }
        Manager.createSessionFactory();
        Manager.addCity("Madrid", "Spain", 10000);
        Manager.addCity("Vienna", "Austria", 11111);
        Manager.addCity("Budapest", "Hungary", 22222);

        Manager.addCitizen(1, "Heinz", "Doofenshmirtz", 36);
        Manager.addCitizen(1, "Perry", "The Platypus", 2);
        Manager.addCitizen(2, "Heinz", "Doofenshmirtz", 36);
        Manager.addCitizen(2, "Perry", "The Platypus", 2);
        Manager.addCitizen(3, "Heinz", "Doofenshmirtz", 36);
        Manager.addCitizen(3, "Perry", "The Platypus", 2);

        Manager.listCollection(City.class, "");
        Manager.listCollection(Citizen.class, "");

        System.out.println(Manager.listCollection(Citizen.class, " city_id = 1").toString());

        Collection<City> cities = (Collection<City>) Manager.listCollection(City.class,"");
        for (int j = 0; j < cities.size() ; j++) {
            City city = ((City) cities.stream().toList().get(j));

            Collection<Citizen> citizens = (Collection<Citizen>) Manager.listCollection(Citizen.class, " city_id = " + city.getCityId());
            for (int i = 0; i < citizens.size(); i++) {
                if (i == 1) Manager.delete(Citizen.class, ((Citizen) citizens.stream().toList().get(i)).getId());
            }
            if (j == 2) {
                Manager.delete(City.class, (city.getCityId()));
            }
        }

        for (Object city : Manager.listCollection(City.class, "")) {
            String cityId = Long.toString(((City) city).getCityId());
            String cityName = ((City) city).getName();
            System.out.println("The citizens in " + cityName + " are:");
            for (Object citizen : Manager.listCollection(Citizen.class, " city_id = " + cityId)) {
                Citizen cityCitizen = (Citizen) citizen;
                System.out.printf("\t %s %s, %s - %s\n", cityCitizen.getFirstName(), cityCitizen.getLastName(), cityCitizen.getAge(), cityCitizen.getId());
            }
        }

        System.out.println("Cities");
        for (Object city : Manager.listCollection(City.class, "")) {
            City cityRow = (City) city;
            System.out.printf("\t %s, %s %s, %s\n", cityRow.getName(), cityRow.getCountry(), cityRow.getPostalCode(), cityRow.getCityId());
        }

        System.out.println("Citizens");
        for (Object citizen : Manager.listCollection(Citizen.class, "")) {
            Citizen citizenRow = (Citizen) citizen;
            System.out.printf("\t %s %s, %s - %s\n", citizenRow.getFirstName(), citizenRow.getLastName(), citizenRow.getAge(), citizenRow.getCityId());
        }

        Manager.close();
    }
}
