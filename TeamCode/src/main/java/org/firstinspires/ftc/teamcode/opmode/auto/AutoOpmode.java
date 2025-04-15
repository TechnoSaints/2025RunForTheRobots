package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public abstract class AutoOpmode extends OpMode {

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
        follower.setStartingPose(FieldLocations.startPose);
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
                .addPath(new BezierLine(new Point(FieldLocations.startPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.startPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        sample1Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.spike1Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.spike1Pose.getHeading())
                .build();

        sample1Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.spike1Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.spike1Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        sample2Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.spike2Pose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.spike2Pose.getHeading())
                .build();

        sample2Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.spike2Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.spike2Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        sample3Setup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.spike3SetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.spike3SetupPose.getHeading())
                .build();

        sample3Pickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.spike3SetupPose), new Point(FieldLocations.spike3Pose)))
                .setLinearHeadingInterpolation(FieldLocations.spike3SetupPose.getHeading(), FieldLocations.spike3Pose.getHeading())
                .build();

        sample3Score = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.spike3Pose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.spike3Pose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        parkSubSetup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.parkSetupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.parkSetupPose.getHeading())
                .build();

        parkSub = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.parkSetupPose), new Point(FieldLocations.parkPose)))
                .setLinearHeadingInterpolation(FieldLocations.parkSetupPose.getHeading(), FieldLocations.parkPose.getHeading())
                .build();

//        look = follower.pathBuilder()
//                .addPath(new BezierLine(new Point(FieldLocations.parkSetupPose), new Point(FieldLocations.lookPose)))
//                .setLinearHeadingInterpolation(FieldLocations.parkSetupPose.getHeading(), FieldLocations.lookPose.getHeading())
//                .build();

        humanPlayerPickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.humanPlayerPickupPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.humanPlayerPickupPose.getHeading())
                .build();

        humanPlayerScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerPickupPose), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerPickupPose.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        humanPlayerBypassPickup = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose1), new Point(FieldLocations.humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose1.getHeading(), FieldLocations.humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose2), new Point(FieldLocations.humanPlayerPickupPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose2.getHeading(), FieldLocations.humanPlayerPickupPose.getHeading())
                .build();

        humanPlayerBypassScore = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerPickupPose), new Point(FieldLocations.humanPlayerBypassPose2)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerPickupPose.getHeading(), FieldLocations.humanPlayerBypassPose2.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose2), new Point(FieldLocations.humanPlayerBypassPose1)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose2.getHeading(), FieldLocations.humanPlayerBypassPose1.getHeading())
                .addPath(new BezierLine(new Point(FieldLocations.humanPlayerBypassPose1), new Point(FieldLocations.bucketPose)))
                .setLinearHeadingInterpolation(FieldLocations.humanPlayerBypassPose1.getHeading(), FieldLocations.bucketPose.getHeading())
                .build();

        parkHuman = follower.pathBuilder()
                .addPath(new BezierLine(new Point(FieldLocations.bucketPose), new Point(FieldLocations.parkHumanPose)))
                .setLinearHeadingInterpolation(FieldLocations.bucketPose.getHeading(), FieldLocations.parkHumanPose.getHeading())
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

