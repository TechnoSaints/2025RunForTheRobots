package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Bot21528;

@Config
@TeleOp(name = "Bot21528WithPedroTest", group = "Test")

public class BotTest extends LinearOpMode {

    private Bot21528 bot;
    private Servo servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new Bot21528(this, telemetry);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            bot.update();
        }
    }
}
