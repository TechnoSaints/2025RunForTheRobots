package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum ExtendoPositions {
    RETRACTED(0.45),
    EXTENDED(0.75);

    private double value;

    ExtendoPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
