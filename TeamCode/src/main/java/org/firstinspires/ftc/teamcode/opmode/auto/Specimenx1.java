package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.LiftPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Specimen x 1 + Park", group = "Specimen")
public class Specimenx1 extends SpecimenAutoOpMode {
    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
                bot.setMode(Modes.HANDLER_HIGH_SPECIMEN_POS);
                bot.followPath(Paths.startToSubSide, false);
                setPathState(2);
                break;

            // move to the sub pose
            case 1:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.HANG_SPECIMEN);
                }
                setPathState(2);
                break;

            // hang the specimen for a score, then transition to park position
            case 2:
                if (!bot.handlerIsBusy()) {
                    setPathState(-1);
                }
                break;

            // move to human player park pose
            case 3:
                setPathState(4);
                break;

            // deactivate and rest up for teleop
            case 4:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

