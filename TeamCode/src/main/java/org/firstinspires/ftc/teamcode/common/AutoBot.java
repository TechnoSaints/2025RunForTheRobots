package org.firstinspires.ftc.teamcode.common;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.data.DrivetrainData;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public class AutoBot extends Bot {
    private final Follower follower;
    public AutoBot(OpMode opMode, Telemetry telemetry) {
        super(opMode, telemetry);
        follower = new Follower(opMode.hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(FieldLocations.startPose);
    }
    public void followPath(PathChain path, boolean holdEnd) {
        follower.followPath(path, holdEnd);
    }

    public Follower getFollower() {
        return (follower);
    }

    public boolean isBusy()
    {
        return (super.isBusy() || followerIsBusy());
    }
    public boolean followerIsBusy() {
        return (follower.isBusy());
    }

    public void update() {
        super.update();
        follower.update();
    }
}
