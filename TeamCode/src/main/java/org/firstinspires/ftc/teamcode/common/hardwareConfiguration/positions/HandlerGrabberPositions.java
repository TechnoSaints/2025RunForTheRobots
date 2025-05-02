package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerGrabberPositions {
    RETRACTED(1.9375),
    EXTENDED(23.5);
    private double value;
    HandlerGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
