package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;

@Config
@TeleOp(name = "HandoffTest", group = "Test")

public class HandoffTest extends LinearOpMode {

    private TeleopBot bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        bot.setMode(Modes.HOVERING_OVER_BRICK);
        while (!opModeIsActive())
        {
            bot.update();
        }
        waitForStart();
        bot.setMode(Modes.INTAKING_BRICK);
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                bot.setMode(Modes.HOLDING_BRICK_FOR_TRANSFER);
            } else if (gamepad1.left_bumper) {
                bot.setMode(Modes.HANDING_OFF_BRICK);
            }
            bot.update();
        }
    }
}
