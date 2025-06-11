package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
    private final RevTouchSensor handlerSwitch, bumperSwitchL, bumperSwitchR;
    private final Extendo extendo;
    private final Lift lift;
    private Modes currentMode;
    private int currentPhase;
    private boolean onHold = false;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        extendo = new Extendo(opMode.hardwareMap, telemetry, "extendo");
        intakeWrist = new ServoSimple(opMode.hardwareMap, telemetry, "intakeWrist");
        intakeSwivel = new ServoSimple(opMode.hardwareMap, telemetry, "intakeSwivel");
        intakeGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "intakeGrabber");
        handlerArm = new ServoSimple(opMode.hardwareMap, telemetry, "handlerArm");
        handlerWrist = new ServoSimple(opMode.hardwareMap, telemetry, "handlerWrist");
        handlerGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "handlerGrabber");
        handlerSwitch = opMode.hardwareMap.get(RevTouchSensor.class, "handlerSwitch");
        lift = new Lift(opMode.hardwareMap, telemetry, "lift", false);
        intakeLight = new ServoSimple(opMode.hardwareMap, telemetry, "intakeLight");
        intakeLight.setPositionTicks(IntakeLightPositions.OFF.getValue(), 0);
        bumperSwitchL = opMode.hardwareMap.get(RevTouchSensor.class, "bumperSwitchL");
        bumperSwitchR = opMode.hardwareMap.get(RevTouchSensor.class, "bumperSwitchR");

    }

    // Phases are used to divide mode actions into sequential section, with entry criteria
    private void setPhase(int phase) {
        currentPhase = phase;
    }

    private boolean isPhase(int phase) {
        return (currentPhase == phase);
    }

    // Modes used to manage all mechanisms except drivetrain
    public void setMode(Modes newMode) {
        currentMode = newMode;
        setPhase(1);
    }

    public Modes getMode() {
        return (currentMode);
    }

    public boolean isMode(Modes mode) {
        return (this.currentMode == mode);
    }

    public void setExtendoPositionPreset(ExtendoPositions position) {
        extendo.setPositionPreset(position);
    }

    private boolean extendoIsBusy() {
        return extendo.isBusy();
    }

    public void setIntakeWristPositionPreset(IntakeWristPositions position) {
        intakeWrist.setPositionTicks(position.getValue(), 250);
    }

    private boolean intakeWristIsBusy() {
        return intakeWrist.isBusy();
    }

    public void setIntakeSwivelPositionPreset(IntakeSwivelPositions position) {
        intakeSwivel.setPositionTicks(position.getValue(), 150);
    }

    private boolean intakeSwivelIsBusy() {
        return intakeSwivel.isBusy();
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue(), 150);
    }

    private boolean intakeGrabberIsBusy() {
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
        handlerArm.setPositionTicks(position.getValue(), 450);
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position, int delay) {
        handlerArm.setPositionTicks(position.getValue(), delay);
    }

    private boolean handlerArmIsBusy() {
        return handlerArm.isBusy();
    }

    public void setHandlerWristPositionPreset(HandlerWristPositions position) {
        handlerWrist.setPositionTicks(position.getValue(), 250);
    }

    private boolean handlerWristIsBusy() {
        return handlerWrist.isBusy();
    }

    public void setHandlerGrabberPositionPreset(HandlerGrabberPositions position) {
        handlerGrabber.setPositionTicks(position.getValue(), 250);
    }

    private boolean handlerGrabberIsBusy() {
        return handlerGrabber.isBusy();
    }

    public boolean handlerIsBusy() {
        return (liftIsBusy() || handlerArmIsBusy() || handlerWristIsBusy() || handlerGrabberIsBusy());
    }

    public boolean intakeIsBusy() {
        return (extendoIsBusy() || intakeWristIsBusy() || intakeSwivelIsBusy() || intakeGrabberIsBusy());
    }

    public boolean isBusy() {
        return (handlerIsBusy() || intakeIsBusy());
    }

    public boolean onHold() {
        return (onHold);
    }

    private void disableHandlerServos() {
        handlerGrabber.disable();
        handlerWrist.disable();
        handlerArm.disable();
    }

    private void enableHandlerServos() {
        handlerGrabber.enable();
        handlerWrist.enable();
        handlerArm.enable();
    }

    private void disableIntakeServos() {
        intakeGrabber.disable();
        intakeSwivel.disable();
        intakeWrist.disable();
        intakeLight.disable();
    }

    private void enableIntakeServos() {
        intakeGrabber.enable();
        intakeSwivel.enable();
        intakeWrist.enable();
        intakeLight.enable();
    }

    public boolean bumperBumped() {
        return (bumperSwitchL.isPressed() || bumperSwitchR.isPressed());
    }

    public void update() {
        extendo.update();
        lift.log();
        switch (getMode()) {
            case AUTO_START_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setHandlerArmPositionPreset(HandlerArmPositions.AUTO_START);
                    setLiftPositionPreset(LiftPositions.MIN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case TELEOP_START_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.MIN);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case INTAKE_HOVER_POS:
                if (isPhase(1)) {
                    onHold = true;
                    extendo.setMedium();
                    setExtendoPositionPreset(ExtendoPositions.AUTO_INTAKING);
                    extendo.setFast();
//                    setExtendoPositionPreset(ExtendoPositions.AUTO_INTAKING);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            // Assumes already directly above target brick
            case INTAKE_BRICK:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeWristIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!intakeGrabberIsBusy()) {
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case HANDLER_HANDOFF_PREP_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case HANDLER_GRAB_SPECIMEN_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    onHold = false;
                    setPhase(-1);
                }
                break;


            // Assumes already HANDLER_HANDOFF_PREP_POS and extendo retracted
            // Transfers brick from intake to handler
            case HAND_OFF_BRICK:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.HANDOFF);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!liftIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
//                        setPhase(3);
//                    }
//                } else if (isPhase(3)) {
//                    if (!handlerGrabberIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case INTAKE_SPIT_BRICK_ON_FLOOR:
                if (isPhase(1)) {
                    onHold = true;
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case INTAKE_SWEEP_POS:
                if (isPhase(1)) {
                    onHold = true;
//                    extendo.setMedium();
                    extendo.setPositionPreset(ExtendoPositions.EXTENDED);
//                    extendo.setFast();
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.SWEEP);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case GRAB_SPECIMEN:
                if (isPhase(1)) {
                    onHold = true;
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!handlerGrabberIsBusy()) {
                        setHandlerWristPositionPreset(HandlerWristPositions.UP_SPECIMEN);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            // Assumes grasping specimen
            case HANDLER_HIGH_SPECIMEN_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.SPECIMEN_HANG_SETUP);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE);
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                    onHold = false;
                    setPhase(-1);
                }
                break;

            case HANG_SPECIMEN:
                if (isPhase(1)) {
                    onHold = true;
                    lift.setPositionPreset(LiftPositions.SPECIMEN_HANG);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!liftIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!handlerGrabberIsBusy()) {
                        setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            // Assumes brick is already gripped tightly
            case HANDLER_HIGH_BUCKET_POS:
                if (isPhase(1)) {
                    onHold = true;
                    if (!handlerGrabberIsBusy()) {
                        setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                        setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET);
                        setHandlerWristPositionPreset(HandlerWristPositions.HIGH_BUCKET);
                        onHold = false;
                        setPhase(-1);
                    }
                }
                break;

            case PARKING_AT_SUB_POS:
                if (isPhase(1)) {
                    onHold = true;
                    setLiftPositionPreset(LiftPositions.MIN);
                    setHandlerArmPositionPreset(HandlerArmPositions.SUB_PARKING);
                    setHandlerWristPositionPreset(HandlerWristPositions.SUB_PARKING);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_LOOSE);
                    onHold = false;
                    setPhase(-1);
                }
                break;
        }
    }
}
