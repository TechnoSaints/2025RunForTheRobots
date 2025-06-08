package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.IntakeSwivelPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "BasicMove", group = "Bucket")
public class BasicMove extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
//                bot.followPath(Paths.startToBucket, true);
//                bot.moveManualInches(-12,12,0);
                bot.followPath(Paths.bucketToSampleSpike3, true);
                setPathState(1);
                break;

            case 1:
                if (!bot.isBusy()) {
                    bot.setIntakeSwivelPositionPreset(IntakeSwivelPositions.DEGREES90);
                    setPathState(2);
                }
                break;

            // deactivate and rest up for teleop
            case 2:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

