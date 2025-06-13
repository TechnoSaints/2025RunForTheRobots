package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.localization.Pose;

public class FieldLocations {
    public static Pose startPose;
    public final static Pose sampleStartPose = new Pose(0, 0.5, Math.toRadians(90));
    public final static Pose specimenStartPose = new Pose(47.25, 0.5, Math.toRadians(90));

    // position to drop the brick in the top bucket
    public final static Pose bucketPose = new Pose(-16.25, 6.5, Math.toRadians(45));

    // position to grab the brick at the innermost spike mark
    public final static Pose sampleSpike1Pose = new Pose(-8.75, 22.5, Math.toRadians(90));

    // position to grab the brick at the middle spike mark
    public final static Pose sampleSpike2Pose = new Pose(-18.75, 22.5, Math.toRadians(90));

    public final static Pose sampleSpike3SetupPose = new Pose(-13.5, 27.5, Math.toRadians(180));

    // position to grab the brick at the spike mark closest to the wall
    public final static Pose sampleSpike3Pose = new Pose(-13.5, 37, Math.toRadians(180));

    public final static Pose sampleHumanPlayerPose = new Pose(73.5, 5, Math.toRadians(0));

    public final static Pose sampleParkSetupPose = new Pose(12, 54, Math.toRadians(0));
    // park position
    public final static Pose sampleParkPose = new Pose(18.5, 54, Math.toRadians(0));

    public final static Pose humanPlayerParkPose = new Pose(85, 3, Math.toRadians(90));

    // Specimen hanging poses
    public static Pose subShortSideSetupPose = new Pose(34, 31.5, Math.toRadians(90));
    public static Pose subShortSidePose = new Pose(34, 35, Math.toRadians(90));

    // Push poses
    public final static Pose subShortClearPose = new Pose(72, 20, Math.toRadians(90));
    public final static Pose specimenSpike1SetupPose = new Pose(75, 54, Math.toRadians(90));
    public final static Pose specimenSpike1Pose = new Pose(90, 57, Math.toRadians(90));
    public final static Pose specimenSpike1DropPose = new Pose(90, 7, Math.toRadians(90));
    public final static Pose specimenSpike2SetupPose = new Pose(80, 54, Math.toRadians(90));
    public final static Pose specimenSpike2Pose = new Pose(100, 57, Math.toRadians(90));
    public final static Pose specimenSpike2DropPose = new Pose(95, 7, Math.toRadians(90));
    public final static Pose specimenSpike3SetupPose = new Pose(95, 70, Math.toRadians(90));
    public final static Pose specimenSpike3Pose = new Pose(104, 60, Math.toRadians(90));
    public final static Pose specimenSpike3DropPose = new Pose(104, 10, Math.toRadians(90));
    public final static Pose specimenSpike3GrabPose = new Pose(104, 2, Math.toRadians(90));

    //
    public final static Pose humanPlayerDropPose = new Pose(98, 7, Math.toRadians(90));

    public final static Pose specimenGrabSetupPose = new Pose(75, 10, Math.toRadians(90));

    public final static Pose specimenGrabPose = new Pose(75, 2, Math.toRadians(90));
}