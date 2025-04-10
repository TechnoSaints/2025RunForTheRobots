package org.firstinspires.ftc.teamcode.common.hardwareData;

public class GoBilda60DcMotorData extends MotorData{

    public GoBilda60DcMotorData() {
        ticksPerMotorRev = 28;
        gearRatio = 99.5;
        ticksPerGearboxRev = gearRatio * ticksPerMotorRev;
        maxMotorRpm = 5900;
        maxMotorRps = maxMotorRpm / 60.0;
        maxTicksPerSec = maxMotorRps * ticksPerMotorRev;
    }
}
