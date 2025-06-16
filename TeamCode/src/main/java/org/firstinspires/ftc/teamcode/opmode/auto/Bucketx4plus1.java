package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeWristPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Bucket x 4 + 1", group = "Bucket")
public class Bucketx4plus1 extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // Go to high bucket scoring config and pose
            case 0:
                bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                bot.followPath(Paths.startToBucket, false);
                setPathState(1);
                break;

            case 1:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
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

            // Go to spike 1
            case 3:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike1, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(4);
                }
                break;

            case 4:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
                    setPathState(5);
                }
                break;

            // Grab brick from spike1
            case 5:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK);
                    setPathState(6);
                }
                break;

            case 6:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike1ToBucket, false);
                    setPathState(7);
                }
                break;

            case 7:
                if (!bot.intakeIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(8);
                }
                break;

            case 8:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                    setPathState(9);
                }
                break;

            case 9:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(11);
                }
                break;

            // Go to spike 2
            case 11:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike2, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(12);
                }
                break;

            case 12:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
                    setPathState(13);
                }
                break;

            // Grab brick from spike2
            case 13:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK);
                    setPathState(14);
                }
                break;

            case 14:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike2ToBucket, false);
                    setPathState(15);
                }
                break;

            case 15:
                if (!bot.isBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(16);
                }
                break;

            case 16:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    setPathState(17);
                }
                break;

            case 17:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
                    setPathState(18);
                }
                break;

            case 18:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(19);
                }
                break;

            // Go to spike 3 setup
            case 19:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleSpike3Setup, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(20);
                }
                break;

            case 20:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
                    setPathState(21);
                }
                break;

                // Rotate swivel, lower grabber, then strafe to spike 3
            case 21:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                    bot.setIntakeWristPositionPreset(IntakeWristPositions.DOWN);
                    bot.followPath(Paths.sampleSpike3SetupToSampleSpike3, false);
                    setPathState(22);
                }
                break;

            case 22:
                if (!bot.followerIsBusy()) {
                    bot.setIntakeGrabberPositionPreset(IntakeGrabberPositions.CLOSED_LOOSE);
                    bot.setExtendoPositionPreset(ExtendoPositions.AUTO_SHORT);
//                    bot.moveManualInches(-4,0,0);
                    setPathState(23);
                }
                break;

            // Grab brick from spike3
            case 23:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK);
                    setPathState(24);
                }
                break;

            case 24:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleSpike3ToBucket, false);
                    setPathState(25);
                }
                break;

            case 25:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(26);
                }
                break;

            case 26:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                    setPathState(27);
                }
                break;

            case 27:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
                    setPathState(28);
                }
                break;

            case 28:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(30);
                }
                break;

            case 30:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.bucketToSampleHumanPlayer, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HANDOFF,0);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(31);
                }
                break;

            case 31:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HANDOFF_PREP_POS);
                    setPathState(32);
                }
                break;

            case 32:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK);
                    setPathState(33);
                }
                break;

            case 33:
                if (!bot.intakeIsBusy() && !bot.onHold()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.RETRACTED);
                    bot.followPath(Paths.sampleHumanPlayerToBucket, false);
                    setPathState(34);
                }
                break;

            case 34:
                if (!bot.intakeIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HAND_OFF_BRICK);
                    setPathState(35);
                }
                break;

            case 35:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP,0);
                    bot.setLiftPositionPreset(LiftPositions.HIGH_BUCKET);
                    setPathState(36);
                }
                break;

            case 36:
                if (!bot.liftIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.HIGH_BUCKET,0);
                    bot.setMode(Modes.HANDLER_HIGH_BUCKET_POS);
                    setPathState(37);
                }
                break;

            case 37:
                if (!bot.handlerIsBusy() && !bot.followerIsBusy() && !bot.onHold()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(38);
                }
                break;

            case 38:
                if (!bot.handlerIsBusy()) {
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.TOP);
                    setPathState(39);
                }
                break;

            case 39:
                if (!bot.handlerIsBusy()) {
                    bot.setMode(Modes.AUTO_END_POS);
                    setPathState(40);
                }
                break;

            // deactivate and rest up for teleop
            case 40:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}
