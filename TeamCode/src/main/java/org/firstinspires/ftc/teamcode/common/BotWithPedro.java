package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class BotWithPedro extends Bot {
    private final Follower follower;

    private final DrivetrainData drivetrainData = new DrivetrainData();
    private final double maxSlowPower = drivetrainData.maxSlowTeleopPower;
    private final double maxFastPower = drivetrainData.maxFastTeleopPower;

    private boolean teleop = true;

    public BotWithPedro(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        follower = new Follower(opMode.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(FieldLocations.startPose);
        follower.startTeleopDrive();
        teleop = true;
    }

    public void followPath(PathChain path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public Follower getFollower() {

        return (follower);
    }

    public boolean followerIsBusy() {

        return (super.isBusy() || follower.isBusy());
    }

    /* Update Pedro to move the robot based on:
    - Forward/Backward Movement: -gamepad1.left_stick_y
    - Left/Right Movement: -gamepad1.left_stick_x
    - Turn Left/Right Movement: -gamepad1.right_stick_x
    - Robot-Centric Mode: true
    */
    public void processGamepadInput(Gamepad gamepad) {
        super.processGamepadInput(gamepad);

        if (gamepad.dpad_up) {
            moveTeleop(maxSlowPower, 0, 0);
        } else if (gamepad.dpad_down) {
            moveTeleop(-maxSlowPower, 0, 0);
        } else if (gamepad.dpad_left) {
            moveTeleop(0, maxSlowPower, 0);
        } else if (gamepad.dpad_right) {
            moveTeleop(0, -maxSlowPower, 0);
        } else {
            if ((Math.abs(-gamepad.left_stick_y) < 0.2) && (Math.abs(-gamepad.left_stick_x) < 0.2) && (Math.abs(-gamepad.right_stick_x) < 0.2)) {
                moveTeleop(0,0,0);
                //                follower.holdPoint(follower.getPose());
//                teleop = false;
            } else {
                moveTeleop(-gamepad.left_stick_y * maxFastPower, -gamepad.left_stick_x * maxFastPower, -gamepad.right_stick_x * maxFastPower);
            }
        }

    }

    private void moveTeleop(double forward, double lateral, double heading) {
//        if (!follower.isBusy() && !teleop)
/*
        if (!teleop) {
            teleop = true;
            follower.startTeleopDrive();
        }
*/
        follower.setTeleOpMovementVectors(forward, lateral, heading, true);
    }

    public void update() {
        super.update();
        follower.update();
    }
}
