package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

public class Paths {

    // samples paths
    public static PathChain startToBucket, bucketToSampleSpike1, sampleSpike1ToBucket, bucketToSampleSpike2, sampleSpike2ToBucket, bucketToSampleSpike3, bucketToSampleSpike3Setup,
            sampleSpike3SetupToSampleSpike3, sampleSpike3ToBucket, bucketToSamplePark, sampleParkSetupToSampleLook, startToSubShortSideSetup, subShortSideSetupToSubShortSide;

    // hybrid paths
    public static PathChain bucketToHumanPlayer, humanPlayerSamplePickupToBucket, bucketToHumanPlayerSamplePickupViaBypass,
            humanPlayerSamplePickupToBucketViaBypass, bucketToHumanPlayerPark, subShortSideToHumanPlayerPark;

    // specimen paths
    public static PathChain startToSubShortSide, subShortSideToSpecimenSpike1Sweep, specimenSpike1SweepSetupToSpecimenSpike1Sweep, specimenSpike1SweepToHumanPlayerDrop, humanPlayerDropToSpecimenSpike2,
            specimenSpike2ToHumanPlayerDrop, humanPlayerDropToSpecimenSpike3, specimenSpike3ToHumanPlayerDrop, humanPlayerDropToSpecimenGrab,
            specimenGrabToSubShortSide, subShortSideToSpecimenGrabSetup, specimenGrabSetupToSpecimenGrab;

    public static void buildSamplePaths(Follower follower) {
        startToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.startPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike1 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike1Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike1Pose.getHeading())
                .build();

        sampleSpike1ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike1Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike1Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike2Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike2Pose.getHeading())
                .build();

        sampleSpike2ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike2Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike2Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSampleSpike3Setup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike3SetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike3SetupPose.getHeading())
                .build();

        sampleSpike3SetupToSampleSpike3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3SetupPose), new Point(FieldLocations.sampleSpike3Pose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3SetupPose.getHeading(), FieldLocations.sampleSpike3Pose.getHeading())
                .build();

        sampleSpike3ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSamplePark = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleParkSetupPose), new Point(FieldLocations.sampleParkPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleParkPose.getHeading())
                .build();

        sampleParkSetupToSampleLook = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleParkSetupPose), new Point(FieldLocations.sampleLookPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleParkSetupPose.getHeading(), FieldLocations.sampleLookPose.getHeading())
                .build();
    }

    public static void buildHybridPaths(Follower follower) {
        bucketToHumanPlayer = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.humanPlayerSamplePickupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.humanPlayerSamplePickupPose.getHeading())
                .build();

        humanPlayerSamplePickupToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerSamplePickupPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerSamplePickupPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToHumanPlayerSamplePickupViaBypass = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose1), new Point(FieldLocations.humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose1.getHeading(), FieldLocations.humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose2), new Point(FieldLocations.humanPlayerSamplePickupPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose2.getHeading(), FieldLocations.humanPlayerSamplePickupPose.getHeading())
                .build();

        humanPlayerSamplePickupToBucketViaBypass = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerSamplePickupPose), new Point(FieldLocations.humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerSamplePickupPose.getHeading(), FieldLocations.humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose2), new Point(FieldLocations.humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose2.getHeading(), FieldLocations.humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose1), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose1.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToHumanPlayerPark = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.humanPlayerParkPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.humanPlayerParkPose.getHeading())
                .build();
    }

    public static void buildSpecimenPaths(Follower follower) {
        startToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.startPose), new Point(FieldLocations.subShortSideSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.subShortSideSetupPose.getHeading())
                .build();

        subShortSideSetupToSubShortSide = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSideSetupPose), new Point(FieldLocations.subShortSidePose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSideSetupPose.getHeading(), FieldLocations.subShortSidePose.getHeading())
                .build();

        subShortSideToSpecimenSpike1Sweep = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.specimenSpike1SweepSetupPose), new Point(FieldLocations.specimenSpike1SweepPose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSidePose.getHeading(), FieldLocations.specimenSpike1SweepPose.getHeading())
                .build();

        specimenSpike1SweepSetupToSpecimenSpike1Sweep = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike1SweepSetupPose), new Point(FieldLocations.specimenSpike1SweepPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike1SweepSetupPose.getHeading(), FieldLocations.specimenSpike1SweepPose.getHeading())
                .build();


        specimenSpike1SweepToHumanPlayerDrop = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike1SweepPose), new Point(FieldLocations.humanPlayerDropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike1SweepPose.getHeading(), FieldLocations.humanPlayerDropPose.getHeading())
                .build();

        humanPlayerDropToSpecimenSpike2 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerDropPose), new Point(FieldLocations.specimenSpike2SweepPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerDropPose.getHeading(), FieldLocations.specimenSpike2SweepPose.getHeading())
                .build();

        specimenSpike2ToHumanPlayerDrop = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike2SweepPose), new Point(FieldLocations.humanPlayerDropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike2SweepPose.getHeading(), FieldLocations.humanPlayerDropPose.getHeading())
                .build();

        humanPlayerDropToSpecimenSpike3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerDropPose), new Point(FieldLocations.specimenSpike3SweepPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerDropPose.getHeading(), FieldLocations.specimenSpike3SweepPose.getHeading())
                .build();

        specimenSpike3ToHumanPlayerDrop = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenSpike3SweepPose), new Point(FieldLocations.humanPlayerDropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike3SweepPose.getHeading(), FieldLocations.humanPlayerDropPose.getHeading())
                .build();

        humanPlayerDropToSpecimenGrab = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerDropPose), new Point(FieldLocations.specimenGrabPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerDropPose.getHeading(), FieldLocations.specimenGrabPose.getHeading())
                .build();

        specimenGrabToSubShortSide = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenGrabPose), new Point(FieldLocations.subShortSidePose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading(), FieldLocations.subShortSidePose.getHeading())
                .build();

        subShortSideToSpecimenGrabSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.specimenGrabSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSidePose.getHeading(), FieldLocations.specimenGrabSetupPose.getHeading())
                .build();

        specimenGrabSetupToSpecimenGrab = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenGrabSetupPose), new Point(FieldLocations.specimenGrabPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenGrabSetupPose.getHeading(), FieldLocations.specimenGrabPose.getHeading())
                .build();

        subShortSideToHumanPlayerPark = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.humanPlayerParkPose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSidePose.getHeading(), FieldLocations.humanPlayerParkPose.getHeading())
                .build();
    }
}
