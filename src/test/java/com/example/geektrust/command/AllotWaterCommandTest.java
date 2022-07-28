package com.example.geektrust.command;

import com.example.geektrust.commands.AllotWaterCommand;
import com.example.geektrust.entity.ApartmentType;
import com.example.geektrust.service.ApartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("AllotWaterCommandTest")
@ExtendWith(MockitoExtension.class)
public class AllotWaterCommandTest {

    @InjectMocks
    AllotWaterCommand allotWaterCommand;

    @Mock
    ApartmentService apartmentService;

    @DisplayName("Allot water executeTest")
    @Test
    public void executeTest() {
        allotWaterCommand.execute(Arrays.asList("ALLOT_WATER", "3", "2:1"));
        verify(apartmentService, times(1)).allotWater(ApartmentType.THREEBEDROOM, "2:1");
    }

}
