package org.firstinspires.ftc.teamcode.opmode.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.Lift;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

@Config
@TeleOp(name = "LiftPresetTest", group = "Test")
@Disabled
public class LiftPresetTest extends LinearOpMode {

    private Lift lift;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        lift = new Lift(hardwareMap, telemetry, "lift", false);
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.right_trigger > 0.2) {
                lift.setPositionPreset(LiftPositions.HIGH_BUCKET);
            } else if (gamepad1.left_trigger > 0.2) {
                lift.setPositionPreset(LiftPositions.MIN);
            }
            lift.log();
        }
    }
}
