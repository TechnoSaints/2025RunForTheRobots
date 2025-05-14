package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.common.AutoBot;

public abstract class AutoOpMode extends OpMode {
    protected ElapsedTime pauseTimer, sleepTimer;
    protected boolean paused = false;
    protected double pauseDuration = 0;

    protected AutoBot bot;
    protected int pathState;

    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        bot = new AutoBot(this, telemetry);
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
}

