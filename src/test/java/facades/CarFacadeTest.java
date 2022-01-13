package facades;

import dtos.CarDTO;
import dtos.UserDTO;
import dtos.WashingAssistantDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            List<WashingAssistant> waList = new ArrayList<>();
            WashingAssistant washingAssistant = new WashingAssistant("Bill", "dansk", 5, 300);
            WashingAssistant washingAssistant2 = new WashingAssistant("Makrel", "dansk", 8, 200);
            WashingAssistant washingAssistant3 = new WashingAssistant("Jønke", "dansk", 12, 400);
            waList.add(washingAssistant);



            User user = new User("Kammerat", "userpass");
            User user1 = new User("lars", "userpass");

            Calendar cal = Calendar.getInstance();
            cal.set(2022, 0, 16, 14, 30, 00);
            Car car = new Car("XD39", "toyota", "aigo", 1999);
            Car car1 = new Car("HO-OH 77", "ferrari", "duolingo", 2001);
            car1.setUser(user1);

            Booking booking = new Booking(cal.getTime(), "2hrs", waList, car);
            List<Booking> bookingList = new ArrayList<>();
            bookingList.add(booking);
            car.setBookingList(bookingList);



            em.getTransaction().begin();

            em.createNamedQuery("booking.deleteAllRows").executeUpdate();
            em.createNamedQuery("car.deleteAllRows").executeUpdate();
            em.createNamedQuery("washingAssistant.deleteAllRows").executeUpdate();
            em.createNamedQuery("user.deleteAllRows").executeUpdate();
            em.createNamedQuery("roles.deleteAllRows").executeUpdate();
            Role userRole = new Role("user");
            user.addRole(userRole);
            user1.addRole(userRole);
            em.persist(userRole);
            em.persist(user);
            em.persist(user1);
            em.persist(washingAssistant);
            em.persist(washingAssistant2);
            em.persist(washingAssistant3);
            em.persist(car);
            em.persist(car1);
            em.persist(booking);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetCar() throws Exception {
        assertEquals("duolingo", facade.getCar("HO-OH 77").getMake(), "Expects two rows in the database");
    }

    @Test
    public void testCreateCar(){
        CarDTO carDTO = new CarDTO("Hehe400", "Mercedes", "l9", 2003);
        UserDTO userDTO = new UserDTO("kammerat");
        carDTO.setUserDTO(userDTO);
        facade.createCar(carDTO);

        assertEquals("l9", facade.getCar("Hehe400").getMake());
    }
    

}
