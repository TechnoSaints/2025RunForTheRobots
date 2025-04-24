package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.ExtendoData;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

public class Extendo extends Component {
    private ServoAngular servo;
    private double currentLength;
    private ElapsedTime timer;
    private final double slowMoveDelayMS = 1000;
    private final double slowMoveIncrement = 0.5;

    public Extendo(HardwareMap hardwareMap, Telemetry telemetry, String extendoName) {
        super(telemetry);
        double angleAtMinLength, angleAtMaxLength;
        angleAtMinLength = lengthToAngle(ExtendoData.minLengthInches);
        angleAtMaxLength = lengthToAngle(ExtendoData.maxLengthInches);
        servo = new ServoAngular(hardwareMap, telemetry, extendoName, angleAtMaxLength, ExtendoData.maxLengthTicks, angleAtMinLength, ExtendoData.minLengthTicks);

        timer = new ElapsedTime();
        timer.reset();

        goToPresetPosition(ExtendoPositions.RETRACTED);
    }

    public void goToPresetPosition(ExtendoPositions position) {
        goToLength(position.getValue());
    }

    private void goToLength(double targetPosInches) {
//        if (!stopAtLimit(targetPosInches)) {
//        telemetry.addData("targetPosInches: ", targetPosInches);
//        telemetry.addData("targetPosAngle: ", lengthToAngle(targetPosInches));

        servo.setPositionDegrees(lengthToAngle(targetPosInches));
        telemetry.addData("lengthToAngle(): ",lengthToAngle(targetPosInches));
        //currentLength = targetPosInches;
        currentLength = angleToLength(servo.getPositionDegrees());
        telemetry.addData("length: ",getCurrentLength());
//        telemetry.addData("targetPosInches: ", currentLength);
//        telemetry.addData("targetPosAngle: ", servo.getPositionDegrees());
//        telemetry.update();

        //        }
    }

    public void moveLinearDistance(double distanceInches) {
        goToLength(currentLength + distanceInches);
    }

    public void extendSlowly(double direction) {
        if (timer.milliseconds() > slowMoveDelayMS) {
            telemetry.addData("currentLength before: ", currentLength);
            telemetry.addData("increment+dir: ", (direction * slowMoveIncrement));
            goToLength(currentLength + (direction * slowMoveIncrement));
            telemetry.addData("currentLength after: ", currentLength);

            timer.reset();
        }
    }

//    private boolean stopAtLimit(double targetLengthInches) {
//        boolean atLimit = false;
//
//        if (targetLengthInches <= ExtendoData.minLengthInches) {
//            goToLength(ExtendoData.minLengthInches);
//            atLimit = true;
//        } else if (targetLengthInches >= ExtendoData.maxLengthInches) {
//            goToLength(ExtendoData.maxLengthInches);
//            atLimit = true;
//        }
//        return (atLimit);
//    }

    // my formula
    // d = l1cos(x)+sqrt(l2^2 - l1^2 + l2^2 * cos(x)^2)
    private double angleToLength(double angle) {
        double l1squared = Math.pow(ExtendoData.nearLinkageLengthInches, 2);
        double l2squared = Math.pow(ExtendoData.farLinkageLengthInches, 2);
        double cosx = Math.cos(Math.toRadians(angle));

        return ((ExtendoData.nearLinkageLengthInches * cosx) + Math.sqrt(l2squared - l1squared + l1squared * Math.pow(cosx, 2)));
    }

    // my formula
    // angle = arccos((d^2 + l1^2 - l2^2)/(2dl1))
    private double lengthToAngle(double length) {
        double temp = ((length * length) + (ExtendoData.nearLinkageLengthInches * ExtendoData.nearLinkageLengthInches) -
                (ExtendoData.farLinkageLengthInches * ExtendoData.farLinkageLengthInches));
        temp = temp / (2.0 * length * ExtendoData.nearLinkageLengthInches);

        return (Math.toDegrees(Math.acos(temp)));
    }

    public double getCurrentLength()
    {
        return (currentLength);
    }

    public double getCurrentAngle()
    {
        return (servo.getPositionDegrees());
    }

    public void update() {
    }

    public void log() {
        telemetry.addData("currentLength: ", getCurrentLength());
        telemetry.addData("lengthToAngle(): ", getCurrentAngle());
        telemetry.update();
    }
}