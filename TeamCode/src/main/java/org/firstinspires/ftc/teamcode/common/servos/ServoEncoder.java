package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareData.ServoData;

public class ServoEncoder extends ServoSimple {
    private final AnalogInput servoEncoder;
    private double setPoint;
    private double controlPosition;
    private double tolerance = 0.008;
    private double increment = 0.015;
    private double delay = 50;
    private ElapsedTime timer;

    public ServoEncoder(HardwareMap hardwareMap, Telemetry telemetry, String servoName, String encoderName, ServoData servoData) {
        super(hardwareMap, telemetry, servoName, servoData);
        timer = new ElapsedTime();
        timer.reset();
        servoEncoder = hardwareMap.get(AnalogInput.class, encoderName);
        open();
        controlPosition = setPoint;
    }

    public void open() {
        servo.setPosition(openPosition);
    }

    public void close() {
        servo.setPosition(closePosition);

    }

    public void middle() {
        servo.setPosition(middlePosition);
    }

    public void swing() {
        servo.setPosition(swingPosition);
    }

    public void look() {
        servo.setPosition(lookPosition);
    }

    private double getEncoderPosition() {
        double distanceFromMiddle = 0.5 - (servoEncoder.getVoltage() / 3.3);
        double distanceScaled = distanceFromMiddle / 0.5203;
        return (0.5 + distanceScaled);
    }

    private boolean atSetPoint() {
        return (Math.abs(servo.getPosition() - getEncoderPosition()) < tolerance);
    }

    public boolean isBusy() {
        return (!atSetPoint());
    }

    public void update() {
        log();
    }

    /*
    // Move in small increments, with a delay between each
    public void update() {
        if (!atSetPoint()) {
            if (timer.milliseconds() > delay) {
                if (setPoint > controlPosition) {
                    controlPosition = controlPosition + increment;
                } else {
                    controlPosition = controlPosition - increment;
                }
                servo.setPosition(controlPosition);
            }
        }
        log();
    }
     */

    public void log() {
        telemetry.addData("isBusy: ", isBusy());
        telemetry.addData("User Set Point: ", setPoint);
        telemetry.addData("Encoder Position: ", getEncoderPosition());
        telemetry.addData("Control Position: ", controlPosition);
        telemetry.update();
    }
}