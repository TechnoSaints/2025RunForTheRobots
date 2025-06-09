package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerWristPositions {
    SPECIMEN_WALL(0.44),
    HIGH_BUCKET(.61),
    HANDOFF_SETUP(.42),
    SUB_PARKING(0.48),
    HANDOFF(.47),
    UP_SPECIMEN(0.35),
    SPECIMEN_HANG(0.59);
    private double value;
    HandlerWristPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
