package org.firstinspires.ftc.teamcode.opmode.test;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpMode;

@Autonomous(name = "WebcamNavigationTest", group = "Test")
@Disabled
public class WebcamNavigationTest extends AutoOpMode {
    private Pose grabPose, currentPose;
    private PathChain grab, retreat;
    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                dropPosition();
                setPathState(1);
                break;

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
                    follower.followPath(parkSubSetup);
                    setPathState(3);
                }
                break;

            case 3:
                if (!isBusy()) {
                    follower.followPath(look);
                    setPathState(4);
                }
                break;

            case 4:
                if (!isBusy()) {
/*                    bot.updateFilteredArmcamResult(250);
                    telemetry.addData("Forward Distance (inches): ", bot.armcamForwardDistanceToTargetInches());
                    telemetry.addData("Left Distance (inches): ", bot.armcamLeftDistanceToTargetInches());
                    telemetry.update();
                    sleepSynch(1500);
                    currentPose = follower.getPose();
                    grabPose = bot.getArmcamOffsetPose(follower.getPose());
                    grab = follower.pathBuilder()
                            .addPath(new BezierLine(new Point(currentPose), new Point(grabPose)))
                            .setLinearHeadingInterpolation(currentPose.getHeading(), grabPose.getHeading())
                            .build();
                    follower.followPath(grab, true);

 */
                    setPathState(5);
                }
                break;

            case 5:
                if (!isBusy()) {
                    pickupPosition();
                    setPathState(6);
                }
                break;

            case 6:
                if (!isBusy()) {
                    grabSample();
                    currentPose = follower.getPose();
                    retreat = follower.pathBuilder()
                            .addPath(new BezierLine(new Point(currentPose), new Point(parkSetupPose)))
                            .setLinearHeadingInterpolation(currentPose.getHeading(), parkSetupPose.getHeading())
                            .build();
                    sleepSynch(250);
                    follower.followPath(retreat);
                    setPathState(7);
                }
                break;

            case 7:
                if (!isBusy()) {
                    dropPosition();
                }
                setPathState(8);
                break;

            case 8:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(250);
                    setPathState(-1);
                }
                break;
        }
    }
}



