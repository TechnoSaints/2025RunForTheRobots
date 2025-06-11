package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.localization.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Specimen x 1", group = "Specimen")
public class Specimenx1 extends SpecimenAutoOpMode {
    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_HANG,0);
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
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);
                    bot.setHandlerArmPositionPreset(HandlerArmPositions.SPECIMEN_WALL,0);
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);

                    FieldLocations.subShortSideSetupPose =
                            new Pose(FieldLocations.subShortSideSetupPose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSideSetupPose.getY(),
                                    FieldLocations.subShortSideSetupPose.getHeading());

                    FieldLocations.subShortSidePose =
                            new Pose(FieldLocations.subShortSidePose.getX() + gapBetweenHangingSpecimensIN,
                                    FieldLocations.subShortSidePose.getY(),
                                    FieldLocations.subShortSidePose.getHeading());

                    Paths.buildSpecimenHangPaths(bot.getFollower());

                    setPathState(99);
                }
                break;

            case 99:
                if (!bot.followerIsBusy() && !bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

