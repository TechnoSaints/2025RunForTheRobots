package org.firstinspires.ftc.teamcode.common.vision;

import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Component;

abstract public class VisionSensor extends Component {
    protected double targetHeightInches, verticalHeightInches;          // Distance from the ground for calculation
    protected double totalVerticalAngleDegrees, totalHorizontalAngleDegrees;
    protected double forwardDistanceToTargetInches, leftDistanceToTargetInches;
    protected double forwardMinInches;
    protected double forwardMaxInches;
    protected double leftMaxInches;
    protected double leftMinInches;
    protected ElapsedTime timer = new ElapsedTime();
    protected double grabberLeftOffsetInches;
    protected double grabberForwardOffsetInches;

    public VisionSensor(Telemetry telemetry) {
        super(telemetry);
        //start();
        timer.reset();
    }

    abstract public boolean updateFilteredResult(double maxSenseTimeMS);

    protected boolean isInRange(double value, double lower, double upper) {
        return ((value > lower) && (value < upper));
    }

    public double getForwardDistanceToTargetInches() {
        return forwardDistanceToTargetInches;
    }

    public double getLeftDistanceToTargetInches() {
        return leftDistanceToTargetInches;
    }

    abstract public void updateResult();

    abstract protected boolean resultIsValid();

    abstract public void start();

    abstract public void stop();

    public Pose getOffsetPose(Pose currentPose) {
        Pose newPose = new Pose();

        newPose.setX(currentPose.getX() + forwardDistanceToTargetInches - grabberForwardOffsetInches);
        telemetry.addData("Forward offset - forwardtarget: ", forwardDistanceToTargetInches - grabberForwardOffsetInches);
        newPose.setY(currentPose.getY() + leftDistanceToTargetInches - grabberLeftOffsetInches);
        telemetry.addData("left offset - lefttarget: ", leftDistanceToTargetInches - grabberLeftOffsetInches);

        newPose.setHeading(currentPose.getHeading());

        return (newPose);
    }
}




