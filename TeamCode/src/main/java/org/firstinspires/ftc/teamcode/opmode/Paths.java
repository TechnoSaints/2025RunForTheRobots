package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.pathgen.PathChain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

public class Paths {

    // samples paths
    public static PathChain startToBucket, bucketToSampleSpike1, sampleSpike1ToBucket, bucketToSampleSpike2, getSampleSpike2ToBucket, bucketToSpike3Setup,
            spike3SetupToSpike3, spike3ToBucket, bucketToParkSetup, parkSetupToPark, parkSetupToSampleSideLook;

    // hybrid paths
    public static PathChain bucketToHumanPlayer, humanPlayerSamplePickupToBucket, bucketToHumanPlayerSamplePickupViaBypass,
            humanPlayerSamplePickupToBucketViaBypass, bucketToHumanPlayerPark;

    // specimen paths
    public static PathChain startToSubSide, subSideToSpecimenSpike1, specimenSpike1ToHumanPlayerDrop, humanPlayerDropToSpecimenSpike2,
            specimenSpike2ToHumanPlayerDrop, humanPlayerDropToSpecimenSpike3, specimenSpike3ToHumanPlayerDrop, humanPlayerDropToSpecimenGrab,
            specimenGrabToSubSide, subSideToSpecimenGrab;

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

        getSampleSpike2ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike2Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike2Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToSpike3Setup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.sampleSpike3SetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.sampleSpike3SetupPose.getHeading())
                .build();

        spike3SetupToSpike3 = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3SetupPose), new Point(FieldLocations.sampleSpike3Pose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3SetupPose.getHeading(), FieldLocations.sampleSpike3Pose.getHeading())
                .build();

        spike3ToBucket = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.sampleSpike3Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.sampleSpike3Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        bucketToParkSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.parkSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.parkSetupPose.getHeading())
                .build();

        parkSetupToPark = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.parkSetupPose), new Point(FieldLocations.parkPose)))
                .setLinearHeadingInterpolation(FieldLocations.parkSetupPose.getHeading(), FieldLocations.parkPose.getHeading())
                .build();

        parkSetupToSampleSideLook = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.parkSetupPose), new Point(FieldLocations.subSampleLongSidePose)))
                .setLinearHeadingInterpolation(FieldLocations.parkSetupPose.getHeading(), FieldLocations.subSampleLongSidePose.getHeading())
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

    }

}
