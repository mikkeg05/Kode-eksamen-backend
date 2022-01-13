package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "car")
@Entity
@NamedQuery(name = "car.deleteAllRows", query = "DELETE from Car")
public class Car implements Serializable {
    @Id
    @Column(name = "registrationnumber", nullable = false, length = 20)
    private String registrationnumber;
    private String brand;
    private String make;
    private int year;
    @OneToMany(mappedBy = "car")
    private List<Booking> bookingList;
    @OneToOne()
    private User user;


    public Car() {
    }

    public Car(String registrationnumber, String brand, String make, int year, List<Booking> bookingList) {
        this.registrationnumber = registrationnumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.bookingList = bookingList;
    }

    public Car(String registrationnumber, String brand, String make, int year) {
        this.registrationnumber = registrationnumber;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public String getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }
}