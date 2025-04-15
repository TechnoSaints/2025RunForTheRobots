package org.firstinspires.ftc.teamcode.common.hardwareConstants;

public enum ExtendoRightPositions {
    RETRACTED(0.51),
    EXTENDED(0.2);

    private double value;

    ExtendoRightPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
