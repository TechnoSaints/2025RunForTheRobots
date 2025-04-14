package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.util.CustomFilteredPIDFCoefficients;
import com.pedropathing.util.CustomPIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.common.hardwareData.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.DrivetrainData21528;

public class FConstants {
    static {
        DrivetrainData drivetrainData = new DrivetrainData21528();
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = drivetrainData.leftFrontMotorName;
        FollowerConstants.leftRearMotorName = drivetrainData.leftRearMotorName;
        FollowerConstants.rightFrontMotorName = drivetrainData.rightFrontMotorName;
        FollowerConstants.rightRearMotorName = drivetrainData.rightRearMotorName;

        FollowerConstants.leftFrontMotorDirection = drivetrainData.leftFrontMotorDirection;
        FollowerConstants.leftRearMotorDirection = drivetrainData.leftRearMotorDirection;
        FollowerConstants.rightFrontMotorDirection = drivetrainData.rightFrontMotorDirection;
        FollowerConstants.rightRearMotorDirection = drivetrainData.rightRearMotorDirection;

        // mass in kg
        FollowerConstants.mass = 9.75;

        FollowerConstants.xMovement = 63;
        FollowerConstants.yMovement = 45;

        FollowerConstants.forwardZeroPowerAcceleration = -30;
        FollowerConstants.lateralZeroPowerAcceleration = -60;

        FollowerConstants.translationalPIDFCoefficients.setCoefficients(0.15,0,0.02,0);
        FollowerConstants.useSecondaryTranslationalPID = false;
        FollowerConstants.secondaryTranslationalPIDFCoefficients.setCoefficients(0.1,0,0.01,0); // Not being used, @see useSecondaryTranslationalPID

        FollowerConstants.headingPIDFCoefficients.setCoefficients(2,0,0.1,0);
        FollowerConstants.useSecondaryHeadingPID = false;
        FollowerConstants.secondaryHeadingPIDFCoefficients.setCoefficients(2,0,0.1,0); // Not being used, @see useSecondaryHeadingPID

        FollowerConstants.drivePIDFCoefficients.setCoefficients(0.007,0,0.000001,0.6,0);
        FollowerConstants.useSecondaryDrivePID = false;
        FollowerConstants.secondaryDrivePIDFCoefficients.setCoefficients(0.1,0,0,0.6,0); // Not being used, @see useSecondaryDrivePID

        FollowerConstants.zeroPowerAccelerationMultiplier = 3;
        FollowerConstants.centripetalScaling = 0.0001;

        FollowerConstants.pathEndTimeoutConstraint = 500;
        FollowerConstants.pathEndTValueConstraint = 0.995;
        FollowerConstants.pathEndVelocityConstraint = 0.1;
        FollowerConstants.pathEndTranslationalConstraint = 0.1;
        FollowerConstants.pathEndHeadingConstraint = 0.007;
    }
}
