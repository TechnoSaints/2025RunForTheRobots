package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeGrabberPositions {
    OPEN(0.58),
    MIDDLE(0.50),
    CLOSED_LOOSE(0.20),
    CLOSED_TIGHT(0.17);
    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
