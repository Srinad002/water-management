package com.example.geektrust.repository;

import com.example.geektrust.entity.ApartmentType;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides map to store number of people for apartment type
 */
public class ApartmentTypePeopleData implements IDataRepository {

    /**
     * Map to store data of apartmentType and number of people
     */
    private final Map<String, Double> apartmentTypePeopleMap;

    public ApartmentTypePeopleData() {
        apartmentTypePeopleMap = new HashMap<>();
    }

    /**
     * Gets the number of people of provided apartment type
     *
     * @return number of people
     */
    @Override
    public double getValue(String key) {
        return apartmentTypePeopleMap.get(key);
    }

    /**
     * Inserts the data of apartmentType, number of people into map
     */
    @Override
    public void insertData(String key, Double value) {
        apartmentTypePeopleMap.put(key, value);
    }

    /**
     * Loads the data into map
     */
    @Override
    public void loadData() {
        insertData(ApartmentType.TWOBEDROOM.name(), 3.0);
        insertData(ApartmentType.THREEBEDROOM.name(), 5.0);
    }

    /**
     * @return size of map
     */
    @Override
    public long size() {
        return apartmentTypePeopleMap.size();
    }
}
