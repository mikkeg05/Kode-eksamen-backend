package dtos;

import entities.Car;

import java.util.List;

public class CarDTO {
    private String registrationnumber;
    private String brand;
    private String make;
    private int year;
    private Car car;
    private List<BookingDTO> bookingDTOList;
    private UserDTO userDTO;


    public CarDTO(Car car) {
        this.registrationnumber = car.getRegistrationnumber();
        this.brand = car.getBrand();
        this.make = car.getMake();
        this.year = car.getYear();
        this.bookingDTOList = BookingDTO.getDTOs(car.getBookingList());
        this.userDTO = new UserDTO(car.getUser().getUserName());
    }

    public CarDTO(String registrationnumber, String brand, String make, int year) {
        this.registrationnumber = registrationnumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }



    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<BookingDTO> getBookingDTOList() {
        return bookingDTOList;
    }

    public void setBookingDTOList(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
    }

    public String getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
