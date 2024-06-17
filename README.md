# Parking Lot

Please see the Problem Statement for context onboarding

## Requirements

- JDK 8 or above
- Maven 2.4 or above
- Junit 4.13.2 or above

## How to run

1. Build
   ```
   ./bin/setup
   ```

2. Run program in interactive CLI
   ```
   ./bin/parking_lot
   ```
   Available commands:
   ```
   create_parking_lot <number_of_available_slots>
   park <registration_number> <colour>
   leave <slot_index>
   status
   registration_numbers_for_cars_with_colour <colour>
   slot_numbers_for_cars_with_colour <colour>
   slot_number_for_registration_number <registration_number>
   exit
   ```
4. Run program with file input
   ```
   ./bin/parking_lot inputFile.txt
   ```
