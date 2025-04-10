package org.firstinspires.ftc.teamcode.opmode.test;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "LimelightNavigationTest-Long", group = "Test")

public class LimelightNavigationTestLong extends AutoOpMode {
    private Pose grabPose, currentPose;
    private PathChain grab, retreat;
    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                dropPosition();
                setPathState(3);
                break;
/*
            case 1:
                if (!isBusy()) {
                    follower.followPath(preloadScore);
                    setPathState(2);
                }

            case 2:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(250);
                    approachPosition();
                    follower.followPath(parkSetup);
                    setPathState(3);
                }
                break;
*/
            case 3:
                if (!isBusy()) {
                    follower.followPath(look);
                    setPathState(4);
                }
                break;

            case 4:
//                if (!isBusy()) {
//                    bot.updateFilteredLimelightResult(250);
//                    currentPose = follower.getPose();
//                    grabPose = bot.getLimelightOffsetPose(follower.getPose());
//                    grab = follower.pathBuilder()
//                            .addPath(new BezierLine(new Point(currentPose), new Point(grabPose)))
//                            .setLinearHeadingInterpolation(currentPose.getHeading(), grabPose.getHeading())
//                            .build();
//                    follower.followPath(grab, true);
//                    setPathState(5);
//                }
//                break;

            case 5:
                if (!isBusy()) {
                    pickupPosition();
                    setPathState(6);
                }
                break;

            case 6:
                if (!isBusy()) {
                    sleepSynch(250);
                    grabSample();
                    sleepSynch(250);
                    currentPose = follower.getPose();
                    retreat = follower.pathBuilder()
                            .addPath(new BezierLine(new Point(currentPose), new Point(parkSetupPose)))
                            .setLinearHeadingInterpolation(currentPose.getHeading(), parkSetupPose.getHeading())
                            .build();
                    follower.followPath(retreat);
                    setPathState(7);
                }
                break;

            case 7:
                if (!isBusy()) {
                    dropPosition();
                    setPathState(8);
                }
                break;

            case 8:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(250);
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}



