package org.firstinspires.ftc.teamcode.common;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.GoBilda435DcMotorData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.LiftData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;

@Config
public class Lift extends Component {
    private final DcMotorEx liftMotorL;
    private final DcMotorEx liftMotorR;
    private final PIDFController pidfL;
    private final PIDFController pidfR;
    public static double kP = 0.02;
    //original kP = 0.025
    public static double kI = 0.0;
    public static double kD = 0.0002;
    //original kD = 0.0005
    public static double kF = 0.0001;

    private final GoBilda435DcMotorData motorData = new GoBilda435DcMotorData();
    private final double maxVelocity = motorData.maxTicksPerSec;

    private final LiftData liftData = new LiftData();
    private final int maxPosition, maxMovePower, stopPower, lockPower, minPosition, manualOffset, autoOffset;
    private final int positionTolerancePID = 10;
    int direction = 1;
    int targetPosition = 0;
    DcMotorEx motorL, motorR;
    private int autoOffset = 0;
    private int manualOffset = 25;

    public Lift(HardwareMap hardwareMap, Telemetry telemetry, String motorNameL, String motorNameR, boolean reverseMotor) {
        super(telemetry);
        maxVelocity = motorData.maxTicksPerSec;
        maxMovePower = liftData.maxMovePower;
        stopPower = liftData.stopPower;
        lockPower = liftData.lockPower;
        maxPosition = LiftPositions.MAX.getValue();
        manualOffset = liftData.teleopTolerance;
        autoOffset = liftData.autoTolerance;
        minPosition = LiftPositions.MIN.getValue();

        positionTolerancePID = autoOffset;

        pidfL = new PIDFController(kP, kI, kD, kF);
        pidfL.setTolerance(positionTolerancePID);
        pidfR = new PIDFController(kP, kI, kD, kF);
        pidfR.setTolerance(positionTolerancePID);

        liftMotorL = hardwareMap.get(DcMotorEx.class, motorNameL);
        liftMotorR = hardwareMap.get(DcMotorEx.class, motorNameR);

//        liftMotorL.setTargetPositionTolerance(autoOffset);
//        liftMotorR.setTargetPositionTolerance(autoOffset);

        liftMotorL.setDirection(DcMotor.Direction.FORWARD);
        liftMotorR.setDirection(DcMotor.Direction.REVERSE);

        liftMotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftMotorL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotorR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (reverseMotor) {
            direction = -1;
        } else {
            direction = 1;
        }

        targetPosition = 0;
    }

    public void up(double targetPower) {
        if (!atTop(manualOffset)) {
            setMotorsPower(targetPower);
        } else {
            stop();
        }
    }

    public void down(double targetPower) {
        if (!atBottom(manualOffset)) {
            setMotorsPower(-targetPower);
        } else {
            stop();
        }
    }

    public void stop() {
        setTargetPosition(liftMotorL.getCurrentPosition());
    }

    public void setPositionPreset(LiftPositions position) {
        setTargetPosition(position.getValue());
    }

    public void setTargetPosition(int targetPosition) {
        if (targetPosition < minPosition) {
            targetPosition = minPosition;
        } else if (targetPosition > maxPosition) {
            targetPosition = maxPosition;
        } else {
            targetPosition = this.targetPosition;
        }
    }

    private boolean atTop(int offset) {
        if ((liftMotorL.getCurrentPosition() - offset) >= maxPosition) {
            return true;
        } else {
            return false;
        }
    }


    private boolean atBottom(int offset) {
        if ((liftMotorL.getCurrentPosition() + offset) <= minPosition) {
            return true;
        } else {
            return false;
        }
    }

    public void setMotorsPower(double power) {
        liftMotorL.setPower(power * maxMovePower);
        liftMotorR.setPower(power * maxMovePower);
    }

    public void setLMotorPower(double power) {
        liftMotorL.setPower(power * maxMovePower);
    }

    public void setRMotorPower(double power) {
        liftMotorR.setPower(power * maxMovePower);
    }

    public boolean isBusy() {
        return (!pidfL.atSetPoint() || !pidfR.atSetPoint());
    }

    private void setPIDFMotorPower() {
        setLMotorPower(pidfL.calculate(liftMotorL.getCurrentPosition(), targetPosition));
        setRMotorPower(pidfR.calculate(liftMotorR.getCurrentPosition(), targetPosition));
    }

    public void update() {
//        pidfL.setPIDF(kP,kI,kD,kF);
//        pidfR.setPIDF(kP,kI,kD,kF);
    }

    private void logTelemetry() {
        telemetry.addData("PositionL:  ", liftMotorL.getCurrentPosition());
        telemetry.addData("PositionR:  ", liftMotorR.getCurrentPosition());
        telemetry.addData("Target:  ", targetPosition);
        telemetry.addData("PowerL:  ", liftMotorL.getPower());
        telemetry.addData("PowerR:  ", liftMotorR.getPower());
        telemetry.addData("Busy:  ", isBusy());
        telemetry.update();
    }


}