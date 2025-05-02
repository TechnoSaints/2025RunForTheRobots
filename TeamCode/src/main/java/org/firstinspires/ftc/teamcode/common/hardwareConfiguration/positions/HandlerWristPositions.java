package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerWristPositions {
    RETRACTED(1.9375),
    EXTENDED(23.5);
    private double value;
    HandlerWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
