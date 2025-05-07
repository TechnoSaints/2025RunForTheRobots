package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum ExtendoPositions {
//    RETRACTED(1.9375), // Actual value
    RETRACTED(3.5),  // Temp value
    EXTENDED(23.5);
    private double value;
    ExtendoPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
