package com.example.geektrust.commands;

import com.example.geektrust.service.ApartmentService;

import java.util.List;

/**
 * Command for execute of bill
 */
public class BillCommand implements ICommand {

    /**
     * ApartmentService for service calls
     */
    private ApartmentService apartmentService;

    /**
     * Initializes for apartmentService
     *
     * @param apartmentService
     */
    public BillCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /**
     * Calls the bill method of service
     *
     * @param data as [BILL]
     */
    @Override
    public void execute(List<String> data) {
        String bill = apartmentService.bill();
        System.out.println(bill);
    }
}
