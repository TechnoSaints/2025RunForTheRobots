package org.firstinspires.ftc.teamcode.opmode.test;

import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.auto.AutoOpmode;

@Autonomous(name = "LimelightBotSensingTest", group = "Test")

public class LimelightBotSensingTest extends AutoOpmode {
    private Pose grabPose, currentPose;
    private PathChain grab, retreat;

    protected void autonomousPathUpdate() {
        if (gamepad1.right_bumper) {
            //limelight.updateFilteredResult(250);
        }
    }
}



