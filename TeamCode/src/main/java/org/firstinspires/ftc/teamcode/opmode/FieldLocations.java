package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.localization.Pose;

public class FieldLocations {
    public static Pose startPose;
    public final static Pose sampleStartPose = new Pose(0, 0, Math.toRadians(90));
    public final static Pose specimenStartPose = new Pose(47.25, 0, Math.toRadians(90));

    // position to drop the brick in the top bucket
    public final static Pose bucketPose = new Pose(-16.25, 6.5, Math.toRadians(45));

    // position to grab the brick at the innermost spike mark
    public final static Pose sampleSpike1Pose = new Pose(-8.75, 23, Math.toRadians(90));

    // position to grab the brick at the middle spike mark
    public final static Pose sampleSpike2Pose = new Pose(-18.75, 23, Math.toRadians(90));

    public final static Pose sampleSpike3SetupPose = new Pose(-13.5, 28, Math.toRadians(180));

    // position to grab the brick at the spike mark closest to the wall
    public final static Pose sampleSpike3Pose = new Pose(-13.5, 37.5, Math.toRadians(180));

    public final static Pose sampleParkSetupPose = new Pose(0, 54, Math.toRadians(0));
    // park position
    public final static Pose sampleParkPose = new Pose(16, 54, Math.toRadians(0));
    public final static Pose sampleLookPose = new Pose(7, 52.5, Math.toRadians(0));

    public final static Pose humanPlayerSamplePickupPose = new Pose(64.5, 3, Math.toRadians(-14.9));

    public final static Pose humanPlayerBypassPose1 = new Pose(12, 25, Math.toRadians(0));

    public final static Pose humanPlayerBypassPose2 = new Pose(52, 25, Math.toRadians(0));

    public final static Pose humanPlayerParkPose = new Pose(64.5, 3, Math.toRadians(-14.9));

    public final static Pose subShortSidePose = new Pose(36, 36, Math.toRadians(90));
    public final static Pose subShortSideSetupPose = new Pose(36, 31, Math.toRadians(90));





    public final static Pose specimenSpike1SweepSetupPose = new Pose(-50, 12, Math.toRadians(25.6));
    public final static Pose specimenSpike1SweepPose = new Pose(-63.5, 23.75, Math.toRadians(25.6));






    public final static Pose specimenSpike2SweepPose = new Pose(47, 26, Math.toRadians(90));
    public final static Pose specimenSpike3SweepPose = new Pose(74, 26, Math.toRadians(90));
    public final static Pose humanPlayerSweepDestinationPose = new Pose(74, 26, Math.toRadians(90));
    public final static Pose humanPlayerDropPose = new Pose(47, 26, Math.toRadians(90));

    public final static Pose specimenGrabSetupPose = new Pose(74, 15, Math.toRadians(90));

    public final static Pose specimenGrabPose = new Pose(74, 5, Math.toRadians(90));

    public final static Pose specimenSideSubPose = new Pose(36, 26, Math.toRadians(90));

}