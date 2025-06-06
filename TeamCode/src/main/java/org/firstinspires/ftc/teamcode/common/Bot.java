package org.firstinspires.ftc.teamcode.common;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorTouch;
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
    private final RevTouchSensor handlerSwitch;
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
        handlerSwitch = opMode.hardwareMap.get(RevTouchSensor.class, "handlerSwitch");
        lift = new Lift(opMode.hardwareMap, telemetry, "lift", false);
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

    public void setExtendoPositionPreset(ExtendoPositions position){
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
        intakeSwivel.setPositionTicks(position.getValue(), 250);
    }

    private boolean intakeSwivelIsBusy() {
        return intakeSwivel.isBusy();
    }

    public void setIntakeGrabberPositionPreset(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue(), 250);
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

    private boolean liftIsBusy() {
        return lift.isBusy();
    }

    public void setHandlerArmPositionPreset(HandlerArmPositions position) {
        handlerArm.setPositionTicks(position.getValue(), 450);
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

    public void update() {
        extendo.update();
        switch (getMode()) {
            case AUTO_START:
                if (isPhase(1)) {
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
//                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
//                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
//                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    // extendo.setMedium();
//                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                    setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
//                    setLiftPositionPreset(LiftPositions.MIN);
                    setPhase(-1);
                }
                break;

            case TELEOP_START:
                if (isPhase(1)) {
//                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
//                    setIntakeWristPositionPreset(IntakeWristPositions.UP);
//                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
//                    setExtendoPositionPreset(ExtendoPositions.RETRACTED);
//                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
//                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
//                    setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
                    setLiftPositionPreset(LiftPositions.MIN);
                    setPhase(-1);
                }
                break;

            case HOVERING_OVER_BRICK:
                if (isPhase(1)) {
                    extendo.setMedium();
                    setExtendoPositionPreset(ExtendoPositions.AUTO_INTAKING);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setPhase(-1);
                }
                break;

            case LOOKING_FOR_BRICK:
                if (isPhase(1)) {
                    extendo.setMedium();
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

            case INTAKING_BRICK:
                if (isPhase(1)) {
                    setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!(intakeWristIsBusy() || intakeGrabberIsBusy())) {
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                        setPhase(-1);
                    }
                }
                break;

            case HOLDING_BRICK_FOR_TRANSFER:
//                telemetry.addData("handler: ", handlerIsBusy());
//                telemetry.addData("lift: ", liftIsBusy());
//                telemetry.addData("wrist: ", handlerWristIsBusy());
//                telemetry.addData("grabber: ", handlerGrabberIsBusy());
//
//                telemetry.addData("intake: ", intakeIsBusy());
//                telemetry.update();
                if (isPhase(1)) {
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF_SETUP);
                    setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF_SETUP);
                    setLiftPositionPreset(LiftPositions.HANDOFF_SETUP);
                    setPhase(2);
                } else if (isPhase(2)) {
                    if (!handlerIsBusy() && !intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                        setIntakeWristPositionPreset(IntakeWristPositions.UP);
                        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                        setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                        extendo.setFast();
                        setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                        setPhase(-1);
                    }
                }
                break;

            // Assumes already HOLDING_BRICK_FOR_TRANSFER
            // Transfers brick from intake to handler
            case HANDING_OFF_BRICK:
                if (isPhase(1)) {
                    if (!handlerIsBusy() && !intakeIsBusy()) {
                        setHandlerWristPositionPreset(HandlerWristPositions.HANDOFF);
                        setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF);
                        setLiftPositionPreset(LiftPositions.HANDOFF);
                        setPhase(2);
                    }
                } else if (isPhase(2)) {
                    if (!handlerIsBusy()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        setPhase(3);
                    }
                } else if (isPhase(3)) {
                    if (!handlerGrabberIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setPhase(-1);
                    }
                }
                break;

            case EJECTING_BRICK_ON_FLOOR:
                if (isPhase(1)) {
                    extendo.setFast();
                    setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
                    setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
                    setIntakeLightPositionPreset(IntakeLightPositions.OFF);
                    setPhase(2);
                } else if (

                        isPhase(2)) {
                    if (!intakeIsBusy()) {
                        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
                        setPhase(-1);
                    }
                }
                break;

            case GRABBING_SPECIMEN_FROM_WALL:
                if (

                        isPhase(1)) {
                    setLiftPositionPreset(LiftPositions.SPECIMEN_WALL);
                    setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_WALL);
                    setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL);
                    setPhase(2);
                } else if (

                        isPhase(2)) {
                    if (!handlerIsBusy() && handlerSwitch.isPressed()) {
                        setHandlerGrabberPositionPreset(HandlerGrabberPositions.CLOSED_TIGHT);
                        setPhase(-1);
                    }
                }
                break;

            // Assumes grasping specimen
            case HIGH_SPECIMEN_SCORING:
                if (

                        isPhase(1)) {
                    if (!handlerGrabberIsBusy()) {
                        setLiftPositionPreset(LiftPositions.SPECIMEN_HANG);
                        setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG);
                        setHandlerWristPositionPreset(HandlerWristPositions.SPECIMEN_HANG);
                        setPhase(-1);
                    }
                }
                break;

            // Assumes brick is already gripped tightly
            case HIGH_BASKET_SCORING:
                if (isPhase(1)) {
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
