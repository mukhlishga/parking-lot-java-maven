package com.parkinglot.services;

import com.parkinglot.contracts.ParkingSpot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingLot {
    private final Integer maximumSpot;
    private Integer availableSlotIndex;
    private final List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingLot(Integer maximumSpot) {
        this.maximumSpot = maximumSpot;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void park(String registrationNumber, String colour) {
        getAvailableSlotIndex();
        if (availableSlotIndex >= maximumSpot) {
            System.out.println("Sorry, parking lot is full");
        } else {
            ParkingSpot parkingSpot = new ParkingSpot(availableSlotIndex, registrationNumber, colour);
            parkingSpots.add(parkingSpot);
            System.out.println("Allocated slot number: " + parkingSpot.getIndex());
        }
    }

    public void getStatus() {
        List<ParkingSpot> listOfOrderedParkingSpots = parkingSpots.stream()
            .sorted(Comparator.comparing(ParkingSpot::getIndex))
            .collect(Collectors.toList());

        System.out.println("Slot No." + "\t" + "Registration No"  + "\t\t" + "Colour");

        for (ParkingSpot parkingSpot : listOfOrderedParkingSpots) {
            System.out.println(parkingSpot.getIndex() + "\t\t" +
                parkingSpot.getRegistrationNumber() + "\t\t" +
                parkingSpot.getColour());
        }
    }

    public void leave(Integer index) {
        parkingSpots.removeIf( obj -> Objects.equals(obj.getIndex(), index));
        System.out.println("Slot number " + index + " is free");
    }

    public String getRegistrationNumbersForCarsWithColour(String colour) {
        return parkingSpots.stream()
            .filter(o -> Objects.equals(o.getColour(), colour))
            .sorted(Comparator.comparing(ParkingSpot::getIndex))
            .map(ParkingSpot::getRegistrationNumber)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
    }

    public String getSlotNumbersForCarsWithColour(String colour) {
        return parkingSpots.stream()
            .filter(o -> Objects.equals(o.getColour(), colour))
            .sorted(Comparator.comparing(ParkingSpot::getIndex))
            .map(ParkingSpot::getIndex)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
    }

    public String getSlotNumberForRegistrationNumber(String registrationNumber) {
        return parkingSpots.stream()
            .filter(o -> Objects.equals(o.getRegistrationNumber(), registrationNumber))
            .map(ParkingSpot::getIndex)
            .map(Object::toString)
            .findFirst()
            .orElse("Not found");
    }

    public void getAvailableSlotIndex() {
        List<Integer> listOfRegisteredIndex = parkingSpots.stream()
            .map(ParkingSpot::getIndex)
            .sorted()
            .collect(Collectors.toList());

        Integer freeIndex = 0;

        if (!listOfRegisteredIndex.isEmpty()) {
            for (Integer registeredIndex : listOfRegisteredIndex) {
                if (freeIndex + 1 >= registeredIndex) {
                    freeIndex = registeredIndex;
                }
            }
        }

        availableSlotIndex = freeIndex;
    }
}
