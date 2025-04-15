package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConstants.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoSlowStop;

public class Bot extends Component {
    private Servo testServo;
    private final Servo intakeGrabber, intakeWrist, intakeSwivel;
    protected ServoSlowStop extendoLeft, extendoRight;
    public Bot(OpMode opMode, Telemetry telemetry, double slowStopServoDelay) {
        super(telemetry);
        intakeGrabber = opMode.hardwareMap.get(Servo.class, "intakeGrabber");
        intakeWrist = opMode.hardwareMap.get(Servo.class, "intakeWrist");
        intakeSwivel = opMode.hardwareMap.get(Servo.class, "intakeSwivel");

        extendoLeft = new ServoSlowStop(opMode.hardwareMap, telemetry, "extendoLeft", slowStopServoDelay);
        extendoRight = new ServoSlowStop(opMode.hardwareMap, telemetry, "extendoRight", slowStopServoDelay);

//        handlerGrabber = new Servo(opMode.hardwareMap, telemetry, "handlerGrabber", new HandlerGrabberServoData21528());
//        handlerWrist = new Servo(opMode.hardwareMap, telemetry, "handlerWrist", new HandlerWristServoData21528());
//        armLeft = new ServoSlowStop(opMode.hardwareMap, telemetry, "armLeft", new ArmLeftServoData21528(), slowStopServoDelay);
//        armRight = new ServoSlowStop(opMode.hardwareMap, telemetry, "armRight", new ArmRightServoData21528(), slowStopServoDelay);

//        lift = new LiftSingleMotor(opMode.hardwareMap, telemetry, "lift", false, new GoBilda435DcMotorData(), new LiftData21528());
//        liftIsLocked = false;
//        liftResetEncoder();
    }

    public void intakeGrabberSetPosition(IntakeGrabberPositions position)
    {
        intakeGrabber.setPosition(position.getValue());
    }
/*
    public void armOpen() {
        armLeft.open();
        armRight.open();
    }

    public void armClose() {
        armLeft.close();
        armRight.close();
    }

    public void armMiddle() {
        armLeft.middle();
        armRight.middle();
    }

    public void armSwing() {
        armLeft.swing();
        armRight.swing();
    }

    public void armLook() {
        armLeft.look();
        armRight.look();
    }

    public void armPwmDisable() {
        armLeft.pwmDisable();
        armRight.pwmDisable();
    }

    public void armPwmEnable() {
        armLeft.pwmEnable();
        armRight.pwmEnable();
    }

    public boolean armIsBusy() {
        return (armLeft.isBusy() || armRight.isBusy());
    }

    public void intakeWristOpen() {
        intakeWrist.open();
    }

    public void intakeWristClose() {
        intakeWrist.close();
    }

    public void intakeWristMiddle() {
        intakeWrist.middle();
    }

    public void intakeWristSwing() {
        intakeWrist.swing();
    }

    public void intakeWristLook() {
        intakeWrist.look();
    }
/*
    public void liftUp(double speed) {
        lift.up(speed);
    }

    public void liftDown(double speed) {
        lift.down(speed);
    }

    public void liftHigh() {
        lift.high();
    }

    public void liftMedium() {
        lift.medium();
    }

    public void liftLow() {
        lift.low();
    }

    public void liftMin() {
        lift.min();
    }

    public void liftStop() {
        lift.stop();
    }

    public void liftLock() {
        lift.lock();
    }

    public void liftUnlock() {
        lift.unlock();
    }

    public void liftMoveDownToSwitch() {
        lift.moveDownToSwitch();
    }

    public void liftResetEncoder() {
        lift.resetEncoder();
    }

    public boolean liftIsBusy() {
        return (lift.isBusy());
    }

    public void increaseLiftMax(int increment) {
        lift.increaseMax(increment);
    }
*/
    public void processGamepadInput(Gamepad gamepad) {
        /*
        if (gamepad.right_trigger > 0.2) {
            liftUp(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            liftDown(gamepad.left_trigger);
        } else {
            liftStop();
        }

        if (gamepad.x) {
            intakeGrabberClose();
        } else if (gamepad.a) {
            intakeGrabberOpen();
        } else if (gamepad.y) {
            intakeGrabberMiddle();
        }

        if (gamepad.left_bumper) {
            armClose();
            wristClose();
        } else if (gamepad.right_bumper) {
            armMiddle();
            wristClose();
        }

        if (gamepad.b) {
            armSwing();
            wristSwing();
        }

        if ((gamepad.start) && (gamepad.share)) {
            liftMoveDownToSwitch();
            liftResetEncoder();
        }

        if (gamepad.touchpad || liftIsLocked == true) {
            liftLock();
            liftIsLocked = true;
        }

        if (gamepad.ps) {
            liftUnlock();
            liftIsLocked = false;
        }
*/
    }

    public void update() {
        extendoLeft.update();
        extendoRight.update();
//        armLeft.update();
//        armRight.update();
    }
}
