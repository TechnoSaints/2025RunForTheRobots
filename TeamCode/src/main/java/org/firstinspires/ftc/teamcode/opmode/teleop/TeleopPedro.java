package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Bot21528WithoutPedro;

@Config
@TeleOp(name = "Teleop with Pedro", group = "Linear OpMode")
public class TeleopPedro extends LinearOpMode {
    private Bot21528WithoutPedro bot;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new Bot21528WithoutPedro(this, telemetry);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            bot.processGamepadInput(gamepad1);
            bot.update();
        }
    }
}
