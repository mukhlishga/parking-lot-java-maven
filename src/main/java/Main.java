import utils.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        InputParser parser = new InputParser();

        switch (args.length) {
            case 0:
                while (true) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferedReader.readLine();
                        if (inputString.equalsIgnoreCase("exit")) {
                            break;
                        } else if (!inputString.isEmpty()) {
                            parser.parseInput(inputString.trim());
                        }
                    } catch (IOException e) {
                        System.out.println("Error when reading CLI input");
                        e.printStackTrace();
                    }
                }
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
