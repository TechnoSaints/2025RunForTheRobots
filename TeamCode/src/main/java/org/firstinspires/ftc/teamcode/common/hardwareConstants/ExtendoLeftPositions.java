package org.firstinspires.ftc.teamcode.common.hardwareConstants;

public enum ExtendoLeftPositions {
    RETRACTED(0.45),
    EXTENDED(0.75);

    private double value;

    ExtendoLeftPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
