package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Bucket x 4", group = "Bucket")
public class Bucketx4 extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
                bot.setMode(Modes.SCORING_SAMPLE);
                bot.followPath(Paths.startToBucket, true);
                setPathState(1);
                break;

            // move to the sub pose
            case 1:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(2);
                }
                break;

            // hang the specimen for a score, then transition to park position
            case 2:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike1, true);
                    setPathState(3);
                }
                break;

            case 3:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    bot.followPath(Paths.sampleSpike1ToBucket, true);
                    setPathState(4);
                }
                break;

            // hang the specimen for a score
            case 4:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike2, true);
                    setPathState(5);
                }
                break;

            case 5:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    bot.followPath(Paths.sampleSpike2ToBucket, true);
                    setPathState(6);
                }
                break;

            case 6:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike3Setup, true);
                    setPathState(7);
                }
                break;

            case 7:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, true);
                    setPathState(8);
                }
                break;

            case 8:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.sampleSpike3ToBucket, true);
                    setPathState(9);
                }
                break;

            // deactivate and rest up for teleop
            case 9:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

