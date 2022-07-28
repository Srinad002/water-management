package com.example.geektrust.service;

import com.example.geektrust.entity.Apartment;
import com.example.geektrust.entity.ApartmentType;
import com.example.geektrust.entity.TankerSlabs;
import com.example.geektrust.entity.WaterSupplyType;
import com.example.geektrust.repository.ApartmentTypePeopleData;
import com.example.geektrust.repository.WaterSupplyTypePriceData;

import java.util.Arrays;
import java.util.List;

/**
 * Apartment service which realizes and implements all service methods
 */
public class ApartmentService implements IApartmentService {

    /**
     * Apartment entity for storing details
     */
    private Apartment apartment;

    /**
     * For accessing water supply price data
     */
    private WaterSupplyTypePriceData waterSupplyTypePriceData;

    /**
     * For accessing data of number of people an apartment holds
     */
    private ApartmentTypePeopleData apartmentTypePeopleData;

    /**
     * Water alloted per person per day
     */
    private final Double waterAllotedPerPersonPerDay = 10.0;

    /**
     * Number of days per month
     */
    private final Double noOfDaysPerMonth = 30.0;
    /**
     * List of tanker slab range
     */
    private final List<Integer> tankerSlabRange = Arrays.asList(0, 500, 1500, 3000);


    /**
     * Initializes the waterSupplyTypePriceData and apartmentTypePeopleData
     *
     * @param waterSupplyTypePriceData
     * @param apartmentTypePeopleData
     */
    public ApartmentService(WaterSupplyTypePriceData waterSupplyTypePriceData,
            ApartmentTypePeopleData apartmentTypePeopleData) {
        this.apartmentTypePeopleData = apartmentTypePeopleData;
        this.waterSupplyTypePriceData = waterSupplyTypePriceData;
    }

    /**
     * Initializes the apartment entity and assigns the apartmentType and water consumption ratio
     *
     * @param apartmentType
     * @param ratio
     */
    @Override
    public void allotWater(ApartmentType apartmentType, String ratio) {
        Integer noOfPeople = (int) apartmentTypePeopleData.getValue(apartmentType.name());
        apartment = new Apartment(apartmentType, ratio, noOfPeople);
    }

    /**
     * Updates the guests to apartment entity
     *
     * @param noOfGuests
     */
    @Override
    public void addGuests(Integer noOfGuests) {
        noOfGuests += apartment.getNoOfGuests();
        apartment.setNoOfGuests(noOfGuests);
    }

    /**
     * Calculates the total water consumption and total bill for water
     *
     * @return String of totalWaterConsumption and total amount
     */
    @Override
    public String bill() {
        double guestsWaterConsumption = calculateGuestsWaterConsumption();
        double apartmentPeopleWaterConsumption = calculateApartmentPeopleWaterConsumption();
        double totalWaterConsumption = apartmentPeopleWaterConsumption + guestsWaterConsumption;
        double totalBill = calculateGuestsWaterBill(guestsWaterConsumption) + calculateApartmentWaterBill(
                apartmentPeopleWaterConsumption);

        return (int) totalWaterConsumption + " " +  Math.round(totalBill);
    }

    /**
     * Calculates apartment's people water consumed
     *
     * @return waterConsumed
     */
    public double calculateApartmentPeopleWaterConsumption() {
        return apartment.getNoOfPeople() * waterAllotedPerPersonPerDay * noOfDaysPerMonth;
    }

    /**
     * Calculates guests water consumed
     *
     * @return waterConsumed
     */
    public double calculateGuestsWaterConsumption() {
        return apartment.getNoOfGuests() * waterAllotedPerPersonPerDay * noOfDaysPerMonth;
    }

