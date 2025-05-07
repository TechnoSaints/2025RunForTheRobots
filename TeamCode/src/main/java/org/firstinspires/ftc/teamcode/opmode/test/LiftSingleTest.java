package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Lift;

@Config
@TeleOp(name = "LiftSingleTest", group = "Test")
//@Disabled
public class LiftSingleTest extends LinearOpMode {

    private Lift lift;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap, telemetry, "lift", false);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                lift.up(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0.2) {
                lift.down(gamepad1.left_trigger);
            } else {
                lift.stop();
            }
            lift.log();

        }
    }
}
