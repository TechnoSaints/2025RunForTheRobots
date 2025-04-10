package org.firstinspires.ftc.teamcode.common.servos;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.Component;
import org.firstinspires.ftc.teamcode.common.hardwareData.ServoData;

public class ServoSimple extends Component {
    protected final double openPosition;

    protected final double closePosition;
    protected final double middlePosition;
    protected final double swingPosition;
    protected final double lookPosition;
    protected final Servo servo;

    public ServoSimple(HardwareMap hardwareMap, Telemetry telemetry, String servoName, ServoData servoData) {
        super(telemetry);
        openPosition = servoData.openPosition;
        closePosition = servoData.closePosition;
        middlePosition = servoData.middlePosition;
        swingPosition = servoData.swingPosition;
        lookPosition = servoData.lookPosition;
        servo = hardwareMap.get(Servo.class, servoName);
    }

    public void pwmDisable() {
        servo.getController().pwmDisable();
    }

    public void pwmEnable() {
        servo.getController().pwmEnable();
    }

    public void open() {
        servo.setPosition(openPosition);
    }

    public void close() {
        servo.setPosition(closePosition);
    }

    public void middle() {
        servo.setPosition(middlePosition);
    }

    public void swing() {
        servo.setPosition(swingPosition);
    }

    public void look() {
        servo.setPosition(lookPosition);
    }

    protected void log(double position) {
        telemetry.addData("Target Position: ", position);
        telemetry.update();
    }
}