package org.firstinspires.ftc.teamcode.pedroPathing.constants;

import com.pedropathing.localization.Localizers;
import com.pedropathing.follower.FollowerConstants;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;

public class FConstants {
    static {
        FollowerConstants.localizers = Localizers.PINPOINT;

        FollowerConstants.leftFrontMotorName = DrivetrainData.leftFrontMotorName;
        FollowerConstants.leftRearMotorName = DrivetrainData.leftRearMotorName;
        FollowerConstants.rightFrontMotorName = DrivetrainData.rightFrontMotorName;
        FollowerConstants.rightRearMotorName = DrivetrainData.rightRearMotorName;

        FollowerConstants.leftFrontMotorDirection = DrivetrainData.leftFrontMotorDirection;
        FollowerConstants.leftRearMotorDirection = DrivetrainData.leftRearMotorDirection;
        FollowerConstants.rightFrontMotorDirection = DrivetrainData.rightFrontMotorDirection;
        FollowerConstants.rightRearMotorDirection = DrivetrainData.rightRearMotorDirection;

        // mass in kg
        FollowerConstants.mass = 8.5;

        FollowerConstants.xMovement = 83.5;
        FollowerConstants.yMovement = 67;

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
