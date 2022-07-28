package com.example.geektrust.service;

import com.example.geektrust.entity.Apartment;
import com.example.geektrust.entity.ApartmentType;
import com.example.geektrust.entity.TankerSlabs;
import com.example.geektrust.entity.WaterSupplyType;
import com.example.geektrust.repository.ApartmentTypePeopleData;
import com.example.geektrust.repository.WaterSupplyTypePriceData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@DisplayName("ApartmentServiceTest")
@ExtendWith(MockitoExtension.class)
public class ApartmentServiceTest {

    @InjectMocks
    ApartmentService apartmentService;

    @Mock
    Apartment apartment;

    @Mock
    WaterSupplyTypePriceData waterSupplyTypePriceData;

    @Mock
    ApartmentTypePeopleData apartmentTypePeopleData;

    @Test
    @DisplayName("allotWater - two bed room")
    public void allotWater_1() {
        when(apartmentTypePeopleData.getValue(ApartmentType.TWOBEDROOM.name())).thenReturn(3.0);

        apartmentService.allotWater(ApartmentType.TWOBEDROOM, "2:3");

        Assertions.assertEquals(ApartmentType.TWOBEDROOM, apartmentService.getApartment().getApartmentType());
        Assertions.assertEquals("2:3", apartmentService.getApartment().getWaterConsumptionRatio());
        Assertions.assertEquals(3, apartmentService.getApartment().getNoOfPeople().intValue());

        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.TWOBEDROOM.name());
    }


    @Test
    @DisplayName("allotWater - three bed room")
    public void allotWater_2() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "6:3");

        Assertions.assertEquals(ApartmentType.THREEBEDROOM, apartmentService.getApartment().getApartmentType());
        Assertions.assertEquals("6:3", apartmentService.getApartment().getWaterConsumptionRatio());
        Assertions.assertEquals(5, apartmentService.getApartment().getNoOfPeople().intValue());

        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.THREEBEDROOM.name());
    }

    @Test
    @DisplayName("addGuests for single time")
    public void addGuests_1() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "6:3");
        apartmentService.addGuests(2);

        Assertions.assertEquals(2, apartmentService.getApartment().getNoOfGuests().intValue());
        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.THREEBEDROOM.name());
    }

    @Test
    @DisplayName("addGuests for two or more time")
    public void addGuests_2() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "6:3");
        apartmentService.addGuests(2);
        apartmentService.addGuests(3);
        apartmentService.addGuests(3);

        Assertions.assertEquals(8, apartmentService.getApartment().getNoOfGuests().intValue());
        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.THREEBEDROOM.name());
    }

    @DisplayName("calculateApartmentPeopleWaterConsumption_1")
    @Test
    public void calculateApartmentPeopleWaterConsumption_1() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "5:3");
        Assertions.assertEquals(5*10*30, apartmentService.calculateApartmentPeopleWaterConsumption());

        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.THREEBEDROOM.name());
    }

    @DisplayName("calculateApartmentPeopleWaterConsumption_2")
    @Test
    public void calculateApartmentPeopleWaterConsumption_2() {
        when(apartmentTypePeopleData.getValue(ApartmentType.TWOBEDROOM.name())).thenReturn(3.0);

        apartmentService.allotWater(ApartmentType.TWOBEDROOM, "5:3");
        Assertions.assertEquals(3*10*30, apartmentService.calculateApartmentPeopleWaterConsumption());

        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.TWOBEDROOM.name());
    }

    @DisplayName("calculateGuestsWaterConsumption_1")
    @Test
    public void calculateGuestsWaterConsumption_1() {
        when(apartmentTypePeopleData.getValue(ApartmentType.TWOBEDROOM.name())).thenReturn(3.0);

        apartmentService.allotWater(ApartmentType.TWOBEDROOM, "5:3");
        apartmentService.addGuests(4);
        apartmentService.addGuests(3);

        Assertions.assertEquals(7*10*30, apartmentService.calculateGuestsWaterConsumption());

        Assertions.assertEquals(7, apartmentService.getApartment().getNoOfGuests().intValue());
        verify(apartmentTypePeopleData, times(1)).getValue(ApartmentType.TWOBEDROOM.name());
    }

    @DisplayName("calculateCorporationWaterBill")
    @Test
    public void calculateCorporationWaterBill() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.CORPORATION.name())).thenReturn(1.0);
        Assertions.assertEquals( 200 * 1.0, apartmentService.calculateCorporationWaterBill(200));
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.CORPORATION.name());
    }

    @DisplayName("calculateBoreWellWaterBill")
    @Test
    public void calculateBoreWellWaterBill() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name())).thenReturn(1.5);
        Assertions.assertEquals( 250 * 1.5, apartmentService.calculateBoreWellWaterBill(250));
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.BOREWELL.name());
    }

    @DisplayName("parseRatio")
    @Test
    public void parseRatio() {
        double[] expected = new double[] {7, 3, 10};

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "7:3");
        double[] actual = apartmentService.parseRatio();

        Assertions.assertEquals(expected[0], actual[0]);
        Assertions.assertEquals(expected[1], actual[1]);
        Assertions.assertEquals(expected[2], actual[2]);
    }

    @DisplayName("calculateApartmentWaterBill_1")
    @Test
    public void calculateApartmentWaterBill_1() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.CORPORATION.name())).thenReturn(1.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name())).thenReturn(1.5);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "7:3");
        double waterConsumed = apartmentService.calculateApartmentPeopleWaterConsumption();
        Assertions.assertEquals(1725, apartmentService.calculateApartmentWaterBill(waterConsumed));
    }

    @DisplayName("calculateApartmentWaterBill_2")
    @Test
    public void calculateApartmentWaterBill_2() {
        when(apartmentTypePeopleData.getValue(ApartmentType.TWOBEDROOM.name())).thenReturn(3.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.CORPORATION.name())).thenReturn(1.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name())).thenReturn(1.5);

        apartmentService.allotWater(ApartmentType.TWOBEDROOM, "2:3");
        double waterConsumed = apartmentService.calculateApartmentPeopleWaterConsumption();
        Assertions.assertEquals(1170, apartmentService.calculateApartmentWaterBill(waterConsumed));
    }

    @DisplayName("calculateBillForTankerSlab_1")
    @Test
    public void calculateBillForTankerSlab() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
        Assertions.assertEquals(600, apartmentService.calculateBillForTankerSlab(200, 1500));
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name());
    }

    @DisplayName("tankerSlabKey")
    @Test
    public void tankerSlabKey() {
        Assertions.assertEquals("_upTo1500", apartmentService.tankerSlabKey(1500));
    }

    @DisplayName("calculateGuestsWaterBill_1")
    @Test
    public void calculateGuestsWaterBill_1() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name())).thenReturn(2.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name())).thenReturn(5.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name())).thenReturn(8.0);

        Assertions.assertEquals(4000, apartmentService.calculateGuestsWaterBill(1500));

        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name());
    }

    @DisplayName("calculateGuestsWaterBill_2")
    @Test
    public void calculateGuestsWaterBill_2() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name())).thenReturn(2.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name())).thenReturn(5.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name())).thenReturn(8.0);

        Assertions.assertEquals(5500, apartmentService.calculateGuestsWaterBill(1800));

        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name());
    }

    @DisplayName("calculateGuestsWaterBill_3")
    @Test
    public void calculateGuestsWaterBill_3() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name())).thenReturn(2.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name())).thenReturn(5.0);
