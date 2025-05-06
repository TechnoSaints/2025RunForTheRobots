package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum LiftPositions {
    MAX(2000),
    MIN(0),
    SPECIMEN_WALL(500),
    HIGH_BASKET(500),
    HANDOFF(500),
    SPECIMEN_HANG(500);
    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
