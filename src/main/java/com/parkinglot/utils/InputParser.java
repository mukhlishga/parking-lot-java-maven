package com.parkinglot.utils;

import com.parkinglot.services.ParkingLot;

import java.io.*;

public class InputParser {
    private ParkingLot parkingLot = null;

    public InputParser() {
    }

    public void parseCliInput() {
        while (true) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String inputString = bufferedReader.readLine();
                if (inputString.equalsIgnoreCase("exit")) {
                    break;
                } else if (!inputString.isEmpty()) {
                    parseInput(inputString.trim());
                }
            } catch (IOException e) {
                System.out.println("Error when reading CLI input");
                e.printStackTrace();
            }
        }
    }

    public void parseInput(String input) {
        String[] inputs = input.split(" ");

        switch (inputs.length) {
            case 1:
                if (parkingLot == null) {
                    System.out.println("Please create a parking lot first");
                } else if (inputs[0].equalsIgnoreCase("status")) {
                    parkingLot.getStatus();
                }
                break;
            case 2:
                if (inputs[0].equalsIgnoreCase("create_parking_lot")) {
                    if (parkingLot == null) {
                        parkingLot = new ParkingLot(Integer.valueOf(inputs[1]));
                        System.out.println("Created a parking lot with " + Integer.valueOf(inputs[1]) + " slots");
                    } else {
                        System.out.println("Parking lot already exists");
                    }
                } else {
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        switch (inputs[0]) {
                            case "leave":
                                parkingLot.leave(Integer.valueOf(inputs[1]));
                                break;
                            case "registration_numbers_for_cars_with_colour":
                                System.out.println(parkingLot.getRegistrationNumbersForCarsWithColour(inputs[1]));
                                break;
                            case "slot_numbers_for_cars_with_colour":
                                System.out.println(parkingLot.getSlotNumbersForCarsWithColour(inputs[1]));
                                break;
                            case "slot_number_for_registration_number":
                                System.out.println(parkingLot.getSlotNumberForRegistrationNumber(inputs[1]));
                                break;
                        }
                    }
                }
                break;
            case 3:
                if (parkingLot == null) {
                    System.out.println("Please create a parking lot first");
                } else if (inputs[0].equalsIgnoreCase("park")) {
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
