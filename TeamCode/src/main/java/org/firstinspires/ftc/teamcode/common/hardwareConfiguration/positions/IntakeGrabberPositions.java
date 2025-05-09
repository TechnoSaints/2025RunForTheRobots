package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeGrabberPositions {
    OPEN(0.64),
    MIDDLE(0.65),
    CLOSED_LOOSE(0.52),
    CLOSED_TIGHT(0.49);
    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
