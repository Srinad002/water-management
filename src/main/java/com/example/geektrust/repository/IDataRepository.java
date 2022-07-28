package com.example.geektrust.repository;

/**
 * Contract for all data required to use in execution
 */
public interface IDataRepository {

    public double getValue(String t);

    public void insertData(String key, Double value);

    public void loadData();

    public long size();
}
