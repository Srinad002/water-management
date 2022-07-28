package com.example.geektrust.command;

import com.example.geektrust.commands.BillCommand;
import com.example.geektrust.service.ApartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("BillCommandTest")
@ExtendWith(MockitoExtension.class)
public class BillCommandTest {

    private PrintStream standardOut = System.out;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Mock
    ApartmentService apartmentService;

    @InjectMocks
    BillCommand billCommand;

    @BeforeEach
    private void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    private void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Bill command execute test")
    public void executeTest() {
        billCommand.execute(Arrays.asList("BILL"));
        verify(apartmentService, times(1)).bill();
    }
}
