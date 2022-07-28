package com.example.geektrust.repository;

import com.example.geektrust.entity.TankerSlabs;
import com.example.geektrust.entity.WaterSupplyType;

import java.util.HashMap;
import java.util.Map;

public class WaterSupplyTypePriceData implements IDataRepository {

    /**
     * Map for storing data
     */
    private final Map<String, Double> waterSupplyTypePriceMap;

    /**
     * Initializes waterSupplyTypePriceMap
     */
    public WaterSupplyTypePriceData() {
        waterSupplyTypePriceMap = new HashMap<>();
    }

    /**
     * @param waterSupplyType
     * @return value of key waterSupplyType from map
     */
    @Override
    public double getValue(String waterSupplyType) {
        return waterSupplyTypePriceMap.get(waterSupplyType);
    }

    /**
     * Inserts data waterSupplyType, value into map
     *
     * @param waterSupplyType
     * @param value
     */
    @Override
    public void insertData(String waterSupplyType, Double value) {
        waterSupplyTypePriceMap.put(waterSupplyType, value);
    }

    /**
     * Loads data into map
     */
    @Override
    public void loadData() {
        insertData(WaterSupplyType.CORPORATION.name(), 1.0);
        insertData(WaterSupplyType.BOREWELL.name(), 1.5);
        insertData(WaterSupplyType.TANKER.name() + TankerSlabs._upTo500.name(), 2.0);
        insertData(WaterSupplyType.TANKER.name() + TankerSlabs._upTo1500.name(), 3.0);
        insertData(WaterSupplyType.TANKER.name() + TankerSlabs._upTo3000.name(), 5.0);
        insertData(WaterSupplyType.TANKER.name() + TankerSlabs._Above.name(), 8.0);
    }

    /**
     * @return size of map
     */
    @Override
    public long size() {
        return waterSupplyTypePriceMap.size();
    }
}
