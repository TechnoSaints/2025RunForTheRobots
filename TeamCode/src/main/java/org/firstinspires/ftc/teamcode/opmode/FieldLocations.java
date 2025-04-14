package org.firstinspires.ftc.teamcode.opmode;
import com.pedropathing.localization.Pose;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


public class FieldLocations {
    public final static Pose startPose = new Pose(0, 0, Math.toRadians(0));

    // position to drop the brick in the top bucket
    public final static Pose bucketPose = new Pose(-14.7, 6.75, Math.toRadians(45));

    // position to grab the brick at the innermost spike mark
    public final static Pose spike1Pose = new Pose(-6.7, 16.55, Math.toRadians(90));

    // position to grab the brick at the middle spike mark
    public final static Pose spike2Pose = new Pose(-16.2, 16.55, Math.toRadians(90));

    public final static Pose spike3SetupPose = new Pose(-9.8, 15.55, Math.toRadians(124.5));

    // position to grab the brick at the spike mark closest to the wall
    public final static Pose spike3Pose = new Pose(-15.6, 21.55, Math.toRadians(124.5));

    public final static Pose parkSetupPose = new Pose(0, 50, Math.toRadians(0));
    // park position
    public final static Pose parkPose = new Pose(9, 53, Math.toRadians(0));

    public final static Pose humanPlayerPickupPose = new Pose(64.5, 3, Math.toRadians(-14.9));
    //64.5, 3
    public final static Pose humanPlayerBypassPose1 = new Pose(12, 25, Math.toRadians(0));

    public final static Pose humanPlayerBypassPose2 = new Pose(52, 25, Math.toRadians(0));

    public final static Pose parkHumanPose = new Pose(64.5, 3, Math.toRadians(-14.9));
}