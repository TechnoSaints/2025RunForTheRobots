package org.firstinspires.ftc.teamcode.common.hardwareConstants;

public enum IntakeGrabberPositions {
    OPEN(0.5),
    MIDDLE(0.35),
    CLOSED(0.20);
    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
