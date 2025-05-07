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
    private int phase;
    private double delay = 0;

    enum Modes {
        WAITING_AT_START,
        CRUISING,
        LOOKING_FOR_BRICK,
        HOLDING_BRICK,
        HANDING_OFF,
        INTAKING_BRICK_FROM_FLOOR,
        INTAKING_SPECIMEN_FROM_WALL,
        PLACING_BRICK_ON_FLOOR,
        SCORING_SAMPLE,
        SCORING_SPECIMEN,
        PARKING_AT_SUB,
        PARKING_IN_HP_AREA,
        CLIMBING
    }

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
        initAsynchTimer();
    }

    private void initAsynchTimer() {
        delay = 0;
        delayTimer.reset();
    }

    private void setAsynchDelay(double delayTime) {
        if (!waiting) {
            delay = delayTime;
            waiting = true;
            delayTimer.reset();
        }
    }

    private boolean waitingAsynch() {
        telemetry.addData("delay: ", delay);
        telemetry.addData("timer in MS: ", delayTimer.milliseconds());
        telemetry.update();
        if (delayTimer.milliseconds() < delay) {
            return (true);
        } else {
            waiting = false;
            return (waiting);
        }
    }

    private void synchDelay(double delayTime) {
        assert (delayTime < 250);
        delayTimer.reset();
        delay = delayTime;
        while (delayTimer.milliseconds() < delay) {
        }
    }

    private void setMode(Modes newMode) {
        currentMode = newMode;
        phase = 1;
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
        }
        if (gamepad.right_trigger > 0.2) {
            lift.up(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            lift.down(gamepad.left_trigger);
        } else {
            lift.stop();
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
                    if (phase == 1) {
                        setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                        synchDelay(200);
                        if (!waitingAsynch()) {
                            phase = 2;
                        }
                    } else if (phase == 2) {
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                        setAsynchDelay(100);
                        if (!waitingAsynch()) {
                            setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                            busy = false;
                            phase = -1;
                        }
                    }
                }
                break;

            case HANDING_OFF:
                if (busy) {
                    if (phase == 1) {
                        setLiftPositionPreset(LiftPositions.HANDOFF);
                        setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
                        setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                        synchDelay(200);
                        if (!waitingAsynch() && !(lift.isBusy())) {
                            phase = 2;
                        }
                    } else if (phase == 2) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    }
                }
                break;

            case LOOKING_FOR_BRICK:
                if (busy) {
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setAsynchDelay(250);
                    if (!waitingAsynch()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.HIGH);
                        busy = false;
                    }
                }
                break;

            case PLACING_BRICK_ON_FLOOR:
                if (busy) {
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setAsynchDelay(350);
                    if (!waitingAsynch()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        busy = false;
                    }
                }
                break;

            case INTAKING_SPECIMEN_FROM_WALL:
                if (busy) {
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                }
                break;

            case SCORING_SPECIMEN:
                if (busy) {
                    if (phase == 1) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        synchDelay(200);
                        if (!waitingAsynch()) {
                            phase = 2;
                        }
                    } else if (phase == 2) {
                        setLiftPositionPreset(LiftPositions.SPECIMEN_HANG);
                        setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                        setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                    }
                }
                break;

            case SCORING_SAMPLE:
                if (busy) {
                    if (phase == 1) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        synchDelay(200);
                        if (!waitingAsynch()) {
                            phase = 2;
                        }
                    } else if (phase == 2) {
                        setLiftPositionPreset(LiftPositions.HIGH_BASKET);
                        setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BASKET);
                        setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BASKET);
                    }
                }
                break;
        }
    }
}
