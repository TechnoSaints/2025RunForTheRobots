package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.hardwareConstants.ExtendoLeftPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoEncoder;

@Config
@TeleOp(name = "Test Servo with Encoder Control", group = "Test")
@Disabled
public class ServoEncoderControlTest extends LinearOpMode {

    private ServoEncoder servo;

    @Override
    public void runOpMode() {
        servo = new ServoEncoder(hardwareMap, telemetry, "extendoLeftServo", "extendoLeftServo");
        telemetry.addLine("Servo Created...");
        telemetry.update();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.setPosition(ExtendoLeftPositions.RETRACTED.getValue());
            } else if (gamepad1.left_bumper) {
                servo.setPosition(ExtendoLeftPositions.EXTENDED.getValue());
            }
            servo.update();
        }
    }
}