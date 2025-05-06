package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerGrabberPositions {
    OPEN(0.5),
    MIDDLE(0.5),
    CLOSED_LOOSE(0.5),
    CLOSED_TIGHT(0.5);
    private double value;
    HandlerGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
