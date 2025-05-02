package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerArmPositions {
    RETRACTED(1.9375),
    EXTENDED(23.5);
    private double value;
    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
