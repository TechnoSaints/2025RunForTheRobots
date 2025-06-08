package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "Bucket x 1 + Park", group = "Bucket")
public class Bucketx1 extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
                bot.setMode(Modes.HIGH_BUCKET_SCORING);
                bot.followPath(Paths.startToBucket, true);
                setPathState(1);
                break;

            // move to the sub pose
            case 1:
                if (!bot.isBusy()) {
                    bot.setHandlerGrabberPositionPreset(HandlerGrabberPositions.OPEN);
                    setPathState(2);
                }
                break;

                 case 2:
                if (!bot.isBusy()) {
                    bot.setMode(Modes.PARKING_AT_SUB);
                    bot.followPath(Paths.bucketToSamplePark, true);
                    setPathState(3);
                }
                break;

            // move to human player park pose
            case 3:
                if (!bot.isBusy()) {
                    setPathState(4);
                }
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

