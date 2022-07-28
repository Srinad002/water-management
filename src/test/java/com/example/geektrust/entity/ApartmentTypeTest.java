package com.example.geektrust.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ApartmentTypeTest")
@ExtendWith(MockitoExtension.class)
public class ApartmentTypeTest {

    @Test
    @DisplayName("getApartmentType test for two bed room")
    public void getApartmentType_1() {
        Assertions.assertEquals(ApartmentType.TWOBEDROOM, ApartmentType.getApartmentType("2"));
    }

    @Test
    @DisplayName("getApartmentType test for two three room")
    public void getApartmentType_2() {
        Assertions.assertEquals(ApartmentType.THREEBEDROOM, ApartmentType.getApartmentType("3"));
    }
}
