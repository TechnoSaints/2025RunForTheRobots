package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.Bot21528WithPedro;
import org.firstinspires.ftc.teamcode.common.Bot21528WithoutPedro;

public abstract class AutoOpMode extends OpMode {

    protected ElapsedTime pauseTimer, sleepTimer;
    protected boolean paused = false;
    protected double pauseDuration = 0;

    protected Bot21528WithPedro bot;
    protected int pathState;

    /**
     * This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system
     **/
    public abstract void start();

    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        pauseTimer = new ElapsedTime();
        sleepTimer = new ElapsedTime();
        pauseTimer.reset();
        sleepTimer.reset();

        bot = new Bot21528WithPedro(this, telemetry);
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

    protected abstract void autonomousPathUpdate();

    protected void setPathState(int pState) {
        pathState = pState;
        pauseTimer.reset();
    }

    protected boolean isBusy() {
//        return ((bot.armIsBusy()) || (bot.liftIsBusy()) || (bot.followerIsBusy()));
        return false;
    }

    /**
     * We do not use this because everything should automatically disable
     **/
    @Override
    public void stop() {
    }


    protected void startPosition() {
        //bot.grabberClose();
        //bot.wristOpen();
        //bot.armOpen();
    }

    protected void specHangPosition() {
    }

    protected void hangSpecimen() {
    }

    protected void specParkPosition() {
    }

    protected void sample3SetupPosition() {
        //bot.armClose();
        //bot.wristClose();
        //bot.liftLow();
    }

    protected void approachPosition() {
        //bot.armMiddle();
        //bot.grabberOpen();
        //bot.wristClose();
        //bot.liftLow();
    }

    protected void pickupPosition() {
        //bot.wristClose();
        //bot.grabberOpen();
        //bot.armClose();
        //bot.liftMin();
    }

    protected void parkPosition() {
        //bot.wristClose();
        //bot.armClose();
        //bot.liftMin();
    }

    protected void lookPosition() {
        //bot.armLook();
        //bot.wristLook();
        //bot.liftMin();
    }

    protected void grabSample() {
        //bot.grabberClose();
    }

    protected void dropPosition() {
        //bot.liftHigh();
        //bot.armSwing();
        //bot.wristSwing();
    }

    protected void dropSample() {
        //bot.grabberOpen();
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

