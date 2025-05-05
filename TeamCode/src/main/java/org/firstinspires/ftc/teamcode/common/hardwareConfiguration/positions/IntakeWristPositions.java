package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeWristPositions {
    UP(0.64),
    MIDDLE(0.35),
    LOOK(0.29),
    DOWN(.07);
    private double value;

    IntakeWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
