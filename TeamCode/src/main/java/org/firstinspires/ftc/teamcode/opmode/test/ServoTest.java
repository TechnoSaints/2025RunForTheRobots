package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;

@Config
@TeleOp(name = "ServoTest", group = "Test")
@Disabled
public class ServoTest extends LinearOpMode {

    private Servo servo1, servo2;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        servo1 = hardwareMap.get(Servo.class,"intakeSwivel");
        servo2 = hardwareMap.get(Servo.class,"intakeWrist");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo1.setPosition(IntakeSwivelPositions.DEGREES0.getValue());
                servo2.setPosition(IntakeWristPositions.UP.getValue());
            } else if (gamepad1.left_bumper) {
                servo1.setPosition(IntakeSwivelPositions.DEGREES180.getValue());
                servo2.setPosition(IntakeWristPositions.DOWN.getValue());            }
        }
    }
}
