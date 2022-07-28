package com.example.geektrust.appconfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ApplicationConfigTest")
@ExtendWith(MockitoExtension.class)
public class ApplicationConfigTest {

    @InjectMocks
    ApplicationConfig applicationConfig;

    @Test
    @DisplayName("Register commands test")
    public void registerCommands() {
        applicationConfig.registerCommands();
        Assertions.assertEquals(3, applicationConfig.commandInvoker.getCommandMap().size());
    }

    @Test
    @DisplayName("Load data test")
    public void loadData() {
        applicationConfig.loadData();
        Assertions.assertEquals(2L, applicationConfig.getApartmentTypePeopleData().size());
        Assertions.assertEquals(6L, applicationConfig.getWaterSupplyTypePriceData().size());
    }
}
