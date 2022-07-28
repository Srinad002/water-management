package com.example.geektrust.entity;

/**
 * ApartType enum represents the apartment of type two/three bedroom
 */
public enum ApartmentType {
    TWOBEDROOM,
    THREEBEDROOM;

    private static final String two = "2";
    private static final String three = "3";

    /**
     * Gets the apartmentType of specified value
     *
     * @param value
     * @return ApartmentType entity of provided value
     */
    public static ApartmentType getApartmentType(String value) {
        ApartmentType apartmentType = null;
        if (value.equals(two)) {
            apartmentType = TWOBEDROOM;
        } else if (value.equals(three)) {
            apartmentType = THREEBEDROOM;
        }
        return apartmentType;
    }

}
