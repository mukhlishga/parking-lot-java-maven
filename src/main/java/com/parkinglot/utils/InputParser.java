package com.parkinglot.utils;

import com.parkinglot.services.ParkingLot;

import java.io.*;
import java.util.Objects;

public class InputParser {
    private ParkingLot parkingLot = null;

    public InputParser() {
    }

    public void parseInput(String input) {
        String[] inputs = input.split(" ");
        switch (inputs.length) {
            case 1:
                if (this.parkingLot == null) {
                    System.out.println("Please create a parking lot first");
                } else if (input.equals("status")) {
                    parkingLot.getStatus();
                }
                break;
            case 2:
                if (Objects.equals(inputs[0], "create_parking_lot")) {
                    if (this.parkingLot == null) {
                        this.parkingLot = new ParkingLot(Integer.valueOf(inputs[1]));
                        System.out.println("Created a parking lot with " + Integer.valueOf(inputs[1]) + " slots");
                    } else {
                        System.out.println("Parking lot already exists");
                    }
                } else {
                    if (this.parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        if (Objects.equals(inputs[0], "leave")) {
                            parkingLot.leave(Integer.valueOf(inputs[1]));
                        } else if (Objects.equals(inputs[0], "registration_numbers_for_cars_with_colour")) {
                            System.out.println(parkingLot.getRegistrationNumbersForCarsWithColour(inputs[1]));
                        } else if (Objects.equals(inputs[0], "slot_numbers_for_cars_with_colour")) {
                            System.out.println(parkingLot.getSlotNumbersForCarsWithColour(inputs[1]));
                        } else if (Objects.equals(inputs[0], "slot_number_for_registration_number")) {
                            System.out.println(parkingLot.getSlotNumberForRegistrationNumber(inputs[1]));
                        }
                    }
                }
                break;
            case 3:
                if (this.parkingLot == null) {
                    System.out.println("Please create a parking lot first");
                } else if (Objects.equals(inputs[0], "park")) {
                    parkingLot.park(inputs[1], inputs[2]);
                }
                break;
            default:
                System.out.println("Invalid input in parser");
        }
    }

    public void parseFileInput(String filepath) {
        File inputFile = new File(filepath);
        try {
            BufferedReader br = new BufferedReader(new FileReader((inputFile)));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseInput(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Error reading the file input");
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error : File not found in the specified path");
            throw new RuntimeException(e);
        }
    }
}
