package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public abstract class AutoOpMode extends OpMode {

    protected Follower follower;
    protected ElapsedTime pauseTimer, sleepTimer;
    protected boolean paused = false;
    protected double pauseDuration = 0;
    protected Bot bot;
    protected int pathState;
//    protected Limelight limelight;

    /* Create and Define Poses + Paths
     * Poses are built with three constructors: x, y, and heading (in Radians).
     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
     * Lets assume our robot is 18 by 18 inches
     * Lets assume the Robot is facing the human player and we want to score in the bucket */
    protected final Pose startPose = new Pose(0, 0, Math.toRadians(0));

    // position to drop the brick in the top bucket
    protected final Pose bucketPose = new Pose(-14.7, 6.75, Math.toRadians(45));

    // position to grab the brick at the innermost spike mark
    protected final Pose spike1Pose = new Pose(-6.7, 16.55, Math.toRadians(90));

    // position to grab the brick at the middle spike mark
    protected final Pose spike2Pose = new Pose(-16.2, 16.55, Math.toRadians(90));

    protected final Pose spike3SetupPose = new Pose(-9.8, 15.55, Math.toRadians(124.5));

    // position to grab the brick at the spike mark closest to the wall
    protected final Pose spike3Pose = new Pose(-15.6, 21.55, Math.toRadians(124.5));

    protected final Pose parkSetupPose = new Pose(0, 50, Math.toRadians(0));
    // park position
    protected final Pose parkPose = new Pose(9, 53, Math.toRadians(0));

    protected final Pose humanPlayerPickupPose = new Pose(64.5, 3, Math.toRadians(-14.9));
//64.5, 3
    protected final Pose humanPlayerBypassPose1 = new Pose(12, 25, Math.toRadians(0));

    protected final Pose humanPlayerBypassPose2 = new Pose(52, 25, Math.toRadians(0));

    protected final Pose parkHumanPose = new Pose(64.5, 3, Math.toRadians(-14.9));

//    protected final Pose lookPose = new Pose(7, 52.5, Math.toRadians(0));

    /* Create and Define Poses + Paths
     * Poses are built with three constructors: x, y, and heading (in Radians).
     * Pedro uses 0 - 144 for x and y, with 0, 0 being on the bottom left.
     * (For Into the Deep, this would be Blue Observation Zone (0,0) to Red Observation Zone (144,144).)
     * Even though Pedro uses a different coordinate system than RR, you can convert any roadrunner pose by adding +72 both the x and y.
     * This visualizer is very easy to use to find and create paths/pathchains/poses: <https://pedro-path-generator.vercel.app/>
     * Lets assume our robot is 18 by 18 inches
     * Lets assume the Robot is facing the human player and we want to score in the bucket */

    protected PathChain preloadScore, sample1Pickup, sample1Score, sample2Pickup, sample2Score, sample3Pickup, sample3Score, sample3Setup,
            parkSubSetup, parkSub, parkHumanSetup, parkHuman, look, humanPlayerPickup, humanPlayerScore, humanPlayerBypassPickup, humanPlayerBypassScore;

    /**
     * This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system
     **/
    public void start() {
    }

    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        pauseTimer = new ElapsedTime();
        sleepTimer = new ElapsedTime();
        pauseTimer.reset();
        sleepTimer.reset();

        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        bot = new Bot(this, telemetry, 5);
        buildPaths();
        startPosition();
        initTestPaths();
        setPathState(0);
        bot.wristOpen();
    }

    protected void initTestPaths() {
    }

    /**
     * This method is called continuously after Init while waiting for "play".
     **/
    @Override
    public void init_loop() {
        bot.update();
    }

    /**
     * This is the main loop of the OpMode, it will run repeatedly after clicking "Play".
     **/
    @Override
    public void loop() {
        // These loop the movements of the robot
        follower.update();
        bot.update();
        autonomousPathUpdate();
    }

    /**
     * Build the paths for the auto (adds, for example, constant/linear headings while doing paths)
     * It is necessary to do this so that all the paths are built before the auto starts.
     **/
    protected void buildPaths() {

        preloadScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(startPose), new Point(bucketPose)))
                .setLinearHeadingInterpolation(startPose.getHeading(), bucketPose.getHeading())
                .build();

        sample1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(spike1Pose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), spike1Pose.getHeading())
                .build();

        sample1Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(spike1Pose), new Point(bucketPose)))
                .setLinearHeadingInterpolation(spike1Pose.getHeading(), bucketPose.getHeading())
                .build();

        sample2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(spike2Pose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), spike2Pose.getHeading())
                .build();

        sample2Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(spike2Pose), new Point(bucketPose)))
                .setLinearHeadingInterpolation(spike2Pose.getHeading(), bucketPose.getHeading())
                .build();

        sample3Setup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(bucketPose), new Point(spike3SetupPose)))
                .setLinearHeadingInterpolation(bucketPose.getHeading(), spike3SetupPose.getHeading())
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

    protected void startPosition() {
        bot.grabberClose();
        bot.wristOpen();
        bot.armOpen();
    }

    protected void sample3SetupPosition() {
        bot.armClose();
        bot.wristClose();
        bot.liftLow();
    }

    protected void approachPosition() {
        bot.armMiddle();
        bot.grabberOpen();
        bot.wristClose();
        bot.liftLow();
    }

    protected void pickupPosition() {
        bot.wristClose();
        bot.grabberOpen();
        bot.armClose();
        bot.liftMin();
    }

    protected void parkPosition() {
        bot.wristClose();
        bot.armClose();
        bot.liftMin();
    }

    protected void lookPosition() {
        bot.armLook();
        bot.wristLook();
        bot.liftMin();
    }

    protected void grabSample() {
        bot.grabberClose();
    }

    protected void dropPosition() {
        bot.liftHigh();
        bot.armSwing();
        bot.wristSwing();
    }

    protected void dropSample() {
        bot.grabberOpen();
    }

    abstract protected void autonomousPathUpdate();

    protected void setPathState(int pState) {
        pathState = pState;
        pauseTimer.reset();
    }

    protected boolean isBusy() {
        return ((bot.armIsBusy()) || (bot.liftIsBusy()) || (follower.isBusy()));
    }

    /**
     * We do not use this because everything should automatically disable
     **/
    @Override
    public void stop() {
    }

    protected void sleepSynch(double duration) {
        sleepTimer.reset();
        while (sleepTimer.milliseconds() < duration) {
        }
    }

    protected void setAsynchPause(double milliseconds) {
        paused = true;
        pauseDuration = milliseconds;
        pauseTimer.reset();
    }
}

