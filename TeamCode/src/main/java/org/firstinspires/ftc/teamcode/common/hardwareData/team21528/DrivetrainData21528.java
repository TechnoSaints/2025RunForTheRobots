package org.firstinspires.ftc.teamcode.common.hardwareData.team21528;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardwareData.DrivetrainData;

public class DrivetrainData21528 extends DrivetrainData {
    public DrivetrainData21528() {
        maxFastPower = 0.8;
        maxMediumPower = 0.5;
        maxSlowPower = 0.25;

        wheelDiameterMM = 104.0;
        wheelDiameterInches = wheelDiameterMM / 25.4;
        wheelCircumferenceInches = Math.PI * wheelDiameterInches;

        leftFrontDirection = DcMotorSimple.Direction.REVERSE;
        leftBackDirection = DcMotorSimple.Direction.REVERSE;
        rightFrontDirection = DcMotorSimple.Direction.FORWARD;
        rightBackDirection = DcMotorSimple.Direction.REVERSE;
    }
}
