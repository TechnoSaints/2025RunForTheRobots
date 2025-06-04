package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Bucket x 4", group = "Bucket")
public class Bucketx4 extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Go to high bucket scoring config and pose
            case 0:
                bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                bot.followPath(Paths.startToBucket, true);
                setPathState(1);
                break;

            case 1:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    setPathState(2);
                }
                break;

            // Drop brick into high bucket
            case 2:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(16);
                }
                break;

            // Go to floor grabbing config and spike1 pose
            case 3:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike1, true);
                    setPathState(4);
                }
                break;

            // Grab brick from spike1
            case 4:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    setPathState(5);
                }
                break;

            // Move to high bucket score pose
            case 5:
                if (!bot.isBusy()) {
                    bot.followPath(Paths.sampleSpike1ToBucket, true);
                    setPathState(6);
                }
                break;

            // Drop brick into bucket
            case 6:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(7);
                }
                break;

            // Go to floor grabbing config and spike2 pose
            case 7:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike2, true);
                    setPathState(8);
                }
                break;

            // Grab brick from spike2
            case 8:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    setPathState(9);
                }
                break;

            // Go to high bucket scoring pose
            case 9:
                if (!bot.isBusy()) {
                    bot.followPath(Paths.sampleSpike2ToBucket, true);
                    setPathState(10);
                }
                break;

            // Drop brick into bucket
            case 10:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(11);
                }
                break;

            // Go to floor grabbing config and spike3 setup pose
            case 11:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK_FROM_FLOOR);
                    bot.followPath(Paths.bucketToSampleSpike3Setup, true);
                    setPathState(12);
                }
                break;

            // Go to spike3 pose
            case 12:
                if (!bot.isBusy()) {
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, true);
                    setPathState(13);
                }
                break;

            // Grab brick from spike3
            case 13:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.SCORING_SAMPLE);
                    setPathState(14);
                }
                break;

            // Go to high bucket scoring pose
            case 14:
                if (!bot.isBusy()) {
                    bot.followPath(Paths.sampleSpike3ToBucket, true);
                    setPathState(15);
                }
                break;

            // Drop brick into bucket
            case 15:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(16);
                }
                break;

            // Go to teleop start config
            case 16:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.TELEOP_START);
                }
                break;

            case 17:
                if (!bot.isBusy()) {
                    setPathState(-1);
                }
                break;
        }
    }
}

