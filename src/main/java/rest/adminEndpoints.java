package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookingDTO;
import dtos.WashingAssistantDTO;
import facades.BookingFacade;
import facades.WashingAssistantFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path("/admin")
public class adminEndpoints {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WashingAssistantFacade ASSISTANT_FACADE = WashingAssistantFacade.getWashingAssistantFacade(EMF);
    private static final BookingFacade BOOKING_FACADE = BookingFacade.getBookingFacade(EMF);

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("/wash")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String createWashingAssistant(String washingAssistant){
        WashingAssistantDTO washingAssistantDTO = gson.fromJson(washingAssistant, WashingAssistantDTO.class);
        System.out.println(gson.toJson(washingAssistantDTO));
        ASSISTANT_FACADE.createWashingAssistant(washingAssistantDTO);

        return ("Washing Assistant successfully created");
    }

    @Path("/updatewash")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String updateWash(String bookingDTO){
        BookingDTO bookingDTO1 = gson.fromJson(bookingDTO, BookingDTO.class);
        BOOKING_FACADE.updateBooking(bookingDTO1);
        return ("Booking succesfully updated");
    }

    @Path("/deletebooking/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String deleteBooking(@PathParam("id")int id){

        BOOKING_FACADE.deleteBooking(id);
        return ("booking successfully deleted");
    }
}
