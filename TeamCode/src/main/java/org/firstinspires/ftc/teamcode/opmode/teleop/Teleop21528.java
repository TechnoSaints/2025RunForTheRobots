package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.BotWithDrivetrain;

@Config
@TeleOp(name = "Teleop21528", group = "Linear OpMode")

public class Teleop21528 extends LinearOpMode {
    private BotWithDrivetrain bot;
    public static boolean loggingOn = false;

    @Override
    public void runOpMode() {
        double driveAxial = 0.0;
        double driveStrafe = 0.0;
        double driveYaw = 0.0;
        double motorDirection = 1;
        boolean liftIsLocked = false;

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        bot = new BotWithDrivetrain(this, telemetry);

        waitForStart();
        bot.liftResetEncoder();
        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.dpad_up) {
                bot.creepDirection(1.0, 0.0, 0.0);
            } else if (gamepad1.dpad_down) {
                bot.creepDirection(-1.0, 0.0, 0.0);
            } else if (gamepad1.dpad_left) {
                bot.creepDirection(0.0, -1.0, 0.0);
            } else if (gamepad1.dpad_right) {
                bot.creepDirection(0.0, 1.0, 0.0);
            } else {
                driveAxial = -gamepad1.left_stick_y * motorDirection;
                driveStrafe = gamepad1.left_stick_x * motorDirection;
                driveYaw = gamepad1.right_stick_x;
                if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                    bot.stopDrive();
                } else
                    bot.moveDirection(driveAxial, driveStrafe, driveYaw);
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
            }
//            else if (gamepad1.y) {
//                bot.grabberMiddle();
//            }

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

            if (gamepad1.triangle)
            {
                bot.increaseLiftMax(100);
            }

            bot.update();
        }
    }
}

