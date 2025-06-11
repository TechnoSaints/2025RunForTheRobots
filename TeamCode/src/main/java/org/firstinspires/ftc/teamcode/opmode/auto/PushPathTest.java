package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "PushPathTest", group = "Specimen")
public class PushPathTest extends SpecimenAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                bot.followPath(Paths.startToSubShortSideSetup, false);
                setPathState(2);
                break;

            case 2:
                if (bot.bumperBumped()) {
                    setPathState(10);
                }
                break;

            case 10:
                bot.followPath(Paths.pushSequence, true);
                setPathState(99);
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

