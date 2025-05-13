package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerGrabberPositions {
    OPEN(0.70),
    MIDDLE(0.65),
    CLOSED_LOOSE(0.6),
    CLOSED_TIGHT(0.59);
    private double value;
    HandlerGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
