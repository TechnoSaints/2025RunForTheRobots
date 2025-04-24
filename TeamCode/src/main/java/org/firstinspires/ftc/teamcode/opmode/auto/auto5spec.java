package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "5 Specimen Auto", group = "Specimen")
public class auto5spec extends AutoOpMode {

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

            // hang the specimen for a score, then transition to spike 1
            case 2:
                if (!isBusy()) {
                    hangSpecimen();
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(3);
                }
                break;

            case 3:
                if (!isBusy()) {
                    bot.followPath(Paths.subSideToSpecimenSpike1, true);
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(4);
                }
                break;


            // move to human player park pose
            case 4:
                if (!isBusy()) {
                    bot.followPath(Paths.specimenSpike1ToHumanPlayerDrop, true);
                    specHangPosition();
                    setPathState(5);
                }
                break;

            // go to sub side
            case 5:
                if (!isBusy()) {
                    bot.followPath(Paths.humanPlayerDropToSpecimenGrab, true);
                    sleepSynch(250);
                    bot.followPath(Paths.specimenGrabToSubSide, true);
                    requestOpModeStop();
                    setPathState(6);
                }
                break;

            case 6:
                if (!isBusy()) {
                    hangSpecimen();
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(7);
                }
                break;

            case 7:
                if (!isBusy()) {
                    bot.followPath(Paths.subSideToSpecimenSpike1, true); //need to change to spike 2
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(8);
                }
                break;


            // move to human player park pose
            case 8:
                if (!isBusy()) {
                    bot.followPath(Paths.specimenSpike1ToHumanPlayerDrop, true); //change to spike 2
                    specHangPosition();
                    setPathState(9);
                }
                break;

            // go to sub side
            case 9:
                if (!isBusy()) {
                    bot.followPath(Paths.humanPlayerDropToSpecimenGrab, true);
                    sleepSynch(250);
                    bot.followPath(Paths.specimenGrabToSubSide, true);
                    requestOpModeStop();
                    setPathState(10);
                }
                break;

            case 10:
                if (!isBusy()) {
                    hangSpecimen();
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(11);
                }
                break;

            case 11:
                if (!isBusy()) {
                    bot.followPath(Paths.subSideToSpecimenSpike1, true); //change to spike 3
                    sleepSynch(500);
                    specParkPosition();
                    setPathState(12);
                }
                break;


            // move to human player park pose
            case 12:
                if (!isBusy()) {
                    bot.followPath(Paths.specimenSpike1ToHumanPlayerDrop, true); //change to spike 3
                    specHangPosition();
                    setPathState(13);
                }
                break;

            // go to sub side
            case 13:
                if (!isBusy()) {
                    bot.followPath(Paths.humanPlayerDropToSpecimenGrab, true);
                    sleepSynch(250);
                    bot.followPath(Paths.specimenGrabToSubSide, true);
                    requestOpModeStop();
                    setPathState(14);
                }
                break;

        }
    }
}
