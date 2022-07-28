package com.example.geektrust.appconfig;

import com.example.geektrust.commands.AddGuestsCommand;
import com.example.geektrust.commands.AllotWaterCommand;
import com.example.geektrust.commands.BillCommand;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.repository.ApartmentTypePeopleData;
import com.example.geektrust.repository.WaterSupplyTypePriceData;
import com.example.geektrust.service.ApartmentService;

/**
 * Application config for all entity initializes required for application
 */
public class ApplicationConfig {

    private final ApartmentTypePeopleData apartmentTypePeopleData = new ApartmentTypePeopleData();
    private final WaterSupplyTypePriceData waterSupplyTypePriceData = new WaterSupplyTypePriceData();
    private final ApartmentService apartmentService = new ApartmentService(waterSupplyTypePriceData, apartmentTypePeopleData);
    private final AllotWaterCommand allotWaterCommand = new AllotWaterCommand(apartmentService);
    private final AddGuestsCommand addGuestsCommand = new AddGuestsCommand(apartmentService);
    private final BillCommand billCommand = new BillCommand(apartmentService);

    CommandInvoker commandInvoker = new CommandInvoker();

    /**
     * Registers all commands to commandInvoker
     *
     * @return commandInvoker
     */
    public CommandInvoker registerCommands() {
        commandInvoker.registerCommands("ALLOT_WATER", allotWaterCommand);
        commandInvoker.registerCommands("ADD_GUESTS", addGuestsCommand);
        commandInvoker.registerCommands("BILL", billCommand);

        return commandInvoker;
    }

    /**
     * Loads data to repos
     */
    public void loadData() {
        apartmentTypePeopleData.loadData();
        waterSupplyTypePriceData.loadData();
    }

    /**
     * Getter for ApartmentTypePeopleData
     *
     * @return ApartmentTypePeopleData
     */
    public ApartmentTypePeopleData getApartmentTypePeopleData() {
        return this.apartmentTypePeopleData;
    }

    /**
     * Getter for WaterSupplyTypePriceData
     *
     * @return WaterSupplyTypePriceData
     */
    public WaterSupplyTypePriceData getWaterSupplyTypePriceData() {
        return this.waterSupplyTypePriceData;
    }

}
