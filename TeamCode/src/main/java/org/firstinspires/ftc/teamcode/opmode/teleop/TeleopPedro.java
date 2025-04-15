package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.BotWithPedro;

@Config
@TeleOp(name = "TeleopPedro", group = "Linear OpMode")
public class TeleopPedro extends LinearOpMode {
    private BotWithPedro bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new BotWithPedro(this, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            bot.update();
        }
    }
}
