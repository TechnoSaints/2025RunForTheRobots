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
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public abstract class Bot extends Component {
    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
    private final ServoSimple handlerArm, handlerWrist, handlerGrabber;
    private final Extendo extendo;
    private final Lift lift;
    private Modes currentMode;
    private int currentPhase;

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

    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }

    public void setMode(Modes newMode) {
        currentMode = newMode;
        setPhase(1);
    }

    public Modes getMode()
    {return(currentMode);}

    public boolean isMode(Modes mode) {
        return (this.currentMode == mode);
    }
    public void setExtendoPositionPreset(ExtendoPositions position) {
        extendo.setPositionPreset(position, 200);
    }

    public boolean extendoIsBusy() {
        return extendo.isBusy();
    }

    public void setIntakeWristPositionPreset(IntakeWristPositions position) {
        intakeWrist.setPositionTicks(position.getValue(), 100);
    }

    public boolean intakeWristIsBusy() {
        return intakeWrist.isBusy();
    }

    public void setIntakeSwivelPositionPreset(IntakeSwivelPositions position) {
        intakeSwivel.setPositionTicks(position.getValue(), 100);
    }

    public boolean intakeSwivelIsBusy() {
        return intakeSwivel.isBusy();
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue(), 200);
    }

    public boolean intakeGrabberIsBusy() {
        return intakeGrabber.isBusy();
    }

    public void setIntakeLightPositionPreset(IntakeLightPositions position) {
        intakeLight.setPositionTicks(position.getValue(), 0);
    }

    public void setLiftPositionPreset(LiftPositions position) {
        lift.setPositionPreset(position);
    }

    public void liftUp(double power) {
        lift.up(power);
    }

    public void liftDown(double power) {
        lift.down(power);
    }

    public void liftStop() {
        lift.stop();
    }

    public boolean liftIsBusy() {
        return lift.isBusy();
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position) {
        handlerArm.setPositionTicks(position.getValue(), 200);
    }

    public boolean handlerArmIsBusy() {
        return handlerArm.isBusy();
    }

    public void setHandlerWristPositionPreset(HandlerWristPositions position) {
        handlerWrist.setPositionTicks(position.getValue(), 100);
    }

    public boolean handlerWristIsBusy() {
        return handlerWrist.isBusy();
    }

    public void setHandlerGrabberPositionPreset(HandlerGrabberPositions position) {
        handlerGrabber.setPositionTicks(position.getValue(), 200);
    }

    public boolean handlerGrabberIsBusy() {
        return handlerGrabber.isBusy();
    }

    private boolean handlerIsBusy() {
        return (liftIsBusy() || handlerArmIsBusy() || handlerWristIsBusy() || handlerGrabberIsBusy());
    }

    private boolean intakeIsBusy() {
        return (extendoIsBusy() || intakeWristIsBusy() || intakeSwivelIsBusy() || intakeGrabberIsBusy());
    }

    public abstract void processGamepadInput(Gamepad gamepad);

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
    }
}
