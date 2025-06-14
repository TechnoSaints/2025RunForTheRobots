package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.TeleopBot;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;

@Config
@TeleOp(name = "HandoffTest", group = "Test")

public class HandoffTest extends LinearOpMode {

    private TeleopBot bot;
    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new TeleopBot(this, telemetry);

        bot.setMode(Modes.INTAKE_HOVER_POS);
        while (!opModeIsActive()) {
            bot.update();
        }
        waitForStart();
        bot.setMode(Modes.INTAKE_BRICK);
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
            } else if (gamepad1.y) {
                bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
            } else if (gamepad1.left_bumper) {
                bot.setMode(Modes.HAND_OFF_BRICK);
            }
            bot.update();
        }
    }
}
