package services;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    public static final Integer MAXIMUM_SPOT = 3;

    @BeforeClass
    public static void setupParkingLot() {
    }

    @Test
    public void parkShouldReturnSuccess() {
        ParkingLot parkingLot = new ParkingLot(MAXIMUM_SPOT);

        parkingLot.park("KA-01-HH-1234", "White");

        assertEquals(1, parkingLot.getParkingSpots().get(0).getIndex());
        assertEquals("KA-01-HH-1234", parkingLot.getParkingSpots().get(0).getRegistrationNumber());
        assertEquals("White", parkingLot.getParkingSpots().get(0).getColour());
    }

    @Test
    public void leaveShouldReturnSuccess() {
        ParkingLot parkingLot = new ParkingLot(MAXIMUM_SPOT);

        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.leave(1);
        parkingLot.park("KA-01-BB-0001", "Black");

        assertEquals(1, parkingLot.getParkingSpots().get(0).getIndex());
        assertEquals("KA-01-BB-0001", parkingLot.getParkingSpots().get(0).getRegistrationNumber());
        assertEquals("Black", parkingLot.getParkingSpots().get(0).getColour());
    }

    @Test
    public void getRegistrationNumbersForCarsWithColourShouldReturnSuccess() {
        ParkingLot parkingLot = new ParkingLot(MAXIMUM_SPOT);

        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-HH-9999", "White");

        assertEquals("KA-01-HH-1234, KA-01-HH-9999", parkingLot.getRegistrationNumbersForCarsWithColour("White"));
        assertEquals("KA-01-BB-0001", parkingLot.getRegistrationNumbersForCarsWithColour("Black"));
    }

    @Test
    public void getSlotNumbersForCarsWithColourShouldReturnSuccess() {
        ParkingLot parkingLot = new ParkingLot(MAXIMUM_SPOT);

        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-HH-9999", "White");

        assertEquals("1, 3", parkingLot.getSlotNumbersForCarsWithColour("White"));
        assertEquals("2", parkingLot.getSlotNumbersForCarsWithColour("Black"));
    }

    @Test
    public void getSlotNumberForRegistrationNumberShouldReturnSuccess() {
        ParkingLot parkingLot = new ParkingLot(MAXIMUM_SPOT);

        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-BB-0001", "Black");
        parkingLot.park("KA-01-HH-9999", "White");

        assertEquals("2", parkingLot.getSlotNumberForRegistrationNumber("KA-01-BB-0001"));
        assertEquals("Not found", parkingLot.getSlotNumberForRegistrationNumber("RANDOM"));
    }
}
