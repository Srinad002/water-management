package com.example.geektrust.command;

import com.example.geektrust.commands.AddGuestsCommand;
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

@DisplayName("AddGuestsCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddGuestsCommandTest {

    @Mock
    ApartmentService apartmentService;

    @InjectMocks
    AddGuestsCommand addGuestsCommand;

    @Test
    @DisplayName("Add guests execute test")
    public void executeTest() {
        addGuestsCommand.execute(Arrays.asList("ADD_GUESTS", "4"));
        verify(apartmentService, times(1)).addGuests(4);
    }

}
