package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum IntakeSwivelPositions {
    DEGREES0(0.85),
    DEGREES180(.29);
    private double value;

    IntakeSwivelPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
