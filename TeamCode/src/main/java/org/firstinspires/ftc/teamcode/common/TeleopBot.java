package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

public class TeleopBot extends Bot {
    private final Drivetrain drivetrain;
    private double driveAxial = 0.0;
    private double driveStrafe = 0.0;
    private double driveYaw = 0.0;
    private ElapsedTime buttonTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    private int buttonDelay = 350;
    private boolean looking = false;

    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        drivetrain = new Drivetrain(opMode.hardwareMap, telemetry, new DrivetrainData(), new GoBilda435DcMotorData());
        buttonTimer.reset();
    }

    private boolean buttonPushable() {
        return (buttonTimer.milliseconds() > buttonDelay);
    }

    private void processDrivetrainInput(Gamepad gamepad) {
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
    }

    public void processSpecimenInput(Gamepad gamepad) {
        processDrivetrainInput(gamepad);

        if (buttonPushable()) {
            //Controls while looking for brick
            if (gamepad.a) {
                if (extendoGetCurrentLength() > (ExtendoPositions.RETRACTED.getValue() + 1.0)) {
//                    telemetry.addData("ex: ", extendoGetCurrentLength());
                    looking = false;
                    setMode(Modes.NOT_LOOKING_POS);
                } else {
                    //                    telemetry.addData("ex: ", extendoGetCurrentLength());
                    setMode(Modes.LOOKING_POS);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    looking = true;
                }
                buttonTimer.reset();
            } else if (gamepad.x) {
                if (looking) {
                    setMode(Modes.INTAKE_BRICK_SIMPLE);
                }
            }
        }
    }
    public void processBucketInput(Gamepad gamepad) {
        processDrivetrainInput(gamepad);
    }
}
