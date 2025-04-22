package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

public class Lift extends Component {
    private final DcMotorEx motor;
    private TouchSensor liftSwitch;
    private final double maxVelocity;
    private final double maxMovePower;
    private final double stopPower;
    private int maxPosition;
    private final int tolerance;
    private final int minPosition;
    private double targetVelocity;
    private final int highPosition;
    private final int mediumPosition;
    private final int lowPosition;
    private int direction = 1;
    private final double lockPower = -0.65;
    private boolean stopped = true;

    public Lift(HardwareMap hardwareMap, Telemetry telemetry, String motorName, boolean reverseMotor) {
        super(telemetry);
        maxVelocity = GoBilda435DcMotorData.maxTicksPerSec;
        maxMovePower = LiftData.maxMovePower;
        stopPower = LiftData.stopPower;
        maxPosition = LiftPositions.MAX.getValue();
        tolerance = LiftData.tolerance;
        minPosition = LiftPositions.MIN.getValue();
        highPosition = LiftPositions.HIGH.getValue();
        mediumPosition = LiftPositions.MEDIUM.getValue();
        lowPosition = LiftPositions.LOW.getValue();
        long prevTime;
        int prevPosition;
        liftSwitch = hardwareMap.get(TouchSensor.class, "liftSwitch");
        motor = hardwareMap.get(DcMotorEx.class, motorName);

        if (reverseMotor) {
            direction = -1;
//            motor.setDirection(DcMotor.Direction.REVERSE);
        } else {
//            motor.setDirection(DcMotorSimple.Direction.FORWARD);
            direction = 1;
        }
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stopped = true;
    }

    public void stop() {
        stopAtPosition(motor.getCurrentPosition());
    }

    public void up(double targetPower) {
        if (!stoppedAtTop()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * targetPower * maxMovePower * maxVelocity;
//            motor.setPower(targetPower);
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Top: ", "false");
        } else {
            telemetry.addData("Stopped at Top: ", "true");
        }
        log();
    }

    public void down(double targetPower) {
        if (!stoppedAtBottom()) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            targetVelocity = direction * -targetPower * maxMovePower * maxVelocity;
//            motor.setPower(targetPower);
            motor.setVelocity(targetVelocity);
            telemetry.addData("Stopped at Bottom: ", " false");
        } else {
            telemetry.addData("Stopped at Bottom: ", " true");
        }
        log();
    }

    public void high() {
        stopAtPosition(highPosition);
        log();
    }

    public void medium() {
        stopAtPosition(mediumPosition);
        log();
    }

    public void low() {
        stopAtPosition(lowPosition);
        log();
    }

    public void min() {
        stopAtPosition(minPosition);
        log();
    }

    public void increaseMax(int increment)
    {
        maxPosition = maxPosition + increment;
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

    private void stopAtPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(stopPower);
        log();
    }

    public void resetEncoder() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveDownToSwitch() {
        down(0.2);
        while (!liftSwitch.isPressed()) {
        }
        stop();
        log();
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
//        prevTime = timer.milliseconds();
//        prevPosition = motor.getCurrentPosition();
        telemetry.addData("Position:  ", motor.getCurrentPosition());
//        telemetry.addData("targetVelocity: ", targetVelocity);
//        opMode.sleep(1000);
//        telemetry.addData("Velocity: ", ((motor.getCurrentPosition() - prevPosition) * 1000) / (timer.milliseconds() - prevTime));
        telemetry.update();
    }
}