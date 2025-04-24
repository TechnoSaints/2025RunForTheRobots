package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.Extendo;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;

@Config
@TeleOp(name = "ExtendoTest", group = "Test")

public class ExtendoTest extends LinearOpMode {

    private Servo servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        Extendo extendo = new Extendo(this.hardwareMap, telemetry, "testServo");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                extendo.goToPresetPosition(ExtendoPositions.EXTENDED);
            } else if (gamepad1.left_bumper) {
                extendo.goToPresetPosition(ExtendoPositions.RETRACTED);
            }

            if (gamepad1.right_trigger > 0.2) {
                extendo.extendSlowly(1);
            } else if (gamepad1.left_trigger > 0.2) {
                extendo.extendSlowly(-1);
            }
            extendo.log();
        }
    }
}
