package contracts;

public class ParkingSpot {
    private final Integer index;
    private final String registrationNumber;
    private final String colour;

    public ParkingSpot (int index, String registrationNumber, String colour) {
        this.index = index;
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

    public Integer getIndex() {
        return this.index + 1;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public String getColour() {
        return colour;
    }
}
