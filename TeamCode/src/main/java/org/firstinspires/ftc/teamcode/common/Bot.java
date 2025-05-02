package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

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
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public class Bot extends Component {
    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
    private final Extendo extendo;
    private final Lift lift;
    private ElapsedTime delayTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private Modes currentMode = Modes.WAITING_AT_START;
    private boolean busy, waiting = false;

    enum Modes {
        WAITING_AT_START,
        CRUISING,
        LOOKING_FOR_BRICK,
        HOLDING_BRICK,
        INTAKING_BRICK_FROM_FLOOR,
        INTAKING_SPECIMEN_FROM_WALL,
        PLACING_BRICK_IN_HP_AREA,
        SCORING_SAMPLE,
        SCORING_SPECIMEN,
        PARKING_AT_SUB,
        PARKING_IN_HP_AREA,
        CLIMBING
    }

    ;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        extendo = new Extendo(opMode.hardwareMap, telemetry, "extendo");
        intakeWrist = new ServoSimple(opMode.hardwareMap, telemetry, "intakeWrist");
        intakeSwivel = new ServoSimple(opMode.hardwareMap, telemetry, "intakeSwivel");
        intakeGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "intakeGrabber");
        intakeLight = new ServoSimple(opMode.hardwareMap, telemetry, "intakeLight");
        handlerArm = new ServoSimple(opMode.hardwareMap, telemetry, "handlerArm");
        handlerWrist = new ServoSimple(opMode.hardwareMap, telemetry, "handlerWrist");
        handlerGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "handlerGrabber");
        lift = new Lift(opMode.hardwareMap, telemetry, "lift", false);
        setMode(Modes.WAITING_AT_START);
    }

    private void setMode(Modes newMode) {
        currentMode = newMode;
        busy = true;
    }

    public void setExtendoPositionPreset(ExtendoPositions position) {
        extendo.setPositionPreset(position);
    }

    public void setIntakeWristPositionPreset(IntakeWristPositions position) {
        intakeWrist.setPositionTicks(position.getValue());
    }

    public void setIntakeSwivelPositionPreset(IntakeSwivelPositions position) {
        intakeSwivel.setPositionTicks(position.getValue());
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue());
    }

    public void setIntakeLightPositionPreset(IntakeLightPositions position) {
        intakeLight.setPositionTicks(position.getValue());
    }

    public void setLiftPositionPreset(LiftPositions position) {
        lift.setPositionPreset(position);
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position) {
        handlerArm.setPositionTicks(position.getValue());
    }

    public void setHandlerWristPositionPreset(HandlerWristPositions position) {
        handlerWrist.setPositionTicks(position.getValue());
    }

    public void setHandlerGrabberPositionPreset(HandlerGrabberPositions position) {
        handlerGrabber.setPositionTicks(position.getValue());
    }

    public boolean isBusy() {
        return (lift.isBusy() || (busy));
    }

    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.x) {
            if (!(currentMode == Modes.HOLDING_BRICK)) {
                setMode(Modes.HOLDING_BRICK);
            }
        } else if (gamepad.a) {
            if (!(currentMode == Modes.LOOKING_FOR_BRICK)) {
                setMode(Modes.LOOKING_FOR_BRICK);
            }
/*
            else {
                //Controls while looking for brick
                if (gamepad.right_bumper) {
                    extendo.extendSlowly(1.0);
                } else if (gamepad.left_bumper) {
                    extendo.extendSlowly(-1.0);
                }

            }
*/
        }
/*

        if (gamepad.right_trigger > 0.2) {
            lift.up(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            lift.down(gamepad.left_trigger);
        } else {
            lift.stop();
        }
*/

/*
        if (gamepad.touchpad) {
            lift.lock();
        }

        if (gamepad.ps) {
            lift.unlock();
        }
*/
    }

    public void update() {
        switch (currentMode) {

            case WAITING_AT_START:
                if (busy) {
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    busy = false;
                }
                break;

            case HOLDING_BRICK:
                if (busy) {
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    if (!waiting) {
                        delayTimer.reset();
                        waiting = true;
                    } else {
                        if (delayTimer.milliseconds() > 100) {
                            waiting = false;
                            setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                            busy = false;
                        }
                    }
                }
                break;

            case LOOKING_FOR_BRICK:
                if (busy) {
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    if (!waiting) {
                        delayTimer.reset();
                        waiting = true;
                    } else if (delayTimer.milliseconds() > 250) {
                        waiting = false;
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.HIGH);
                        busy = false;
                    }
                }
                break;
        }
    }
}
