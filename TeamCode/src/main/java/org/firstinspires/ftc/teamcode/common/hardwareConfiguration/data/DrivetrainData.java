package org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DrivetrainData {
    public static double maxFastPower = 0.8;
    public static double maxMediumPower = 0.5;
    public static double maxSlowPower = 0.25;

    public static double wheelDiameterMM = 104.0;
    public static double wheelDiameterInches = wheelDiameterMM / 25.4;
    public static double wheelCircumferenceInches = Math.PI * wheelDiameterInches;

    public static String leftFrontMotorName = "leftFrontDrive";
    public static String leftRearMotorName = "leftRearDrive";
    public static String rightFrontMotorName = "rightFrontDrive";
    public static String rightRearMotorName = "rightRearDrive";

    public static DcMotorSimple.Direction leftFrontMotorDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction leftRearMotorDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction rightRearMotorDirection = DcMotorSimple.Direction.FORWARD;
}
