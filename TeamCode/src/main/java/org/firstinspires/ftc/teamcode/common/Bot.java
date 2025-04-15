package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareData.GoBilda312DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ArmLeftServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.ArmRightServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.WristServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.GrabberServoData21528;
import org.firstinspires.ftc.teamcode.common.hardwareData.team21528.LiftData21528;
import org.firstinspires.ftc.teamcode.common.servos.ServoSimple;
import org.firstinspires.ftc.teamcode.common.servos.ServoSlowStop;

public class Bot extends Component {
    protected final LiftSingleMotor lift;
    private final ServoSimple grabber, wrist;
    protected ServoSlowStop armLeft, armRight;
    private boolean liftIsLocked;

    public Bot(OpMode opMode, Telemetry telemetry, double slowStopServoDelay) {
        super(telemetry);
        lift = new LiftSingleMotor(opMode.hardwareMap, telemetry, "lift", false, new GoBilda312DcMotorData(), new LiftData21528());
        grabber = new ServoSimple(opMode.hardwareMap, telemetry, "grabber", new GrabberServoData21528());
        armLeft = new ServoSlowStop(opMode.hardwareMap, telemetry, "armLeft", new ArmLeftServoData21528(), slowStopServoDelay);
        armRight = new ServoSlowStop(opMode.hardwareMap, telemetry, "armRight", new ArmRightServoData21528(), slowStopServoDelay);
        wrist = new ServoSimple(opMode.hardwareMap, telemetry, "wrist", new WristServoData21528());
        liftIsLocked = false;
        liftResetEncoder();
    }

    public void grabberClose() {
        grabber.close();
    }

    public void grabberOpen() {
        grabber.open();
    }

    public void grabberMiddle() {
        grabber.middle();
    }

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

    public void wristOpen() {
        wrist.open();
    }

    public void wristClose() {
        wrist.close();
    }

    public void wristMiddle() {
        wrist.middle();
    }

    public void wristSwing() {
        wrist.swing();
    }

    public void wristLook() {
        wrist.look();
    }

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

    public void processGamepadInput(Gamepad gamepad) {
        if (gamepad.right_trigger > 0.2) {
            liftUp(gamepad.right_trigger);
        } else if (gamepad.left_trigger > 0.2) {
            liftDown(gamepad.left_trigger);
        } else {
            liftStop();
        }

        if (gamepad.x) {
            grabberClose();
        } else if (gamepad.a) {
            grabberOpen();
        } else if (gamepad.y) {
            grabberMiddle();
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
    }

    public void update() {
        armLeft.update();
        armRight.update();
    }
}
