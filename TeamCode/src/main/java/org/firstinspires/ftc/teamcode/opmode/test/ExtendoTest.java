package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.hardwareData.ServoData;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ArmLeftServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ExtendoLeftServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ExtendoRightServoData21528;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

@Config
@TeleOp(name = "ExtendoTest", group = "Test")

public class ExtendoTest extends LinearOpMode {

    private ServoSimple servoL, servoR;

    @Override
    public void runOpMode() {
        ServoData servoLData = new ExtendoLeftServoData21528();
        ServoData servoRData = new ExtendoRightServoData21528();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        servoL = new ServoSimple(hardwareMap, telemetry, "extendoLeft", new ArmLeftServoData21528());
        servoR = new ServoSimple(hardwareMap, telemetry, "extendoRight", new ArmLeftServoData21528());

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_bumper) {
                servoL.setPosition(servoLData.extendoExtendedPosition);
                servoR.setPosition(servoRData.extendoExtendedPosition);
            } else if (gamepad1.left_bumper) {
                servoL.setPosition(servoLData.extendoRetractedPosition);
                servoR.setPosition(servoRData.extendoRetractedPosition);
            }
        }
    }
}
