package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum ExtendoPositions {
    RETRACTED(4.5),
    AUTO_INTAKING(14),
    AUTO_SHORT(10),
    EXTENDED(23.5);
    private double value;
    ExtendoPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
