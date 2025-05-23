package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeGrabberPositions {
    OPEN(0.74),
    MIDDLE(0.65),
    CLOSED_LOOSE(0.5),
    CLOSED_TIGHT(0.47);
    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
