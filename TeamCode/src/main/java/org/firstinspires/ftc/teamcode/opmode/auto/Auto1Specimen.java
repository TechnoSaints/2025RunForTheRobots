package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "1 Spec+Park Auto", group = "Specimen")
public class Auto1Specimen extends AutoOpMode {

    @Override
    public void start() {
        Paths.buildSpecimenPaths(bot.getFollower());
//        Paths.buildHybridPaths(bot.getFollower());
    }

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
                specHangPosition();
                setPathState(1);
                break;

            // move to the sub pose
            case 1:
                if (!isBusy()) {
                    bot.followPath(Paths.startToSubSide, true);
                    setPathState(2);
                }
                break;

            // hang the specimen for a score, then transition to park position
            case 2:
                if (!isBusy()) {
                    hangSpecimen();
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(3);
                }
                break;

            // move to human player park pose
            case 3:
                if (!isBusy()) {
                    bot.followPath(Paths.subSideToHumanPlayerPark, true);
                    setPathState(4);
                }
                break;

            // deactivate and rest up for teleop
            case 4:
                if (!isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

