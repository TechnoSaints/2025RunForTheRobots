package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum LiftPositions {
    MAX(2100),
    HIGH_BUCKET(2050),
    SPECIMEN_HANG(850),
    SPECIMEN_WALL(0),
    HANDOFF_SETUP(150),
    HANDOFF(150),
    MIN(0);

    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
