package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerArmPositions {
    SPECIMEN_WALL(.07),
    HIGH_BASKET(.30),
    SPECIMEN_HANG(.78),
    HANDOFF(.86);
    private double value;
    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
