package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Component;

public class ServoSimple extends Component {

    protected final Servo servo;

    public ServoSimple(HardwareMap hardwareMap, Telemetry telemetry, String servoName) {
        super(telemetry);
        servo = hardwareMap.get(Servo.class, servoName);
    }

    public void setPositionTicks(double position, double delay)
    {
        servo.setPosition(position);
        setTimer(delay);
    }

    protected double getPositionTicks() {
        return (servo.getPosition());
    }
}
