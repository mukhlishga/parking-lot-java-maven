package com.parkinglot;

import com.parkinglot.utils.InputParser;

public class Main {
    public static void main(String[] args) {
        InputParser parser = new InputParser();

        switch (args.length) {
            case 0:
                parser.parseCliInput();
                break;
            case 1:
                parser.parseFileInput(args[0]);
                break;
            default:
                System.out.println("Invalid input in main");
                break;
        }
    }
}
