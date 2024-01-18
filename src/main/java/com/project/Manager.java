package com.project;

import com.project.Entities.Citizen;
import com.project.Entities.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Collection;

public class Manager {
    private static SessionFactory factory;

    public static void createSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void close () {
        factory.close();
    }

    public static City addCity(String name, String country, int postalCode) {
        Session session = factory.openSession();
        Transaction transaction = null;
        City result;
        try {
            transaction = session.beginTransaction();
            result = new City(name, country, postalCode);
            session.save(result);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }

    public static Citizen addCitizen(long cityId, String firstName, String lastName, int age) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Citizen result;
        try {
            transaction = session.beginTransaction();
            result = new Citizen(cityId, firstName, lastName, age);
            session.save(result);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz, String where){
        Session session = factory.openSession();
        Transaction transaction = null;
        Collection<?> result = null;
        try {
            transaction = session.beginTransaction();
            if (where.isEmpty()) {
                result = session.createQuery("FROM " + clazz.getName()).list();
            } else {
                result = session.createQuery("FROM " + clazz.getName() + " WHERE " + where).list();
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> void delete(Class<? extends T> clazz, Serializable id){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T obj = clazz.cast(session.get(clazz, id));
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateCitizen(long citizenId, String name, String lastName, int age, long cityId){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Citizen obj = (Citizen) session.get(Citizen.class, citizenId);
            obj.setFirstName(name);
            obj.setLastName(lastName);
            obj.setAge(age);
            obj.setCityId(cityId);
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateCity(long cityId, String name, String country, int postalCode){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            City obj = (City) session.get(City.class, cityId);
            obj.setName(name);
            obj.setCountry(country);
            obj.setPostalCode(postalCode);
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
