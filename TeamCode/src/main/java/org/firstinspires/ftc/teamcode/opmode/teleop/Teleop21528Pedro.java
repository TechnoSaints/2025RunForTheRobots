package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.DrivetrainData21528;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Config
@TeleOp(name = "Pedro Teleop21528", group = "Linear OpMode")
@Disabled
public class Teleop21528Pedro extends LinearOpMode {
    private Bot bot;
    private Follower follower;
    private DrivetrainData21528 drivetrainData = new DrivetrainData21528();
    private double maxSlowPower = drivetrainData.maxSlowPower;
    private Pose currentPose;
    private boolean teleop;
    protected final Pose parkPose = new Pose(9, 52.5, Math.toRadians(0));
    private final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    protected final Pose bucketPose = new Pose(-14.7, 7, Math.toRadians(45));
    protected final Pose parkSetupPose = new Pose(0, 49.5, Math.toRadians(0));
    private PathChain bucketPath;

    @Override
    public void runOpMode() {
        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;
        boolean liftIsLocked = false;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new Bot(this, telemetry, 0);
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        waitForStart();
        bot.liftResetEncoder();
        follower.startTeleopDrive();
        teleop = true;
        while (opModeIsActive() && !isStopRequested()) {

        /* Update Pedro to move the robot based on:
        - Forward/Backward Movement: -gamepad1.left_stick_y
        - Left/Right Movement: -gamepad1.left_stick_x
        - Turn Left/Right Movement: -gamepad1.right_stick_x
        - Robot-Centric Mode: true
        */
            driveAxial = -gamepad1.left_stick_y;
            driveStrafe = -gamepad1.left_stick_x;
            driveYaw = -gamepad1.right_stick_x;

            if (gamepad1.dpad_up) {
                follower.setTeleOpMovementVectors(maxSlowPower, 0, 0, true);
            } else if (gamepad1.dpad_down) {
                follower.setTeleOpMovementVectors(-maxSlowPower, 0, 0, true);
            } else if (gamepad1.dpad_left) {
                follower.setTeleOpMovementVectors(0, maxSlowPower, 0, true);
            } else if (gamepad1.dpad_right) {
                follower.setTeleOpMovementVectors(0, -maxSlowPower, 0, true);
            } else {
                if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                    follower.holdPoint(follower.getPose());
                    teleop = false;
                    // follower.setTeleOpMovementVectors(0, 0, 0, true);
                } else {
                    follower.setTeleOpMovementVectors(driveAxial, driveStrafe, driveYaw, true);
                }
            }

            if (gamepad1.right_trigger > 0.2) {
                bot.liftUp(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.2) {
                bot.liftDown(gamepad1.left_trigger);
            } else {
                bot.liftStop();
            }

            if (gamepad1.x) {
                bot.grabberClose();
            } else if (gamepad1.a) {
                bot.grabberOpen();
            } else if (gamepad1.y) {
                bot.grabberMiddle();
            }

            if (gamepad1.left_bumper) {
                bot.armClose();
                bot.wristClose();
            } else if (gamepad1.right_bumper) {
                bot.armMiddle();
                bot.wristClose();
            }

            if (gamepad1.b) {
                bot.armSwing();
                bot.wristSwing();
            }

            if ((gamepad1.start) && (gamepad1.share)) {
                bot.liftMoveDownToSwitch();
                bot.liftResetEncoder();
            }

            if (gamepad1.touchpad || liftIsLocked == true) {
                bot.liftLock();
                liftIsLocked = true;
            }

            if (gamepad1.ps) {
                bot.liftUnlock();
                liftIsLocked = false;
            }

            if (gamepad1.share) {
                currentPose = follower.getPose();
                bucketPath = follower.pathBuilder()
                        .addPath(new BezierLine(new Point(currentPose), new Point(parkSetupPose)))
                        .setLinearHeadingInterpolation(currentPose.getHeading(), parkSetupPose.getHeading())
                        .addPath(new BezierLine(new Point(parkSetupPose), new Point(bucketPose)))
                        .setLinearHeadingInterpolation(parkSetupPose.getHeading(), bucketPose.getHeading())
                        .build();
                follower.followPath(bucketPath, true);
                bot.liftHigh();
                bot.armSwing();
                bot.wristSwing();
                teleop = false;
            }

            if (!follower.isBusy() && !teleop)
            {
                teleop = true;
                follower.startTeleopDrive();
            }

            follower.update();
            bot.update();
        }
    }
}