//        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name())).thenReturn(8.0);

        Assertions.assertEquals(600, apartmentService.calculateGuestsWaterBill(300));

        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name());
//        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name());
    }

    @DisplayName("calculateGuestsWaterBill_4")
    @Test
    public void calculateGuestsWaterBill_4() {
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name())).thenReturn(2.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name())).thenReturn(5.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name())).thenReturn(8.0);

        Assertions.assertEquals(16300, apartmentService.calculateGuestsWaterBill(3600));

        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name());
        verify(waterSupplyTypePriceData, times(1)).getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._Above.name());
    }

    @DisplayName("Test")
    @Test
    public void test() {
        when(apartmentTypePeopleData.getValue(ApartmentType.THREEBEDROOM.name())).thenReturn(5.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.CORPORATION.name())).thenReturn(1.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.BOREWELL.name())).thenReturn(1.5);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo500.name())).thenReturn(2.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo1500.name())).thenReturn(3.0);
        when(waterSupplyTypePriceData.getValue(WaterSupplyType.TANKER.name()+ TankerSlabs._upTo3000.name())).thenReturn(5.0);

        apartmentService.allotWater(ApartmentType.THREEBEDROOM, "5:4");
        apartmentService.addGuests(3);
        apartmentService.addGuests(5);
        Assertions.assertEquals("3900 10334", apartmentService.bill());
    }

}
