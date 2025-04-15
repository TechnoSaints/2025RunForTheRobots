package org.firstinspires.ftc.teamcode.common.hardwareConstants;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DrivetrainData {
    public double maxFastPower = 0.8;
    public double maxMediumPower = 0.5;
    public double maxSlowPower = 0.25;

    public double wheelDiameterMM = 104.0;
    public double wheelDiameterInches = wheelDiameterMM / 25.4;
    public double wheelCircumferenceInches = Math.PI * wheelDiameterInches;

    public String leftFrontMotorName = "leftFrontMotor";
    public String leftRearMotorName = "leftRearMotor";
    public String rightFrontMotorName = "rightFrontMotor";
    public String rightRearMotorName = "rightRearMotor";

    public DcMotorSimple.Direction leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
    public DcMotorSimple.Direction leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
    public DcMotorSimple.Direction rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
    public DcMotorSimple.Direction rightRearMotorDirection = DcMotorSimple.Direction.REVERSE;
}
