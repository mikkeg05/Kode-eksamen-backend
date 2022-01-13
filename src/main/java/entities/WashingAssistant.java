package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "washing_assistant")
@Entity
@NamedQuery(name = "washingAssistant.deleteAllRows", query = "DELETE from WashingAssistant")
public class WashingAssistant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String primarylanguage;
    private int yearsofexp;
    private int priceprhour;
    @ManyToMany()
    private List<Booking> bookingList;

    public WashingAssistant() {
    }

    public WashingAssistant(String name, String primarylanguage, int yearsofexp, int priceprhour, List<Booking> bookingList) {
        this.name = name;
        this.primarylanguage = primarylanguage;
        this.yearsofexp = yearsofexp;
        this.priceprhour = priceprhour;
        this.bookingList = bookingList;
    }

    public WashingAssistant(String name, String primarylanguage, int yearsofexp, int priceprhour) {
        this.name = name;
        this.primarylanguage = primarylanguage;
        this.yearsofexp = yearsofexp;
        this.priceprhour = priceprhour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimarylanguage() {
        return primarylanguage;
    }

    public void setPrimarylanguage(String primarylanguage) {
        this.primarylanguage = primarylanguage;
    }

    public int getYearsofexp() {
        return yearsofexp;
    }

    public void setYearsofexp(int yearsofexp) {
        this.yearsofexp = yearsofexp;
    }

    public int getPriceprhour() {
        return priceprhour;
    }

    public void setPriceprhour(int priceprhour) {
        this.priceprhour = priceprhour;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}