package com.example.geektrust.commands;

import com.example.geektrust.service.ApartmentService;

import java.util.List;

/**
 * Command for execute the add guests
 */
public class AddGuestsCommand implements ICommand {

    /**
     * ApartmentService for service calls
     */
    private ApartmentService apartmentService;

    /**
     * Initializes the apartmentService
     *
     * @param apartmentService
     */
    public AddGuestsCommand(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    /**
     * Calls the add guest method of service
     *
     * @param data as [ADD_GUESTS, 4]
     */
    @Override
    public void execute(List<String> data) {
        Integer noOfGuests = Integer.parseInt(data.get(1));
        apartmentService.addGuests(noOfGuests);
    }
}
