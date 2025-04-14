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

    public String leftFrontMotorName;
    public String leftRearMotorName;
    public String rightFrontMotorName;
    public String rightRearMotorName;

    public DcMotorSimple.Direction leftFrontMotorDirection;
    public DcMotorSimple.Direction leftRearMotorDirection;
    public DcMotorSimple.Direction rightFrontMotorDirection;
    public DcMotorSimple.Direction rightRearMotorDirection;
}

