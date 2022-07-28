package com.example.geektrust.entity;

/**
 * Apartment entity contains apartmentType and noOfGuests
 */
public class Apartment {

    /**
     * ApartmentType of the apartment
     */
    private ApartmentType apartmentType;

    /**
     * Water consumption ratio of apartment
     */
    private String waterConsumptionRatio;

    /**
     * Number of guests came to each apartment
     */
    private Integer noOfGuests = 0;

    /**
     * Number of people apartment holds
     */
    private Integer noOfPeople;

    public Apartment(ApartmentType apartmentType, String waterConsumptionRatio, Integer noOfPeople) {
        this.apartmentType = apartmentType;
        this.waterConsumptionRatio = waterConsumptionRatio;
        this.noOfPeople = noOfPeople;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Integer getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(Integer noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public Integer getNoOfPeople() {
        return noOfPeople;
    }

    public String getWaterConsumptionRatio() {
        return waterConsumptionRatio;
    }

    public void setWaterConsumptionRatio(String waterConsumptionRatio) {
        this.waterConsumptionRatio = waterConsumptionRatio;
    }
}
