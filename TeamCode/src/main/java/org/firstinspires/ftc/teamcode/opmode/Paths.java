package org.firstinspires.ftc.teamcode.opmode;

import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;

import java.lang.reflect.Field;

public class Paths {

    // samples paths
    public static PathChain startToBucket, bucketToSampleSpike1, sampleSpike1ToBucket, bucketToSampleSpike2, sampleSpike2ToBucket, bucketToSampleSpike3Setup,
            sampleSpike3SetupToSampleSpike3, sampleSpike3ToBucket, bucketToSamplePark;
    // specimen paths
    public static PathChain startToSubShortSideSetup, subShortSideSetupToSubShortSide, subShortSideToHumanPlayerPark, pushSequence,
            specimenGrabToSubShortSideSetup, subShortSideToSpecimenGrabSetup, specimenGrabSetupToSpecimenGrab;

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
    }

    public static void buildSpecimenPaths(Follower follower) {
        buildSpecimenHangPaths(follower);

        pushSequence = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSidePose), new Point(FieldLocations.specimenSpike1Setup1Pose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSidePose.getHeading(), FieldLocations.specimenSpike1Setup1Pose.getHeading())

                .addPath(new BezierCurve(new Point(FieldLocations.specimenSpike1Setup1Pose), new Point(FieldLocations.specimenSpike1Setup2Pose),
                        new Point(FieldLocations.specimenSpike1Pose), new Point(FieldLocations.specimenSpike1DropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike1Setup1Pose.getHeading(), FieldLocations.specimenSpike1DropPose.getHeading())

                .addPath(new BezierCurve(new Point(FieldLocations.specimenSpike1DropPose), new Point(FieldLocations.specimenSpike1Setup2Pose),
                        new Point(FieldLocations.specimenSpike1Pose), new Point(FieldLocations.specimenSpike2Pose),
                        new Point(FieldLocations.specimenSpike2DropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike1DropPose.getHeading(), FieldLocations.specimenSpike2DropPose.getHeading())

                .addPath(new BezierCurve(new Point(FieldLocations.specimenSpike2DropPose), new Point(FieldLocations.specimenSpike1Setup2Pose),
                        new Point(FieldLocations.specimenSpike1Pose), new Point(FieldLocations.specimenSpike2Pose),
                        new Point(FieldLocations.specimenSpike3DropPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike2DropPose.getHeading(), FieldLocations.specimenSpike3DropPose.getHeading())

                .addPath(new BezierCurve(new Point(FieldLocations.specimenSpike3DropPose), new Point(FieldLocations.pushFinishPose),
                        new Point(FieldLocations.specimenGrabSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenSpike3DropPose.getHeading(), FieldLocations.specimenGrabSetupPose.getHeading())
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

    public static void buildSpecimenHangPaths(Follower follower) {

        startToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.startPose),
                        new Point(FieldLocations.subShortSidePose)))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.subShortSidePose.getHeading())
                .build();

        subShortSideSetupToSubShortSide = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.subShortSideSetupPose),
                        new Point(FieldLocations.subShortSidePose)))
                .setLinearHeadingInterpolation(FieldLocations.subShortSideSetupPose.getHeading(), FieldLocations.subShortSidePose.getHeading())
                .build();

        specimenGrabToSubShortSideSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.specimenGrabPose),
                        new Point(FieldLocations.subShortSideSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.specimenGrabPose.getHeading(),
                        FieldLocations.subShortSideSetupPose.getHeading())
                .build();
    }
}
