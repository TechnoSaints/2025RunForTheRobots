package org.firstinspires.ftc.teamcode.common.hardwareConstants;

public enum LiftPositions {
    MAX(1750),
    MIN(0),
    HIGH(1700),
    MEDIUM(700),
    LOW(275);
    private int value;
    LiftPositions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
