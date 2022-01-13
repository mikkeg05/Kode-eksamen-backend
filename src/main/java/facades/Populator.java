/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<WashingAssistant> washAList = new ArrayList<>();
        List<WashingAssistant> washAList2 = new ArrayList<>();
        List<Booking> bookingList = new ArrayList<>();


        WashingAssistant washingAssistant1 = new WashingAssistant("Camilla", "dansk", 4, 300);
        WashingAssistant washingAssistant2 = new WashingAssistant("Bill", "dansk", 15, 1000);


        washAList.add(washingAssistant1);
        washAList2.add(washingAssistant1);
        washAList.add(washingAssistant2);


        Car car1 = new Car("8888", "toyota", "j7", 1999);
        Car car2 = new Car("6969", "ferrari", "enzo", 1953);
        Car car3 = new Car("coolcar", "volkswagen", "golf", 2018);

        Calendar cal = Calendar.getInstance();
        cal.set(2021, 0, 16, 12, 30, 00);
        Calendar cal2 = Calendar.getInstance();
        cal.set(2021, 4, 24, 16, 00, 00);
        Calendar cal3 = Calendar.getInstance();
        cal.set(2021, 12, 31, 23, 30, 00);


        Booking booking1 = new Booking(cal.getTime(), "8hrs", washAList, car2);
        Booking booking2 = new Booking(cal2.getTime(), "4hrs", washAList2, car3);
        Booking booking3 = new Booking(cal3.getTime(), "24hrs", washAList, car1);

        bookingList.add(booking1);

        WashingAssistant washingAssistant3 = new WashingAssistant("Nicolai", "måske", 1, 50, bookingList);



        em.getTransaction().begin();

        em.persist(washingAssistant1);
        em.persist(washingAssistant2);
        em.persist(washingAssistant3);
        em.persist(car1);
        em.persist(car2);
        em.persist(car3);
        em.persist(booking1);
        em.persist(booking2);
        em.persist(booking3);

        em.getTransaction().commit();
        em.close();






    }

    public static void main(String[] args) {
        populate();
    }
}
