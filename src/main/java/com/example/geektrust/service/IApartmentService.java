package com.example.geektrust.service;

import com.example.geektrust.entity.ApartmentType;

/**
 * Contract for apartmentService and specified methods to implement
 */
public interface IApartmentService {

    public void allotWater(ApartmentType apartmentType, String ratio);

    public void addGuests(Integer noOfGuests);

    public String bill();

}
