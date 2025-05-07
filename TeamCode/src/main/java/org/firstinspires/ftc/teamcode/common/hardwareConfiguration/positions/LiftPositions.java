package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum LiftPositions {
    MAX(2150),
    HIGH_BASKET(2150),
    SPECIMEN_HANG(850),
    SPECIMEN_WALL(0),
    HANDOFF(0),
    MIN(0);

    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
