package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeWristPositions {
    UP(0.67),
    MIDDLE(0.38),
    LOOK(0.32),
    DOWN(.10);
    private double value;

    IntakeWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
