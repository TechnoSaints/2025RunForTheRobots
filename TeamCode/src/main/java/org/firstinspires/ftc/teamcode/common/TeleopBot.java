package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeLightPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

public class TeleopBot extends BotWithoutPedro {
    public TeleopBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        setMode(Modes.WAITING_AT_START);
    }

    public void processGamepadInput(Gamepad gamepad) {
        super.processGamepadInput(gamepad);
        if (gamepad.x) {
            if (!(isMode(Modes.HOLDING_BRICK))) {
                setMode(Modes.HOLDING_BRICK);
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
            setMode(Modes.INTAKING_SPECIMEN_FROM_WALL);
        } else if (gamepad.y) {
            setMode(Modes.SCORING_SPECIMEN);
        } else if (gamepad.right_bumper) {
            setMode(Modes.SCORING_SAMPLE);
        } else if (gamepad.left_bumper) {
            setMode(Modes.HANDING_OFF);
        }
    }

    public void update() {
        switch (getMode()) {
            case WAITING_AT_START:
                if (isPhase(1)) {
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setPhase(-1);
                }
                break;

            case HOLDING_BRICK:
                if (isPhase(1)) {
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!(intakeWristIsBusy() || intakeGrabberIsBusy())) {
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!(intakeWristIsBusy() || intakeSwivelIsBusy())) {
                        setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                        setPhase(-1);
                    }
                }
                break;

            case HANDING_OFF:
                if (isPhase(1)) {
                    setLiftPositionPreset(LiftPositions.HANDOFF);
                    setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!(handlerIsBusy())) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setPhase(-1);
                    }
                }
                break;

            case LOOKING_FOR_BRICK:
                if (isPhase(1)) {
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!(extendoIsBusy())) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.HIGH);
                        setPhase(-1);
                    }
                }
                break;

            case PLACING_BRICK_ON_FLOOR:
                if (isPhase(1)) {
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                } else if (isPhase(2)) {
                    if (!(intakeIsBusy())) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setPhase(-1);
                    }
                }
                break;

            case INTAKING_SPECIMEN_FROM_WALL:
                if (isPhase(1)) {
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setPhase(-1);
                }
                break;

            case SCORING_SPECIMEN:
                if (isPhase(1)) {
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!(handlerGrabberIsBusy())) {
                        setLiftPositionPreset(LiftPositions.SPECIMEN_HANG);
                        setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                        setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                        setPhase(-1);
                    }
                }
                break;

            case SCORING_SAMPLE:
                if (isPhase(1)) {
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!handlerGrabberIsBusy()) {
                        setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                        setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET);
                        setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                        setPhase(-1);
                    }
                }
                break;
        }
        super.update();
    }
}
