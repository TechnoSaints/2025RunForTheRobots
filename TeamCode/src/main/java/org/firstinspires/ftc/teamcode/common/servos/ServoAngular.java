package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoAngular extends ServoSimple {
    private final double rangeTicks, rangeDegrees, ticksPerDegree, minPosDegrees, ticksAtMinPosDegrees, maxPosDegrees, ticksAtMaxPosDegrees;
    private double currentPosDegrees;

    public ServoAngular(HardwareMap hardwareMap, Telemetry telemetry, String servoName, double minPosDegrees, double ticksAtMinPosDegrees, double maxPosDegrees, double ticksAtMaxPosDegrees) {
        super(hardwareMap, telemetry, servoName);

        if (minPosDegrees >= maxPosDegrees) {
            throw new IllegalArgumentException();
        }

        this.minPosDegrees = minPosDegrees;
        this.ticksAtMinPosDegrees = ticksAtMinPosDegrees;
        this.maxPosDegrees = maxPosDegrees;
        this.ticksAtMaxPosDegrees = ticksAtMaxPosDegrees;

        rangeDegrees = this.maxPosDegrees - this.minPosDegrees;
        rangeTicks = this.ticksAtMaxPosDegrees - this.ticksAtMinPosDegrees;
        ticksPerDegree = rangeTicks / rangeDegrees;

        setPositionDegrees(minPosDegrees,0);
        currentPosDegrees = minPosDegrees;
    }

    public void setPositionDegrees(double posDegrees, double delay) {
//        telemetry.addData("posDegrees: ",posDegrees);
//        telemetry.addData("degreesToTicks(): ", degreesToTicks(posDegrees));
//        telemetry.update();
        if (!stopAtLimit(posDegrees)) {
            setPositionTicks(degreesToTicks(posDegrees),0);
            currentPosDegrees = posDegrees;
        }
        setTimer(delay);
    }

    public void rotateDegrees(double angleDegrees) {
        setPositionDegrees(currentPosDegrees + angleDegrees,0);
    }

    public double getPositionDegrees() {
        return (currentPosDegrees);
    }

    public double getPositionTicks() {
        return (servo.getPosition());
    }

    private boolean stopAtLimit(double posDegrees) {
        boolean atLimit = false;
//        telemetry.addData("target: ",posDegrees );
//        telemetry.addData("maxPos: ", maxPosDegrees);
//        telemetry.addData("ticksAtMax: ", ticksAtMaxPosDegrees);
//        telemetry.addData("currentPosDegrees before: ", currentPosDegrees);
//        telemetry.addData("currentPosTicks before: ", getPositionTicks());
//        telemetry.addData("atLimit before ", atLimit);
        if (posDegrees <= minPosDegrees) {
            setPositionTicks(ticksAtMinPosDegrees,0);
            currentPosDegrees = minPosDegrees;

            atLimit = true;
        } else if (posDegrees >= maxPosDegrees) {
            setPositionTicks(ticksAtMaxPosDegrees,0);
            currentPosDegrees = maxPosDegrees;
            atLimit = true;
        }
//        telemetry.addData("currentPosDegrees after: ", currentPosDegrees);
//        telemetry.addData("currentPosTicks after: ", getPositionTicks());
//        telemetry.addData("atLimit after: ", atLimit);
//        telemetry.update();
        return (atLimit);
    }

    private double degreesToTicks(double posDegrees) {
        return (ticksAtMinPosDegrees + ((posDegrees - minPosDegrees) * ticksPerDegree));
    }
}
