package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "4 Sample Auto", group = "Auto")
public class Auto4Sample extends AutoOpMode {

    @Override
    public void start() {
        Paths.buildSamplePaths(bot.getFollower());
        Paths.buildHybridPaths(bot.getFollower());
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to drop position
            case 0:
                dropPosition();
                setPathState(1);
                break;

            // move to the basket
            case 1:
                if (!isBusy()) {
                    bot.followPath(Paths.startToBucket, true);
                    setPathState(2);
                }
                break;

            // drop the sample for a score, then transition to approach position
            case 2:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(100);
                    approachPosition();
                    setPathState(3);
                }
                break;

            // go to spike1 for pickup
            case 3:
                if (!isBusy()) {
                    bot.followPath(Paths.bucketToSampleSpike1, true);
                    setPathState(4);
                }
                break;

            // transition to pickup position
            case 4:
                if (!isBusy()) {
                    pickupPosition();
                    setPathState(5);
                }
                break;

            // pick up sample at spike1, begin transitioning to drop position, and
            // then begin moving toward basket to score
            case 5:
                if (!isBusy()) {
                    sleepSynch(200);
//                    bot.grabberClose();
                    sleepSynch(200);
                    dropPosition();
                    bot.followPath(Paths.sampleSpike1ToBucket, true);
                    setPathState(6);
                }
                break;

            // drop the sample for a score, then transition to approach position
            case 6:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(100);
                    approachPosition();
                    setPathState(7);
                }
                break;

            // go to spike2 for pickup
            case 7:
                if (!isBusy()) {
                    bot.followPath(Paths.bucketToSampleSpike2, true);
                    setPathState(8);
                }
                break;

            // transition to pickup position
            case 8:
                if (!isBusy()) {
                    pickupPosition();
                    setPathState(9);
                }
                break;

            // pick up sample at spike2, begin transitioning to drop position, and
            // then begin moving toward basket to score
            case 9:
                if (!isBusy()) {
                    sleepSynch(200);
//                    bot.grabberClose();
                    sleepSynch(200);
                    dropPosition();
                    bot.followPath(Paths.getSampleSpike2ToBucket, true);
                    setPathState(10);
                }
                break;

            // drop the sample for a score, then transition to approach position
            case 10:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(100);
                    approachPosition();
                    setPathState(11);
                }
                break;

            // go to spike3 setup position
            case 11:
                if (!isBusy()) {
                    bot.followPath(Paths.bucketToSampleSpike3Setup, false);
                    setPathState(12);
                }
                break;

            // move to spike3 pickup pose
            case 12:
                if (!isBusy()) {
                    sample3SetupPosition();
                    setPathState(13);
                }
                break;

            case 13:
                if (!isBusy()) {
                    bot.followPath(Paths.sampleSpike3SetupToSpike3, true);
                    setPathState(14);
                }
                break;

            // transition to pickup position
            case 14:
                paused = false;
                if (!isBusy()) {
                    pickupPosition();
                    setPathState(15);
                }
                break;

            // pick up sample at spike3, then move to spike3 setup2 position
            case 15:
                if (!isBusy()) {
                    sleepSynch(200);
                    grabSample();
                    sleepSynch(200);
                    dropPosition();
                    bot.followPath(Paths.sampleSpike3, true);
                    setPathState(17);
                }
                break;

            // drop the sample into the basket for a score,
            // then begin moving toward the park setup position
            case 17:
                if (!isBusy()) {
                    dropSample();
                    sleepSynch(100);
                    approachPosition();
                    bot.followPath(parkSubSetup, false);
                    setPathState(18);
                }
                break;

            // go to park position pose
            case 18:
                if (!isBusy()) {
                    bot.followPath(parkSub, false);
                    setPathState(19);
                }
                break;

            // go to park position
            case 19:
                if (!isBusy()) {
                    parkPosition();
                    setPathState(20);
                }
                break;

            case 20:
                if (!isBusy()) {
                    bot.armPwmDisable();
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

