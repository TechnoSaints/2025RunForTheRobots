package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeLightPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;

public class Bot extends Component {
    private final ServoSimple extendo, intakeWrist, intakeSwivel, intakeGrabber, intakeLight;

    public Bot(OpMode opMode, Telemetry telemetry) {
        super(telemetry);
        extendo = new ServoSimple(opMode.hardwareMap, telemetry, "extendo");
        intakeWrist = new ServoSimple(opMode.hardwareMap, telemetry, "intakeWrist");
        intakeSwivel = new ServoSimple(opMode.hardwareMap, telemetry, "intakeSwivel");
        intakeGrabber = new ServoSimple(opMode.hardwareMap, telemetry, "intakeGrabber");
        intakeLight = new ServoSimple(opMode.hardwareMap, telemetry, "intakeLight");

        retractIntake();
    }

    public void setExtendoPosition(ExtendoPositions position) {
        extendo.setPositionTicks(position.getValue());
    }

    public void setIntakeWristPosition(IntakeWristPositions position) {
        intakeWrist.setPositionTicks(position.getValue());
    }

    public void setIntakeSwivelPosition(IntakeSwivelPositions position) {
        intakeSwivel.setPositionTicks(position.getValue());
    }

    public void setIntakeGrabberPosition(IntakeGrabberPositions position) {
        intakeGrabber.setPositionTicks(position.getValue());
    }

    public void setIntakeLightPosition(IntakeLightPositions position) {
        intakeLight.setPositionTicks(position.getValue());
    }


    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.x) {
            setIntakeGrabberPosition(IntakeGrabberPositions.CLOSED_TIGHT);
        } else if (gamepad.a) {
            setIntakeGrabberPosition(IntakeGrabberPositions.OPEN);
        } else if (gamepad.y) {
            setIntakeGrabberPosition(IntakeGrabberPositions.MIDDLE);
        }

        if (gamepad.right_bumper) {
            deployIntake();
        } else if (gamepad.left_bumper) {
            retractIntake();
        }
    }

    private void deployIntake() {
        setExtendoPosition(ExtendoPositions.EXTENDED);
        setIntakeWristPosition(IntakeWristPositions.LOOK);
        setIntakeSwivelPosition(IntakeSwivelPositions.DEGREES0);
        setIntakeGrabberPosition(IntakeGrabberPositions.OPEN);
        setIntakeLightPosition(IntakeLightPositions.HIGH);
    }


    private void retractIntake() {
        setIntakeWristPosition(IntakeWristPositions.UP);
        setIntakeSwivelPosition(IntakeSwivelPositions.DEGREES0);
        setIntakeGrabberPosition(IntakeGrabberPositions.CLOSED_TIGHT);
        setIntakeLightPosition(IntakeLightPositions.OFF);
        setExtendoPosition(ExtendoPositions.RETRACTED);
    }

    public void update() {
    }
}
