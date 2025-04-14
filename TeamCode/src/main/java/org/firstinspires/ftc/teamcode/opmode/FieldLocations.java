package org.firstinspires.ftc.teamcode.opmode;
import com.pedropathing.localization.Pose;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
;

public class FieldLocations {
    public static final Pose parkPose = new Pose(9, 52.5, Math.toRadians(0));
    public static final Pose startPose = new Pose(0, 0, Math.toRadians(0));
    public static final Pose bucketPose = new Pose(-14.7, 7, Math.toRadians(45));
    public static final Pose parkSetupPose = new Pose(0, 49.5, Math.toRadians(0));

    // red submersible locations
    public static final Pose redAscent = new Pose(-14.75+72, 0+72, 0);
    public static final Pose redChamber = new Pose(0+72, -24.125+72, Math.PI/2);

    // red bucket side locations
    public static final Pose redBucketStart = new Pose(-11.875+72, -58.75+72, Math.PI);
    public static final Pose redBucketBucket = new Pose(-64.875+72, -64.875+72, Math.PI);
    public static final Pose redBucketSpike1 = new Pose(-47.75+72, -25.625+72, Math.PI/2);
    public static final Pose redBucketSpike2 = new Pose(redBucketSpike1.getX() - 10, redBucketSpike1.getY(), redBucketSpike1.getHeading());
    public static final Pose redBucketSpike3 = new Pose(redBucketSpike2.getX() - 10, redBucketSpike2.getY(), redBucketSpike2.getHeading());

    // red specimen side locations
    public static final Pose redSpecimenStart = new Pose(-redBucketStart.getX(), redBucketStart.getY(), -redBucketStart.getHeading());
    public static final Pose redSpecimenObservation = new Pose(58.5+72, -64.25+72, 0);
    public static final Pose redSpecimenSpike1 = new Pose(-redBucketSpike1.getX(), redBucketSpike1.getY(), redBucketSpike1.getHeading());
    public static final Pose redSpecimenSpike2 = new Pose(redSpecimenSpike1.getX() + 10, redSpecimenSpike1.getY(), redSpecimenSpike1.getHeading());
    public static final Pose redSpecimenSpike3 = new Pose(redSpecimenSpike2.getX() + 10, redSpecimenSpike2.getY(), redSpecimenSpike2.getHeading());

    // blue submersible locations
    public static final Pose blueAscent = new Pose(-redAscent.getX(), -redAscent.getY(), -redAscent.getHeading());
    public static final Pose blueChamber = new Pose(-redChamber.getX(), -redChamber.getY(), -redChamber.getHeading());

    // blue bucket side locations
    public static final Pose blueBucketStart = new Pose(-redBucketStart.getX(), -redBucketStart.getY(), -redBucketStart.getHeading());
    public static final Pose blueBucketBucket = new Pose(-redBucketBucket.getX(), -redBucketBucket.getY(), -redBucketBucket.getHeading());
    public static final Pose blueBucketSpike1 = new Pose(-redBucketSpike1.getX(), -redBucketSpike1.getY(), -redBucketSpike1.getHeading());
    public static final Pose blueBucketSpike2 = new Pose(blueBucketSpike1.getX() + 10, blueBucketSpike1.getY(), blueBucketSpike1.getHeading());
    public static final Pose blueBucketSpike3 = new Pose(blueBucketSpike2.getX() + 10, blueBucketSpike2.getY(), blueBucketSpike2.getHeading());

    // blue specimen side locations
    public static final Pose blueSpecimenStart = new Pose(-blueBucketStart.getX(), blueBucketStart.getY(), -blueBucketStart.getHeading());
    public static final Pose blueSpecimenObservation = new Pose(-redSpecimenObservation.getX(), -redSpecimenObservation.getY(), -redSpecimenObservation.getHeading());
    public static final Pose blueSpecimenSpike1 = new Pose(-blueBucketSpike1.getX(), blueBucketSpike1.getY(), blueBucketSpike1.getHeading());
    public static final Pose blueSpecimenSpike2 = new Pose(blueSpecimenSpike1.getX() - 10, blueSpecimenSpike1.getY(), blueSpecimenSpike1.getHeading());
    public static final Pose blueSpecimenSpike3 = new Pose(blueSpecimenSpike2.getX() - 10, blueSpecimenSpike2.getY(), blueSpecimenSpike2.getHeading());
}