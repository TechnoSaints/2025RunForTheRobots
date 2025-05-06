package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerArmPositions {
    SPECIMEN_WALL(0.1),
    HIGH_BASKET(.33),
    HANDOFF(.78),
    SPECIMEN_HANG(0.65);
    private double value;
    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
