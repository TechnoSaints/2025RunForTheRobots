package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.BotWithPedro;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;

public abstract class AutoOpMode extends OpMode {

    protected ElapsedTime pauseTimer, sleepTimer;
    protected boolean paused = false;
    protected double pauseDuration = 0;
    protected BotWithPedro bot;
    protected int pathState;
//    protected Limelight limelight;

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
        bot = new BotWithPedro(this, telemetry);
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
        bot.update();
        autonomousPathUpdate();
    }

    abstract protected void autonomousPathUpdate();

    protected void setPathState(int pState) {
        pathState = pState;
        pauseTimer.reset();
    }

    protected boolean isBusy() {
        return ((bot.armIsBusy()) || (bot.liftIsBusy()) || (bot.followerIsBusy()));
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

