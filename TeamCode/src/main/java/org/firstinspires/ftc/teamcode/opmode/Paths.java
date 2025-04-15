package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.pathgen.PathChain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

public class Paths {

    public static PathChain startToBucket, bucketToSampleSpike1, sampleSpike1ToBucket, bucketToSampleSpike2, getSampleSpike2ToBucket, bucketToSpike3Setup,
            spike3SetupToSpike3, spike3ToBucket, bucketToParkSetup, parkSetupToPark, bucketToHumanPlayer, humanPlayerToBucket, bucketToHumanPlayerSetup,
            humanPlayerSetupToHumanPlayer, bucketToHumanPlayerBypass, humanPlayerToBucketBypass;

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

        sample3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(spike3SetupPose), new Point(spike3Pose)))
                .setLinearHeadingInterpolation(spike3SetupPose.getHeading(), spike3Pose.getHeading())
                .build();

        sample3Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(spike3Pose), new Point(bucketPose)))
                .setLinearHeadingInterpolation(spike3Pose.getHeading(), bucketPose.getHeading())
                .build();

        parkSubSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(parkSetupPose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), parkSetupPose.getHeading())
                .build();

        parkSub = follower.pathBuilder()
                .addPath(new BezierLine(new Point(parkSetupPose), new Point(parkPose)))
                .setLinearHeadingInterpolation(parkSetupPose.getHeading(), parkPose.getHeading())
                .build();

//        look = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(parkSetupPose), new Point(lookPose)))
//                .setLinearHeadingInterpolation(parkSetupPose.getHeading(), lookPose.getHeading())
//                .build();


    }

    public static void buildSpecimenPaths(Follower follower) {

    }

    public static void buildHybridPaths(Follower follower) {
        humanPlayerPickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(humanPlayerPickupPose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), humanPlayerPickupPose.getHeading())
                .build();

        humanPlayerScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(humanPlayerPickupPose), new Point(bucketPose)))
                .setLinearHeadingInterpolation(humanPlayerPickupPose.getHeading(), bucketPose.getHeading())
                .build();

        humanPlayerBypassPickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(humanPlayerBypassPose1), new Point(humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(humanPlayerBypassPose1.getHeading(), humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(humanPlayerBypassPose2), new Point(humanPlayerPickupPose)))
                .setLinearHeadingInterpolation(humanPlayerBypassPose2.getHeading(), humanPlayerPickupPose.getHeading())
                .build();

        humanPlayerBypassScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(humanPlayerPickupPose), new Point(humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(humanPlayerPickupPose.getHeading(), humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(humanPlayerBypassPose2), new Point(humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(humanPlayerBypassPose2.getHeading(), humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(humanPlayerBypassPose1), new Point(bucketPose)))
                .setLinearHeadingInterpolation(humanPlayerBypassPose1.getHeading(), bucketPose.getHeading())
                .build();

        parkHuman = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(parkHumanPose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), parkHumanPose.getHeading())
                .build();
    }
}
