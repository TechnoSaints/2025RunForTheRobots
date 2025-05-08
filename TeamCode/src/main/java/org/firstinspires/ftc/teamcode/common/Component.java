package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Component {

    protected Telemetry telemetry;

    private ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double delay = 0;

    public Component(Telemetry telemetry)
    {
        this.telemetry = telemetry;
    }

    protected void setTimer(double delay)
    {
        this.delay = delay;
        timer.reset();
    }

    protected boolean isBusy()
    {
        return (timer.milliseconds() < delay);
    }
}
