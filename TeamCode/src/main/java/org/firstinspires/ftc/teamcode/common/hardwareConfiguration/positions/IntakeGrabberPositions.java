package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeGrabberPositions {
    //    OPEN(0.74),
    OPEN(0.90),
    MIDDLE(0.65),
    SWEEP(0.63),
    //  CLOSED_LOOSE(0.54),
    CLOSED_LOOSE(0.67),
    //    CLOSED_TIGHT(0.52);
    CLOSED_TIGHT(0.65);

    private double value;

    IntakeGrabberPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
