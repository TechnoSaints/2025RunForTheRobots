package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;

@Config
@TeleOp(name = "ExtendoPresetTest", group = "Test")

public class ExtendoPresetTest extends LinearOpMode {

    private Extendo extendo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        extendo = new Extendo(hardwareMap, telemetry, "extendo");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                extendo.setPositionPreset(ExtendoPositions.EXTENDED,0);
            } else if (gamepad1.left_bumper) {
                extendo.setPositionPreset(ExtendoPositions.RETRACTED,0);
            }
            extendo.update();
        }
    }
}
