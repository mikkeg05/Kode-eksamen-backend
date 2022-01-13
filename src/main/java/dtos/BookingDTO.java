package dtos;

import entities.Booking;
import entities.Car;
import entities.WashingAssistant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookingDTO {
    private int id;
    private Date date;
    private String duration;
    private List<WashingAssistantDTO> washingAssistantDTOList;
    private CarDTO carDTO;
    private Booking booking;


    public BookingDTO(Booking booking){
        this.id = booking.getId();
        this.date = booking.getDate();
        this.duration = booking.getDuration();
        this.washingAssistantDTOList = WashingAssistantDTO.getDTOs(booking.getWashingAssistantList());
        this.carDTO = new CarDTO(booking.getCar().getRegistrationnumber(), booking.getCar().getBrand(), booking.getCar().getMake(), booking.getCar().getYear());
    }

    public BookingDTO(Date date, String duration, List<WashingAssistantDTO> washingAssistantDTOList, CarDTO carDTO) {
        this.date = date;
        this.duration = duration;
        this.washingAssistantDTOList = washingAssistantDTOList;
        this.carDTO = carDTO;
    }

    public static List<BookingDTO> getDTOs(List<Booking> bookingList){
        List<BookingDTO> bookingDTOs = new ArrayList();
        bookingList.forEach(booking->bookingDTOs.add(new BookingDTO(booking)));
        return bookingDTOs;
    }


    public List<WashingAssistantDTO> getWashingAssistantDTOList() {
        return washingAssistantDTOList;
    }

    public void setWashingAssistantDTOList(List<WashingAssistantDTO> washingAssistantDTOList) {
        this.washingAssistantDTOList = washingAssistantDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
