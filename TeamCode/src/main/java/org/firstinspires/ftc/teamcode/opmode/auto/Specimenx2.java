package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
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
                    setPathState(4);
                }
                break;

            case 4:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.HANDLER_GRAB_SPECIMEN_POS);
                    bot.followPath(Paths.subShortSideToSpecimenGrabSetup, false);
                    setPathState(5);
                }
                break;

            case 5:
                if (!bot.followerIsBusy()) {
                    bot.followPath(Paths.specimenGrabSetupToSpecimenGrab, true);
                    setPathState(6);
                }
                break;

            case 6:
                if (!bot.handlerIsBusy() && !bot.onHold()) {
                    bot.setMode(Modes.GRAB_SPECIMEN);
                    setPathState(7);
                }
                break;

            case 7:
                if (!bot.isBusy() && !bot.onHold()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

