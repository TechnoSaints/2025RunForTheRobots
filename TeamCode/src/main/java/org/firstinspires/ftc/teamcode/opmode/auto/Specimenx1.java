package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Specimen x 1 + Park", group = "Specimen")
public class Specimenx1 extends SpecimenAutoOpMode {

    @Override
    public void start() {
        Paths.buildSpecimenPaths(bot.getFollower());
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
                setPathState(1);
                break;

            // move to the sub pose
            case 1:
                setPathState(2);
                break;

            // hang the specimen for a score, then transition to park position
            case 2:
                setPathState(3);
                break;

            // move to human player park pose
            case 3:
                setPathState(4);
                break;

            // deactivate and rest up for teleop
            case 4:
                setPathState(-1);
                requestOpModeStop();
                break;
        }
    }
}

