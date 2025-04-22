package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeWristPositions {
    UP(0.735),
    MIDDLE(0.46),
    LOOK(0.32),
    DOWN(.18);
    private double value;

    IntakeWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
