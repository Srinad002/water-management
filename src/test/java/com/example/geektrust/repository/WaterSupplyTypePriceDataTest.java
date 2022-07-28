package com.example.geektrust.repository;

import com.example.geektrust.entity.TankerSlabs;
import com.example.geektrust.entity.WaterSupplyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("WaterSupplyTypePriceData")
@ExtendWith(MockitoExtension.class)
public class WaterSupplyTypePriceDataTest {

    @InjectMocks
    WaterSupplyTypePriceData waterSupplyTypePriceData;

    @DisplayName("waterSupplyTypePriceData loadData test")
    @Test
    public void loadData() {
        waterSupplyTypePriceData.loadData();
        Assertions.assertEquals(6L, waterSupplyTypePriceData.size());
    }

    @DisplayName("waterSupplyTypePriceData getValue")
    @Test
    public void getValue() {
        waterSupplyTypePriceData.loadData();
        Assertions.assertEquals(1.5, waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name()));
    }

    @DisplayName("waterSupplyTypePriceData insertData")
    @Test
    public void insertData() {
        waterSupplyTypePriceData.insertData(WaterSupplyType.TANKER.name() + TankerSlabs._Above.name(), 8.0);
        Assertions.assertEquals(8.0,
                waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name() + TankerSlabs._Above.name()));
    }
}
