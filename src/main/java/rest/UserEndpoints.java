package rest;
import dtos.BookingDTO;
import dtos.CarDTO;
import facades.BookingFacade;
import facades.CarFacade;
import facades.UserFacade;
import facades.WashingAssistantFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

@Path("/user")
public class UserEndpoints {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WashingAssistantFacade ASSISTANT_FACADE = WashingAssistantFacade.getWashingAssistantFacade(EMF);
    private static final BookingFacade BOOKING_FACADE = BookingFacade.getBookingFacade(EMF);
    private static final CarFacade CAR_FACADE = CarFacade.getCarFacade(EMF);
    private static final UserFacade USER_FACADE =  UserFacade.getUserFacade(EMF);


    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("/allwash")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getWash(){
        return(gson.toJson(ASSISTANT_FACADE.getAll()));
    }


    @Path("/carbookings/{car}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String getBookings(@PathParam("car") String car){
        List<BookingDTO> bookingDTOList = BOOKING_FACADE.getByCar(car);
        return gson.toJson(bookingDTOList);
    }

    @Path("/createcar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public String createCar(String car) {
        CarDTO carDTO = gson.fromJson(car, CarDTO.class);
        System.out.println(gson.toJson(carDTO));
        CAR_FACADE.createCar(carDTO);
        return("Car succesfully created");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getcar/{username}")
    @RolesAllowed("user")
    public String getFromUser(@PathParam("username")String username) {
        CarDTO carDTO = CAR_FACADE.getByUser(username);
        return gson.toJson(carDTO);

    }

    @Path("/createbooking")
    @POST
    @Produces
    @Consumes
    @RolesAllowed("user")
    public String createBooking(String booking){
        BookingDTO bookingDTO = gson.fromJson(booking, BookingDTO.class);
        BOOKING_FACADE.createBooking(bookingDTO);
        return("Booking successfully created");
    }




}