package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.MotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

public class Lift extends Component {
    private final DcMotorEx motor;
    private final double maxVelocity;
    private final double maxMovePower;
    private final double stopPower;
    private final int maxPosition;
    private final int tolerance;
    private final int minPosition;
    private double targetVelocity;
    private int direction = 1;
    private final double lockPower;

    private final GoBilda435DcMotorData motorData = new GoBilda435DcMotorData();

    private final LiftData liftData = new LiftData();

    public Lift(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor) {
        super(telemetry);
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        lockPower = liftData.lockPower;
        maxPosition = LiftPositions.MAX.getValue();
        tolerance = liftData.tolerance;
        minPosition = LiftPositions.MIN.getValue();
        motor = hardwareMap.get(DcMotorEx.class, motorName);

        if (reverseMotor) {
            direction = -1;
        } else {
            direction = 1;
        }
        resetEncoder();
    }

    public void stop() {
        stopAtPosition(motor.getCurrentPosition());
    }

    public void up(double targetPower) {
        if (!stoppedAtTop()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * targetPower * maxMovePower * maxVelocity;
            motor.setVelocity(targetVelocity);

            telemetry.addData("Stopped at Top: ", "false");
        } else {
            telemetry.addData("Stopped at Top: ", "true");
        }
//        log();
    }

    public void down(double targetPower) {
        if (!stoppedAtBottom()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * -targetPower * maxMovePower * maxVelocity;
            motor.setVelocity(targetVelocity);

            telemetry.addData("Stopped at Bottom: ", " false");
        } else {
            telemetry.addData("Stopped at Bottom: ", " true");
        }
    }

    public void setPositionPreset(LiftPositions position) {
        setPositionTicks(position.getValue());
    }

    private void setPositionTicks(int ticks) {
        stopAtPosition(ticks);
    }

    private void stopAtPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
    }

    private boolean stoppedAtTop() {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        if (currentPosition > (maxPosition - tolerance)) {
            stop = true;
            stopAtPosition(maxPosition);
        }
        return stop;
    }

    private boolean stoppedAtBottom() {
        boolean stop = false;
        int currentPosition = motor.getCurrentPosition();
        if (currentPosition < (minPosition - tolerance)) {
            stop = true;
            stopAtPosition(minPosition);
        }
        return stop;
    }

    private void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void lock() {
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(lockPower);
    }

    public void unlock() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stop();
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public void log() {
        telemetry.addData("isBusy(): ", isBusy());
        telemetry.addData("Position:  ", motor.getCurrentPosition());
        telemetry.update();
    }
}