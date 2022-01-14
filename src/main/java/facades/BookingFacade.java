package facades;

import dtos.BookingDTO;
import dtos.CarDTO;
import dtos.WashingAssistantDTO;
import entities.Booking;
import entities.Car;
import entities.WashingAssistant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class  BookingFacade {

    private static BookingFacade instance;
    private static EntityManagerFactory emf;

    private BookingFacade() {}


    public static BookingFacade getBookingFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookingFacade();
        }
        return instance;
    }
    CarFacade carFacade = CarFacade.getCarFacade(emf);
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public List<BookingDTO> getByCar(String registrationnumber) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            Car car = em.find(Car.class, registrationnumber);
            if (car == null) {
                throw new EntityNotFoundException("Could not find a car with registration number " + registrationnumber);
            } else if(car.getBookingList() == null ){
                throw new EntityNotFoundException("Could not find any bookings for that car");
            } else {
                return BookingDTO.getDTOs(car.getBookingList());
            }
        } catch (EntityNotFoundException oo){
        throw new WebApplicationException(oo.getMessage(), 404);
        } finally {
            em.close();
        }
    }

    public BookingDTO createBooking(BookingDTO bookingDTO){
        EntityManager em = getEntityManager();
        List<WashingAssistant> washingAssistantList = new ArrayList<>();
        List<WashingAssistantDTO> washingAssistantDTOS = bookingDTO.getWashingAssistantDTOList();
        for(WashingAssistantDTO wDTO : washingAssistantDTOS){
            WashingAssistant washingAssistant = new WashingAssistant(wDTO.getName(), wDTO.getPrimarylanguage(), wDTO.getYearsofexp(), wDTO.getPriceprhour());
            washingAssistantList.add(washingAssistant);
        }
        Car car = carFacade.getCar(bookingDTO.getCarDTO().getRegistrationnumber());

        Booking booking = new Booking(bookingDTO.getDate(), bookingDTO.getDuration(), washingAssistantList, car);
        try{
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
            return new BookingDTO(booking);
        } finally {
            em.close();
        }

    }


    public BookingDTO updateBooking(BookingDTO bookingDTO){
        EntityManager em = getEntityManager();
        Booking booking = em.find(Booking.class, bookingDTO.getId());
        try{
            List<WashingAssistant> washList = new ArrayList<>();
            List<WashingAssistantDTO> washDTOList = bookingDTO.getWashingAssistantDTOList();
            for(WashingAssistantDTO washDTO : washDTOList){
                WashingAssistant washingAssistant = new WashingAssistant(washDTO.getName(), washDTO.getPrimarylanguage(), washDTO.getYearsofexp(), washDTO.getPriceprhour());
                washList.add(washingAssistant);
            }

            em.getTransaction().begin();
            booking.setDate(bookingDTO.getDate());
            booking.setDuration(bookingDTO.getDuration());
            booking.setWashingAssistantList(washList);
            em.getTransaction().commit();
            return new BookingDTO(booking);
        } finally {
            em.close();
        }
    }

    public void deleteBooking(int id) throws EntityNotFoundException{
        EntityManager em = getEntityManager();
        Booking booking = em.find(Booking.class, id);
        try{
            em.getTransaction().begin();
            em.remove(booking);
            em.getTransaction().commit();
            if (booking == null){
                throw new EntityNotFoundException("no booking with that id found");
            }
        } catch(EntityNotFoundException oo){
            throw new WebApplicationException(oo.getMessage(), 404);
        }

        finally{
            em.close();
        }

    }



}