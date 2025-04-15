package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareConstants.ExtendoLeftPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConstants.ExtendoRightPositions;

@Config
@TeleOp(name = "ExtendoTest", group = "Test")

public class ExtendoTest extends LinearOpMode {

    private Servo servoL, servoR;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servoL = hardwareMap.get(Servo.class, "extendoLeft");
        servoR = hardwareMap.get(Servo.class, "extendoReft");

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servoL.setPosition(ExtendoLeftPositions.EXTENDED.getValue());
                servoR.setPosition(ExtendoRightPositions.EXTENDED.getValue());
            } else if (gamepad1.left_bumper) {
                servoL.setPosition(ExtendoLeftPositions.RETRACTED.getValue());
                servoR.setPosition(ExtendoRightPositions.RETRACTED.getValue());
            }
        }
    }
}
