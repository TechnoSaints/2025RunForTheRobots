package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

public class Extendo extends Component {
    private ServoAngular servo;
    private double currentLength;
    private ElapsedTime timer;
    private final double slowMoveDelayMS = 1000;
    private final double slowMoveIncrement = 0.1;

    public Extendo(HardwareMap hardwareMap, Telemetry telemetry, String extendoName) {
        super(telemetry);
        double minLengthAngle, maxLengthAngle;
        minLengthAngle = angleForLength(ExtendoData.minLengthInches);
        maxLengthAngle = angleForLength(ExtendoData.maxLengthInches);
        timer = new ElapsedTime();

        servo = new ServoAngular(hardwareMap, telemetry, extendoName, minLengthAngle, ExtendoData.minLengthTicks, maxLengthAngle, ExtendoData.maxLengthTicks);
        goToLinearPosition(ExtendoData.minLengthInches);
    }

    public void goToLinearPosition(double targetPosInches) {
        if (!stopAtLimit(targetPosInches)) {
            servo.setPositionDegrees(angleForLength(targetPosInches));
            currentLength = targetPosInches;
        }
    }

    public void moveLinearDistance(double distanceInches) {
        goToLinearPosition(currentLength + distanceInches);
    }

    public void extendSlowly(double direction) {
        if (timer.milliseconds() > slowMoveDelayMS) {
            goToLinearPosition(currentLength + (direction * slowMoveIncrement));
            timer.reset();
        }
    }

    private boolean stopAtLimit(double targetLengthInches) {
        boolean atLimit = false;

        if (targetLengthInches <= ExtendoData.minLengthInches) {
            goToLinearPosition(ExtendoData.minLengthInches);
            atLimit = true;
        } else if (targetLengthInches >= ExtendoData.maxLengthInches) {
            goToLinearPosition(ExtendoData.maxLengthInches);
            atLimit = true;
        }
        return (atLimit);
    }

    // my formula
    // angle = arccos((d^2 + l1^2 - l2^2)/(2dl1))
    private double angleForLength(double targetLength) {
        double temp = ((targetLength * targetLength) + (ExtendoData.nearLinkageLengthInches * ExtendoData.nearLinkageLengthInches) -
                (ExtendoData.farLinkageLengthInches * ExtendoData.farLinkageLengthInches));
        temp = temp / (2.0 * targetLength * ExtendoData.nearLinkageLengthInches);
        return (Math.acos(temp));
    }

    public void update() {
    }
}