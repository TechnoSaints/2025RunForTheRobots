package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareData.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.DrivetrainData21528;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class BotWithPedro extends Bot {
    private Follower follower;
    private double maxSlowPower;
    private Pose currentPose;
    private PathChain bucketPath;
    private boolean teleop;
    public BotWithPedro(OpMode opMode, Telemetry telemetry, DrivetrainData drivetrainData) {
        super(opMode, telemetry, 0);
        Constants.setConstants(FConstants.class, LConstants.class);
        maxSlowPower = drivetrainData.maxSlowPower;
        follower = new Follower(opMode.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(FieldLocations.startPose);
        follower.startTeleopDrive();
        teleop = true;
    }

    /* Update Pedro to move the robot based on:
    - Forward/Backward Movement: -gamepad1.left_stick_y
    - Left/Right Movement: -gamepad1.left_stick_x
    - Turn Left/Right Movement: -gamepad1.right_stick_x
    - Robot-Centric Mode: true
    */
    public void pedroMove(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            follower.setTeleOpMovementVectors(maxSlowPower, 0, 0, true);
        } else if (gamepad.dpad_down) {
            follower.setTeleOpMovementVectors(-maxSlowPower, 0, 0, true);
        } else if (gamepad.dpad_left) {
            follower.setTeleOpMovementVectors(0, maxSlowPower, 0, true);
        } else if (gamepad.dpad_right) {
            follower.setTeleOpMovementVectors(0, -maxSlowPower, 0, true);
        } else {
            if ((Math.abs(-gamepad.left_stick_y) < 0.2) && (Math.abs(-gamepad.left_stick_x) < 0.2) && (Math.abs(-gamepad.right_stick_x) < 0.2)) {
                follower.holdPoint(follower.getPose());
                teleop = false;
                // follower.setTeleOpMovementVectors(0, 0, 0, true);
            } else {
                follower.setTeleOpMovementVectors(-gamepad.left_stick_y, -gamepad.left_stick_x, -gamepad.right_stick_x, true);
            }
        }
    }

    public void goToBucket() {
        currentPose = follower.getPose();
        bucketPath = follower.pathBuilder()
                .addPath(new BezierLine(new Point(currentPose), new Point(FieldLocations.parkSetupPose)))
                .setLinearHeadingInterpolation(currentPose.getHeading(), FieldLocations.parkSetupPose.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.parkSetupPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.parkSetupPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();
        follower.followPath(bucketPath, true);
        teleop = false;
    }

    public void update() {
        if (!follower.isBusy() && !teleop) {
            follower.startTeleopDrive();
            teleop = true;
        }
        follower.update();
    }
}

