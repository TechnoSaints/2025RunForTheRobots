package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;

public class TeleopBot extends Bot {

    private final Drivetrain drivetrain;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;

    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
    }

    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            drivetrain.creepDirection(1.0, 0.0, 0.0);
        } else if (gamepad.dpad_down) {
            drivetrain.creepDirection(-1.0, 0.0, 0.0);
        } else if (gamepad.dpad_left) {
            drivetrain.creepDirection(0.0, -1.0, 0.0);
        } else if (gamepad.dpad_right) {
            drivetrain.creepDirection(0.0, 1.0, 0.0);
        } else {
            driveAxial = -gamepad.left_stick_y;
            driveStrafe = gamepad.left_stick_x;
            driveYaw = gamepad.right_stick_x;
            if ((Math.abs(driveAxial) < 0.2) && (Math.abs(driveStrafe) < 0.2) && (Math.abs(driveYaw) < 0.2)) {
                drivetrain.stop();
            } else
                drivetrain.moveDirection(driveAxial, driveStrafe, driveYaw);
        }

        if (gamepad.x) {
            if (!(isMode(Modes.HOLDING_BRICK_FOR_TRANSFER))) {
                setMode(Modes.HOLDING_BRICK_FOR_TRANSFER);
            }
        }
        if (gamepad.right_trigger > 0.2) {
            liftUp(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            liftDown(gamepad.left_trigger);
        } else {
            liftStop();
        }
        //Controls while looking for brick
        if (gamepad.a) {
            setMode(Modes.LOOKING_FOR_BRICK);
        } else if (gamepad.b) {
            setMode(Modes.GRABBING_SPECIMEN_FROM_WALL);
        } else if (gamepad.y) {
            setMode(Modes.HIGH_SPECIMEN_SCORING);
        } else if (gamepad.right_bumper) {
            setMode(Modes.HIGH_BASKET_SCORING);
        } else if (gamepad.left_bumper) {
            setMode(Modes.HANDING_OFF_BRICK);
        }
    }
}
