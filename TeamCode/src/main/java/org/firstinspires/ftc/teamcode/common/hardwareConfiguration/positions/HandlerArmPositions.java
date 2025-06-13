package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions;

public enum HandlerArmPositions {
    SPECIMEN_WALL(.06),
    HIGH_BUCKET(.30),
    TOP(.45),
    SPECIMEN_HANG(.60),
    SPECIMEN_SLAP_UP(.57),
    SUB_PARKING(.61),
    AUTO_START(.86),
    HANDOFF(.90);
    private double value;
    HandlerArmPositions(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
