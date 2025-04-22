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
    private final double maxSlowPower = DrivetrainData.maxSlowPower;
    private Pose currentPose;
    private boolean teleopDriving;

    public BotWithPedro(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        follower = new Follower(opMode.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(FieldLocations.startPose);
        follower.startTeleopDrive();
        teleopDriving = true;
    }

    public void followPath(PathChain path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public Follower getFollower() {

        return (follower);
    }

    public boolean followerIsBusy() {

        return (follower.isBusy());
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
                teleopDriving = false;
                // follower.setTeleOpMovementVectors(0, 0, 0, true);
            } else {
                follower.setTeleOpMovementVectors(-gamepad.left_stick_y, -gamepad.left_stick_x, -gamepad.right_stick_x, true);
            }
        }
    }

    public void update() {
        super.update();
        if (!followerIsBusy() && !teleopDriving) {
            follower.startTeleopDrive();
            teleopDriving = true;
        }
        follower.update();
    }
}
