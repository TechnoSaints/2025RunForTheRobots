package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.Point;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Specimen x 2", group = "Specimen")
public class Specimenx2 extends SpecimenAutoOpMode {
    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                bot.followPath(Paths.startToSubShortSideSetup, false);
                setPathState(1);
                break;

            case 1:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(2);
                }
                break;

            case 2:
                if (bot.bumperBumped()) {
                    setPathState(3);
                }
                break;

            case 3:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(10);
                }
                break;

            case 10:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(11);
                }
                break;

            case 11:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
                    setPathState(12);
                }
                break;

            case 12:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(13);
                }
                break;

            case 13:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(14);
                }
                break;

            case 14:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(15);
                }
                break;

            case 15:
                if (bot.bumperBumped()) {
                    setPathState(16);
                }
                break;

            case 16:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(20);
                }
                break;

            case 20:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(21);
                }
                break;

            case 21:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
                    setPathState(22);
                }
                break;

            case 22:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(23);
                }
                break;

            case 23:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(24);
                }
                break;

            case 24:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(25);
                }
                break;

            case 25:
                if (bot.bumperBumped()) {
                    setPathState(26);
                }
                break;

            case 26:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(30);
                }
                break;

            case 30:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(31);
                }
                break;

            case 31:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
                    setPathState(32);
                }
                break;

            case 32:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(33);
                }
                break;

            case 33:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(34);
                }
                break;

            case 34:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(35);
                }
                break;

            case 35:
                if (bot.bumperBumped()) {
                    setPathState(36);
                }
                break;

            case 36:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(40);
                }
                break;

            case 40:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(41);
                }
                break;

            case 41:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
                    setPathState(42);
                }
                break;

            case 42:
                if (!bot.followerIsBusy() && !bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(43);
                }
                break;

            case 43:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                    bot.followPath(Paths.specimenGrabToSubShortSideSetup, false);
                    setPathState(44);
                }
                break;

            case 44:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.subShortSideSetupToSubShortSide, true);
                    setPathState(45);
                }
                break;

            case 45:
                if (bot.bumperBumped()) {
                    setPathState(46);
                }
                break;

            case 46:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                    setPathState(50);
                }
                break;

            case 50:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.followPath(Paths.subShortSideToHumanPlayerPark, false);
                    setPathState(99);
                }

            case 99:
                if (!bot.followerIsBusy() && !bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

