package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
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
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HIGH_BUCKET_SCORING);
                    setPathState(2);
                }
                break;

            // Drop brick into high bucket
            case 2:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(3);
                }
                break;

            // Move handler arm and go to spike 1
            case 3:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike1, true);
                    bot.setMode(Modes.HOVERING_OVER_BRICK);
                    setPathState(4);
                }
                break;

            case 4:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.PREPARING_TO_TRANSFER);
                    setPathState(5);
                }
                break;

            // Grab brick from spike1
            case 5:
                if (!bot.followerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKING_BRICK);
                    setPathState(6);
                }
                break;

            case 6:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setPathState(7);
                }
                break;

            case 7:
                if (!bot.intakeIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDING_OFF_BRICK);
                    bot.followPath(Paths.sampleSpike1ToBucket, true);
                    setPathState(8);
                }
                break;

            case 8:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    setPathState(9);
                }
                break;

            case 9:
                if (!bot.liftIsBusy()) {
                    bot.setMode(Modes.HIGH_BUCKET_SCORING);
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(11);
                }
                break;

            // Move handler arm and go to spike 2
            case 11:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HOVERING_OVER_BRICK);
                    bot.followPath(Paths.bucketToSampleSpike2, true);
                    setPathState(12);
                }
                break;

            // Grab brick from spike2
            case 12:
                if (!bot.followerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKING_BRICK);
                    setPathState(13);
                }
                break;

            case 13:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.PREPARING_TO_TRANSFER);
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setPathState(15);
                }
                break;

            case 15:
                if (!bot.isBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDING_OFF_BRICK);
                    setPathState(16);
                }
                break;

            case 16:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    bot.followPath(Paths.sampleSpike2ToBucket, true);
                    setPathState(17);
                }
                break;

            case 17:
                if (!bot.liftIsBusy()) {
                    bot.setMode(Modes.HIGH_BUCKET_SCORING);
                    setPathState(18);
                }
                break;

            case 18:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(19);
                }
                break;

            // Move handler arm and go to spike 3 setup
            case 19:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HOVERING_OVER_BRICK);
                    bot.followPath(Paths.bucketToSampleSpike3Setup, false);
                    setPathState(20);
                }
                break;

            // Rotate swivel, lower grabber, then strafe to spike 3
            case 20:
                if (!bot.followerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                    bot.setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, true);
                    setPathState(21);
                }
                break;

            // Grab brick from spike3
            case 21:
                if (!bot.followerIsBusy()) {
                    bot.setMode(Modes.INTAKING_BRICK);
                    setPathState(22);
                }
                break;

            case 22:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.PREPARING_TO_TRANSFER);
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    setPathState(23);
                }
                break;

            case 23:
                if (!bot.isBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDING_OFF_BRICK);
                    setPathState(24);
                }
                break;

            case 24:
                if (!bot.handlerIsBusy() && !bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    bot.followPath(Paths.sampleSpike3ToBucket, true);
                    setPathState(25);
                }
                break;

            case 25:
                if (!bot.liftIsBusy()) {
                    bot.setMode(Modes.HIGH_BUCKET_SCORING);
                    setPathState(26);
                }
                break;

            case 26:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(27);
                }
                break;

            case 27:
                if (!bot.handlerIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SUB_PARKING);
                    bot.followPath(Paths.bucketToSamplePark, true);
                    setPathState(28);
                }
                break;

            case 28:
                if (!bot.handlerIsBusy()) {
                    bot.setMode(Modes.PARKING_AT_SUB);
                    setPathState(29);
                }
                break;

            // deactivate and rest up for teleop
            case 29:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}
