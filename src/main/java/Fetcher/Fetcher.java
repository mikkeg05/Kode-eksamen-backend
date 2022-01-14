package Fetcher;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.gson.GsonBuilder;
import dtos.CarDTO;
import entities.Booking;
import entities.Car;
import entities.User;
import entities.WashingAssistant;
import facades.BookingFacade;
import facades.CarFacade;
import facades.UserFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fetcher {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CarFacade CAR_FACADE = CarFacade.getCarFacade(emf);
        UserFacade USER_FACADE = UserFacade.getUserFacade(emf);
        EntityManager em = emf.createEntityManager();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        User user = USER_FACADE.getUser("user1");






        //System.out.println(gson.toJson(CAR_FACADE.getCar("6969")));





    }
}
