package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import dalvik.system.DelegateLastClassLoader;

public class ServoAngularSpeedLimited extends ServoAngular {

    private double incrementSizeDegrees = 0.01;
    private double incrementDelayMS, targetPositionDegrees, currentPositionDegrees, workingDegreesIncrement;
    private double positionTolerance = 0.005;
    private ElapsedTime controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    public ServoAngularSpeedLimited(HardwareMap hardwareMap, Telemetry telemetry, String servoName, double minPosDegrees, double ticksAtMinPosDegrees, double maxPosDegrees, double ticksAtMaxPosDegrees) {
        super(hardwareMap, telemetry, servoName, minPosDegrees, ticksAtMinPosDegrees, maxPosDegrees, ticksAtMaxPosDegrees);
        controlTimer.reset();
    }

    public void goToPositionDegrees(double position, double incrementDelayMS) {
        this.incrementDelayMS = incrementDelayMS;
        targetPositionDegrees = position;
        currentPositionDegrees = getPositionDegrees();
        if (targetPositionDegrees >= currentPositionDegrees) {
            workingDegreesIncrement = incrementSizeDegrees;
        } else {
            workingDegreesIncrement = -incrementSizeDegrees;
        }
        controlTimer.reset();
    }

    private boolean atTargetPosition() {
        return (Math.abs(targetPositionDegrees - currentPositionDegrees) <= positionTolerance);
    }

    public boolean isBusy() {
        return (!atTargetPosition());
    }

    public void update() {
        if (isBusy()) {
            if (controlTimer.milliseconds() >= incrementDelayMS) {
                currentPositionDegrees = currentPositionDegrees + workingDegreesIncrement;
                setPositionDegrees(currentPositionDegrees, 0);
                controlTimer.reset();
            }
        }
    }
}
