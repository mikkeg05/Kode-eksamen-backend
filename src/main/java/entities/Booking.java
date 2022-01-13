package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "booking")
@Entity
@NamedQuery(name = "booking.deleteAllRows", query = "DELETE from Booking")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Date date;
    private String duration;
    @ManyToMany(mappedBy = "bookingList")
    private List<WashingAssistant> washingAssistantList;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

    public Booking( Date date, String duration, List<WashingAssistant> washingAssistantList, Car car) {
        this.date = date;
        this.duration = duration;
        this.washingAssistantList = washingAssistantList;
        this.car = car;
    }

    public Booking() {
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

    public List<WashingAssistant> getWashingAssistantList() {
        return washingAssistantList;
    }

    public void setWashingAssistantList(List<WashingAssistant> washingAssistantList) {
        this.washingAssistantList = washingAssistantList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}