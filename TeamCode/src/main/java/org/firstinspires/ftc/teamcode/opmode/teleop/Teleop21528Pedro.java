package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
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
import org.firstinspires.ftc.teamcode.common.BotWithPedro;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.DrivetrainData21528;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

@Config
@TeleOp(name = "Pedro Teleop21528", group = "Linear OpMode")
public class Teleop21528Pedro extends LinearOpMode {
    private BotWithPedro bot;
    private boolean liftIsLocked = false;
    private DrivetrainData21528 drivetrainData = new DrivetrainData21528();

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new BotWithPedro(this, telemetry, drivetrainData);

        waitForStart();
        bot.liftResetEncoder();
        while (opModeIsActive() && !isStopRequested()) {

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
                bot.goToBucket();
                bot.liftHigh();
                bot.armSwing();
                bot.wristSwing();
            }

            bot.update();
        }
    }
}