    /**
     * Calculates guests water bill using water consumption
     *
     * @param waterConsumed
     * @return waterBill
     */
    public double calculateGuestsWaterBill(double waterConsumed) {

        double waterBill = 0;
        double waterUsed = waterConsumed;

        for (int i = 1; i < tankerSlabRange.size() && waterUsed>0; i++) {
            if (waterConsumed <= tankerSlabRange.get(i)) {
                waterBill += calculateBillForTankerSlab(waterUsed, tankerSlabRange.get(i));
            } else {
                waterBill += calculateBillForTankerSlab(tankerSlabRange.get(i) - tankerSlabRange.get(i-1),
                        tankerSlabRange.get(i));
            }
            waterUsed = waterConsumed - tankerSlabRange.get(i);
        }

        if (waterUsed > 0) {
            waterBill += waterUsed * waterSupplyTypePriceData.getValue(
                            WaterSupplyType.TANKER.name() + TankerSlabs._Above.name());
        }

//        if (waterConsumed <= 500) {
//            waterBill += calculateBillForTankerSlab(waterConsumed, TankerSlabs._upTo500);
//        } else if (waterConsumed <= 1500) {
//            waterBill += calculateBillForTankerSlab(500, TankerSlabs._upTo500);
//            waterBill += calculateBillForTankerSlab(waterConsumed-500, TankerSlabs._upTo1500);
//        } else if (waterConsumed <= 3000) {
//            waterBill += calculateBillForTankerSlab(500, TankerSlabs._upTo500);
//            waterBill += calculateBillForTankerSlab(1500-500, TankerSlabs._upTo1500);
//            waterBill += calculateBillForTankerSlab(waterConsumed-1500, TankerSlabs._upTo3000);
//        } else {
//            waterBill += calculateBillForTankerSlab(500, TankerSlabs._upTo500);
//            waterBill += calculateBillForTankerSlab(1500-500, TankerSlabs._upTo1500);
//            waterBill += calculateBillForTankerSlab(3000-1500, TankerSlabs._upTo3000);
//            waterBill += calculateBillForTankerSlab(waterConsumed-3000, TankerSlabs._Above);
//        }

        return waterBill;
    }

    /**
     * Gets the tanker slab key with given value
     *
     * @param value
     * @return tankerKey
     */
    public String tankerSlabKey(Integer value) {
        return "_upTo"+ value;
    }

    /**
     * calculates bill for that tanker slab
     *
     * @param waterConsumed
     * @param value for tanker slab key
     * @return bill
     */
    public double calculateBillForTankerSlab(double waterConsumed, Integer value) {
        return waterConsumed * waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name() + tankerSlabKey(value));
    }

    /**
     * Calculates apartment's water bill by parsing the water consumption ratio
     *
     * @param waterConsumed
     * @return apartmentWaterBill
     */
    public double calculateApartmentWaterBill(double waterConsumed) {
        double[] parsedRatio = parseRatio();
        double corporationWaterConsumed = (parsedRatio[0] * waterConsumed) / parsedRatio[2];
        double boreWellWaterConsumed = (parsedRatio[1] * waterConsumed) / parsedRatio[2];
        return calculateCorporationWaterBill(corporationWaterConsumed) + calculateBoreWellWaterBill(
                boreWellWaterConsumed);
    }

    /**
     * Calculates the corporation water bill of consumed water
     *
     * @param waterConsumed
     * @return waterBill
     */
    public double calculateCorporationWaterBill(double waterConsumed) {
        return Math.round(waterConsumed) * waterSupplyTypePriceData.getValue(WaterSupplyType.CORPORATION.name());
    }

    /**
     * Calculates the bore well water bill of consumed water
     *
     * @param waterConsumed
     * @return waterBill
     */
    public double calculateBoreWellWaterBill(double waterConsumed) {
        return Math.round(waterConsumed) * waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name());
    }

    /**
     * Parse the water consumption ratio
     *
     * @return array of pared ratio
     */
    public double[] parseRatio() {
        double[] paredRatio = new double[3];
        String[] ratio = apartment.getWaterConsumptionRatio().split(":");
        int a = Integer.parseInt(ratio[0]);
        int b = Integer.parseInt(ratio[1]);
        paredRatio[0] = a;
        paredRatio[1] = b;
        paredRatio[2] = a + b;
        return paredRatio;
    }


    public Apartment getApartment() {
        return this.apartment;
    }

    public WaterSupplyTypePriceData getWaterSupplyTypePriceData() {
        return waterSupplyTypePriceData;
    }

    public ApartmentTypePeopleData getApartmentTypePeopleData() {
        return apartmentTypePeopleData;
    }

    public Double getWaterAllotedPerPersonPerDay() {
        return waterAllotedPerPersonPerDay;
    }

    public Double getNoOfDaysPerMonth() {
        return noOfDaysPerMonth;
    }

    public List<Integer> getTankerSlabRange() {
        return tankerSlabRange;
    }
}
