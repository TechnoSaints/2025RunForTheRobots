package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeLightPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public class Bot extends Component {
    private final ServoSimple intakeWrist, intakeSwivel, intakeGrabber, intakeLight;
    private final Extendo extendo;
    private final Lift lift;

    private Modes currentMode = Modes.WAITING_AT_START;

    enum Modes {
        WAITING_AT_START,
        CRUISING,
        LOOKING_FOR_BRICK,
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
        lift = new Lift(opMode.hardwareMap, telemetry, "lift", false);

        retractIntake();
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

    private void deployIntake() {
        setExtendoPositionPreset(ExtendoPositions.EXTENDED);
        setIntakeWristPositionPreset(IntakeWristPositions.LOOK);
        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
        setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
        setIntakeLightPositionPreset(IntakeLightPositions.HIGH);
    }

    private void retractIntake() {
        setIntakeWristPositionPreset(IntakeWristPositions.UP);
        setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES0);
        setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
        setIntakeLightPositionPreset(IntakeLightPositions.OFF);
        setExtendoPositionPreset(ExtendoPositions.RETRACTED);
    }

    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.x) {
            setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_TIGHT);
        } else if (gamepad.a) {
            setIntakeGrabberPositionPreset(IntakeGrabberPositions.OPEN);
        } else if (gamepad.y) {
            setIntakeGrabberPositionPreset(IntakeGrabberPositions.MIDDLE);
        }

        if (gamepad.right_bumper) {
            extendo.extendSlowly(1.0);
        } else if (gamepad.left_bumper) {
            extendo.extendSlowly(-1.0);
        }

        if (gamepad.right_trigger > 0.2) {
            lift.up(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            lift.down(gamepad.left_trigger);
        } else {
            lift.stop();
        }

        if (gamepad.touchpad) {
            lift.lock();
        }

        if (gamepad.ps) {
            lift.unlock();
        }

    }

    public boolean isBusy() {
        return (lift.isBusy());
    }

    public void update() {
    }
}
