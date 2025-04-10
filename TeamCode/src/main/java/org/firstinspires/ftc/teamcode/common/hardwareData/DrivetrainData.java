package org.firstinspires.ftc.teamcode.common.hardwareData;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public abstract class DrivetrainData {
    public double maxFastPower;
    public double maxMediumPower;
    public double maxSlowPower;

    public double wheelDiameterMM;
    public double wheelDiameterInches;
    public double wheelCircumferenceInches;
    public double wheelTicksPerInch;

    public DcMotorSimple.Direction leftFrontDirection;
    public DcMotorSimple.Direction leftBackDirection;
    public DcMotorSimple.Direction rightFrontDirection;
    public DcMotorSimple.Direction rightBackDirection;
}

