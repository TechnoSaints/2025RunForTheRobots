package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.common.Modes;
import org.firstinspires.ftc.teamcode.common.hardwareConfiguration.positions.HandlerGrabberPositions;
import org.firstinspires.ftc.teamcode.opmode.Paths;

@Autonomous(name = "BasicMove", group = "Bucket")
public class BasicMove extends BucketAutoOpMode {

    protected void autonomousPathUpdate() {
        switch (pathState) {
            // transition to specimen hang position
            case 0:
//                bot.followPath(Paths.startToBucket, true);
                bot.moveManualInches(-12,12,0);
                setPathState(1);
                break;

            // deactivate and rest up for teleop
            case 1:
                if (!bot.isBusy()) {
                    setPathState(-1);
                    requestOpModeStop();
                }
                break;
        }
    }
}

