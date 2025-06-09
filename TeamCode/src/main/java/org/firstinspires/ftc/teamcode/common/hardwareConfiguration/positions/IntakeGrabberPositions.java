package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeGrabberPositions {
    OPEN(0.74),
    MIDDLE(0.65),
    SWEEP(0.63),
    CLOSED_LOOSE(0.54),
    CLOSED_TIGHT(0.52);
    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
