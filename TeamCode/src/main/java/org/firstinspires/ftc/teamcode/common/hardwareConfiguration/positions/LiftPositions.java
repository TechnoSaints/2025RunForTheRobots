package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum LiftPositions {
    MAX(2250),
    HIGH_BUCKET(2050),
    HIGH_BUCKET_TELEOP(2050),
    SPECIMEN_HANG_SETUP(0),
    SPECIMEN_HANG(675),
    HANDOFF_SETUP(300),
    HANDOFF(135),
    SPECIMEN_WALL(0),
    MIN(0);

    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
