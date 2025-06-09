package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum LiftPositions {
    MAX(2100),
    HIGH_BUCKET(2050),
    SPECIMEN_HANG_SETUP(0),
    SPECIMEN_HANG(675),
    HANDOFF_SETUP(350),
    HANDOFF(125),
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
