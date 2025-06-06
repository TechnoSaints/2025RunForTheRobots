package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerWristPositions {
    SPECIMEN_WALL(0.45),
    HIGH_BUCKET(.61),
    HANDOFF_SETUP(.42),
    HANDOFF(.45),
    SPECIMEN_HANG(0.44);
    private double value;
    HandlerWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
