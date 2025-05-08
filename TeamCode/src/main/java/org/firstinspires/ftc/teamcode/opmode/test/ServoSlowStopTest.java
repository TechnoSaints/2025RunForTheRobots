package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot21528;
import org.firstinspires.ftc.teamcode.common.servos.ServoSlowStop;

@Config
@TeleOp(name = "ServoSlowStopTest", group = "Test")

public class ServoSlowStopTest extends LinearOpMode {

    ServoSlowStop servoL, servoR;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Bot21528 bot = new Bot21528(this, telemetry);
//        BotDrivetrain bot = new BotDrivetrain(this, telemetry);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            if (gamepad1.right_bumper) {
//                bot.armClose();
            }
            if (gamepad1.left_bumper) {
//                bot.armOpen();
            }
            if (gamepad1.right_trigger > 0.2) {
//                bot.armLook();
            }
            if (gamepad1.left_trigger > 0.2) {
//                bot.armSwing();
            }
            bot.update();
        }
    }
}
