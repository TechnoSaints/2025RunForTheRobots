package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerWristPositions {
    SPECIMEN_WALL(0.5),
    HIGH_BASKET(.5),
    HANDOFF(.5),
    SPECIMEN_HANG(0.5);
    private double value;
    HandlerWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
