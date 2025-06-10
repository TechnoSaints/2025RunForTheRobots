package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.ExtendoPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerArmPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
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
                    bot.followPath(Paths.subShortSideToSpecimenSpike1Sweep, false);
                    setPathState(11);
                }
                break;

            case 11:
                if (!bot.followerIsBusy()) {
                    bot.setExtendoPositionPreset(ExtendoPositions.EXTENDED);
                    bot.setMode(Modes.INTAKE_HOVER_POS);
                    setPathState(14);
                }
                break;

//            case 12:
//                if (!bot.intakeIsBusy() && !bot.onHold()) {
//                    bot.followPath(Paths.specimenSpike1SweepToHumanPlayerDrop, false);
//                    setPathState(13);
//                }
//                break;

//            case 13:
//                if (!bot.followerIsBusy() && !bot.onHold()) {
//                    bot.setMode(Modes.INTAKE_HOVER_POS);
//                    setPathState(14);
//                }
//                break;

            case 14:
                if (!bot.followerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.INTAKE_BRICK);
                    setPathState(99);
                }
                break;



//            case 4:
//                if (!bot.handlerIsBusy() && !bot.onHold()) {
//                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
//                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);
//                    setPathState(5);
//                }
//                break;
//
//            case 5:
//                if (!bot.followerIsBusy()) {
//                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
//                    setPathState(6);
//                }
//                break;
//
//            case 6:
//                if (!bot.followerIsBusy()) {
//                    bot.setMode(Modes.GRAB_SPECIMEN);
//                    setPathState(7);
//                }
//                break;

            case 99:
                if (!bot.followerIsBusy() && !bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

