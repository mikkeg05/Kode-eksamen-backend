package facades;

import dtos.BookingDTO;
import dtos.CarDTO;
import dtos.WashingAssistantDTO;
import entities.Car;
import entities.User;
import entities.WashingAssistant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class  CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;


    private CarFacade() {}


    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }
    UserFacade userFacade = UserFacade.getUserFacade(emf);
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public CarDTO createCar(CarDTO carDTO){
        EntityManager em = emf.createEntityManager();
        Car car = new Car(carDTO.getRegistrationnumber(), carDTO.getBrand(), carDTO.getMake(), carDTO.getYear());
        User user =  userFacade.getUser(carDTO.getUserDTO().getUserName());
        car.setUser(user);
        try{
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
            return new CarDTO(car);
        } finally{
            em.close();
        }
    }

    public CarDTO getCar(String registrationnumber){
        EntityManager em = getEntityManager();
        Car car;
        car = em.find(Car.class, registrationnumber);
        return new CarDTO(car);
    }

    public CarDTO getByUser(String username) {
        EntityManager em = emf.createEntityManager();

        try {
           User user = em.find(User.class, username);

            Car car = user.getCar();

            return new CarDTO(car);
        } finally{
            em.close();
        }
    }

}