package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerArmPositions {
    SPECIMEN_WALL(.05),
    HIGH_BUCKET(.30),
    SPECIMEN_HANG(.51),
    HANDOFF(.86);
    private double value;
    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
