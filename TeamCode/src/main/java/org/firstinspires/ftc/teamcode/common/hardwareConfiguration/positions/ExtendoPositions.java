package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum ExtendoPositions {
    RETRACTED(1.9375),
    EXTENDED(23.5);
    //    RETRACTED(0.45),
//    EXTENDED(0.75);

    private double value;

    ExtendoPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
