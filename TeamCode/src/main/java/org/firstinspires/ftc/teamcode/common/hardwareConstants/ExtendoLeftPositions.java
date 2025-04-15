package org.firstinspires.ftc.teamcode.common.hardwareConstants;

public enum ExtendoLeftPositions {
    RETRACTED(0.49),
    EXTENDED(0.8);

    private double value;

    ExtendoLeftPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
