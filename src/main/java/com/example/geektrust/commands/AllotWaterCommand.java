package com.example.geektrust.commands;

import com.example.geektrust.entity.ApartmentType;
import com.example.geektrust.service.ApartmentService;

import java.util.List;

/**
 * Command for execute of allot water
 */
public class AllotWaterCommand implements ICommand {

    /**
     * ApartmentService for service calls
     */
    private ApartmentService apartmentService;

    /**
     * Initializes apartmentService
     *
     * @param apartmentService
     */
    public AllotWaterCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /**
     * Executes calls the allotWater service call
     *
     * @param data as [ALLOT_WATER, 3, 2:1]
     */
    @Override
    public void execute(List<String> data) {
        ApartmentType apartmentType = ApartmentType.getApartmentType(data.get(1));
        apartmentService.allotWater(apartmentType, data.get(2));
    }
}
