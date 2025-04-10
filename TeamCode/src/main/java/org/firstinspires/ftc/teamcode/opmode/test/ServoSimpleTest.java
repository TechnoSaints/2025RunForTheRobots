package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ArmLeftServoData21528;

@Config
@TeleOp(name = "TEST ServoSimpleTest", group = "Test")
@Disabled
public class ServoSimpleTest extends LinearOpMode {

    private ServoSimple servo;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servo = new ServoSimple(hardwareMap, telemetry, "testServo", new ArmLeftServoData21528());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servo.close();
            } else if (gamepad1.left_bumper) {
                servo.open();
            }
        }
    }
}
