package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

public class Extendo extends Component {
    private ServoAngular servo;
    private ElapsedTime controlTimer;

    private final double totalSlowExtensionTimeMS = 2500.0;
    private final double totalMediumExtensionTimeMS = 1000.0;
    private final double moveIncrementInches = 0.1;
    private final ExtendoData extendoData = new ExtendoData();
    private final double slowMoveDelayMS = (totalSlowExtensionTimeMS * moveIncrementInches) / (extendoData.maxLengthInches - extendoData.minLengthInches);
    private final double mediumMoveDelayMS = (totalMediumExtensionTimeMS * moveIncrementInches) / (extendoData.maxLengthInches - extendoData.minLengthInches);

    private final double lengthToleranceInches = moveIncrementInches / 5.5;

    private double targetLengthInches, currentLengthInches, currentMoveDelayMS;
    private int direction = 1;

    public Extendo(HardwareMap hardwareMap, Telemetry telemetry, String extendoName) {
        super(telemetry);
        double angleAtMinLength, angleAtMaxLength;
        angleAtMinLength = lengthToAngle(extendoData.minLengthInches);
        angleAtMaxLength = lengthToAngle(extendoData.maxLengthInches);
        servo = new ServoAngular(hardwareMap, telemetry, extendoName, angleAtMaxLength, extendoData.maxLengthTicks, angleAtMinLength, extendoData.minLengthTicks);

        controlTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        controlTimer.reset();
        currentLengthInches = angleToLength(servo.getPositionDegrees());
        targetLengthInches = currentLengthInches;
        setPositionPreset(ExtendoPositions.RETRACTED);
    }

    public void setPositionPreset(ExtendoPositions position) {
        if (position.getValue() >= currentLengthInches) {
            direction = 1;
        } else {
            direction = -1;
        }
        goToLength(position.getValue());
    }

    public void extendSlowly(int direction) {
        if (controlTimer.milliseconds() > currentMoveDelayMS) {
            this.direction = direction;
            goToLength(currentLengthInches + (direction * moveIncrementInches));
        }
    }

    private void setSlow() {
        currentMoveDelayMS = slowMoveDelayMS;
    }

    private void setMedium() {
        currentMoveDelayMS = mediumMoveDelayMS;
    }

    private void goToLength(double targetLengthInches) {
        this.targetLengthInches = targetLengthInches;
        controlTimer.reset();
    }

    // my formula
    // d = l1cos(x)+sqrt(l2^2 - l1^2 + l2^2 * cos(x)^2)
    private double angleToLength(double angle) {
        double l1squared = Math.pow(extendoData.nearLinkageLengthInches, 2);
        double l2squared = Math.pow(extendoData.farLinkageLengthInches, 2);
        double cosx = Math.cos(Math.toRadians(angle));

        return ((extendoData.nearLinkageLengthInches * cosx) + Math.sqrt(l2squared - l1squared + l1squared * Math.pow(cosx, 2)));
    }

    // my formula
    // angle = arccos((d^2 + l1^2 - l2^2)/(2dl1))
    private double lengthToAngle(double length) {
        double temp = ((length * length) + (extendoData.nearLinkageLengthInches * extendoData.nearLinkageLengthInches) -
                (extendoData.farLinkageLengthInches * extendoData.farLinkageLengthInches));
        temp = temp / (2.0 * length * extendoData.nearLinkageLengthInches);

        return (Math.toDegrees(Math.acos(temp)));
    }

    private double getCurrentLength() {
        return (currentLengthInches);
    }

    private double getCurrentAngle() {
        return (servo.getPositionDegrees());
    }

    private boolean atTarget() {
        return (Math.abs(targetLengthInches - currentLengthInches) <= lengthToleranceInches);
    }

    public boolean isBusy() {
        return (!atTarget());
    }

    public void update() {
        if (isBusy()) {
            if (controlTimer.milliseconds() >= currentMoveDelayMS) {
                currentLengthInches = currentLengthInches + direction * moveIncrementInches;
                servo.setPositionDegrees(lengthToAngle(currentLengthInches), 0);
                controlTimer.reset();
            }
        }
    }

    public void log() {
        telemetry.addData("currentLength: ", getCurrentLength());
        telemetry.addData("lengthToAngle(): ", getCurrentAngle());
        telemetry.update();
    }
}