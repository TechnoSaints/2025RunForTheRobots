package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoAngular extends ServoSimple {
    private final double rangeTicks, rangeDegrees, ticksPerDegree, minPosDegrees, ticksAtMinPosDegrees, maxPosDegrees, ticksAtMaxPosDegrees;
    private double currentPosTicks, currentPosDegrees;

    public ServoAngular(HardwareMap hardwareMap, Telemetry telemetry, String servoName, double minPosDegrees, double ticksAtMinPosDegrees, double maxPosDegrees, double ticksAtMaxPosDegrees) {
        super(hardwareMap, telemetry, servoName);
        this.minPosDegrees = minPosDegrees;
        this.ticksAtMinPosDegrees = ticksAtMinPosDegrees;
        this.maxPosDegrees = maxPosDegrees;
        this.ticksAtMaxPosDegrees = ticksAtMaxPosDegrees;

        rangeDegrees = this.maxPosDegrees - this.minPosDegrees;
        rangeTicks = Math.abs(this.ticksAtMaxPosDegrees - this.ticksAtMinPosDegrees);
        ticksPerDegree = rangeTicks / rangeDegrees;

        setPositionDegrees(minPosDegrees);
    }

    public void setPositionDegrees(double posDegrees) {
        if (!stopAtLimit(posDegrees)) {
            setPositionTicks(degreesToTicks(posDegrees));
            currentPosDegrees = posDegrees;
            currentPosTicks = getPositionTicks();
        }
    }

    public void rotateDegrees(double angleDegrees) {
        setPositionDegrees(currentPosDegrees + angleDegrees);
    }

    private boolean stopAtLimit(double posDegrees) {
        boolean atLimit = false;

        if (posDegrees <= minPosDegrees) {
            setPositionTicks(ticksAtMinPosDegrees);
            atLimit = true;
        } else if (posDegrees >= maxPosDegrees) {
            setPositionTicks(ticksAtMaxPosDegrees);
            atLimit = true;
        }
        return (atLimit);
    }

    private double degreesToTicks(double posDegrees) {
        return (ticksAtMinPosDegrees + ((posDegrees - minPosDegrees) * ticksPerDegree));
    }
}
