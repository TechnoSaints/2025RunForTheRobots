package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConstants.ExtendoLeftPositions;

@Config
@TeleOp(name = "TEST ServoTest", group = "Test")
@Disabled
public class ServoTest extends LinearOpMode {

    private Servo servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        servo = hardwareMap.get(Servo.class,"extendoLeft");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.setPosition(ExtendoLeftPositions.EXTENDED.getValue());
            } else if (gamepad1.left_bumper) {
                servo.setPosition(ExtendoLeftPositions.EXTENDED.getValue());
            }
        }
    }
}
