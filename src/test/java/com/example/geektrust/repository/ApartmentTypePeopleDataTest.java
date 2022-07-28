package com.example.geektrust.repository;

import com.example.geektrust.entity.ApartmentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ApartmentTypePeopleDataTest")
@ExtendWith(MockitoExtension.class)
public class ApartmentTypePeopleDataTest {

    @InjectMocks
    ApartmentTypePeopleData apartmentTypePeopleData;

    @DisplayName("apartmentTypePeopleData loadData test")
    @Test
    public void loadData() {
        apartmentTypePeopleData.loadData();
        Assertions.assertEquals(2, apartmentTypePeopleData.size());
    }

    @DisplayName("apartmentTypePeopleData getValue")
    @Test
    public void getValue() {
        apartmentTypePeopleData.loadData();
        Assertions.assertEquals(3.0, apartmentTypePeopleData.getValue(ApartmentType.TWOBEDROOM.name()));
    }

    @DisplayName("apartmentTypePeopleData insertData")
    @Test
    public void insertData() {
        apartmentTypePeopleData.insertData(ApartmentType.THREEBEDROOM.name(), 3.0);
        Assertions.assertEquals(3.0, apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name()));
    }

}
