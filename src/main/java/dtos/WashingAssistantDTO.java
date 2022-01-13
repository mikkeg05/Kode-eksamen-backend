package dtos;


import entities.RenameMe;
import entities.WashingAssistant;

import java.util.ArrayList;
import java.util.List;

public class WashingAssistantDTO {

    private String name;
    private String primarylanguage;
    private int yearsofexp;
    private int priceprhour;
    private WashingAssistant washingAssistant;
    private List<BookingDTO> bookingDTOList;


    public WashingAssistantDTO(WashingAssistant washingAssistant){
        this.name = washingAssistant.getName();
        this.primarylanguage = washingAssistant.getPrimarylanguage();
        this.yearsofexp = washingAssistant.getYearsofexp();
        this.priceprhour = washingAssistant.getPriceprhour();
    }



    public WashingAssistantDTO(String name, String primarylanguage, int yearsofexp, int priceprhour) {
        this.name = name;
        this.primarylanguage = primarylanguage;
        this.yearsofexp = yearsofexp;
        this.priceprhour = priceprhour;
    }


    public static List<WashingAssistantDTO> getDTOs(List<WashingAssistant> washList){
        List<WashingAssistantDTO> washDTOs = new ArrayList();
        washList.forEach(wash->washDTOs.add(new WashingAssistantDTO(wash)));
        return washDTOs;
    }

    public List<BookingDTO> getBookingDTOList() {
        return bookingDTOList;
    }

    public void setBookingDTOList(List<BookingDTO> bookingDTOList) {
        this.bookingDTOList = bookingDTOList;
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
}
