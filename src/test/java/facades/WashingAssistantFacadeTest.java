package facades;

import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;
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
public class WashingAssistantFacadeTest {

    private static EntityManagerFactory emf;
    private static WashingAssistantFacade facade;

    public WashingAssistantFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = WashingAssistantFacade.getWashingAssistantFacade(emf);
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

            Calendar cal = Calendar.getInstance();
            cal.set(2022, 0, 16, 14, 30, 00);
            Car car = new Car("XD39", "toyota", "aigo", 1999);

            Booking booking = new Booking(cal.getTime(), "2hrs", waList, car);
            List<Booking> bookingList = new ArrayList<>();
            bookingList.add(booking);
            car.setBookingList(bookingList);



            em.getTransaction().begin();
            em.createNamedQuery("booking.deleteAllRows").executeUpdate();
            em.createNamedQuery("car.deleteAllRows").executeUpdate();
            em.createNamedQuery("washingAssistant.deleteAllRows").executeUpdate();
            em.persist(washingAssistant);
            em.persist(washingAssistant2);
            em.persist(washingAssistant3);
            em.persist(car);
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
    public void testGetAll() throws Exception {
        assertEquals(3, facade.getAll().size(), "Expects two rows in the database");
    }

    @Test
    public void testCreateWashingAssistant(){
        WashingAssistantDTO washDTO = new WashingAssistantDTO("brotherman", "engelsk", 20, 3000);
        facade.createWashingAssistant(washDTO);
        assertEquals("brotherman", washDTO.getName());
    }
    

}
