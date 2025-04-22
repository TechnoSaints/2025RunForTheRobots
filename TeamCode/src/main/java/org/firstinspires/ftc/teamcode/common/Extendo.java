package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.servos.ServoAngular;

public class Extendo extends Component {

    private ServoAngular servo;
    private final double minLinearPosition = 0;
    private final double servoDegreesAtMinLinearPosition;
    private final double maxLinearPosition = 24.375;
    private final double servoDegreesAtMaxLinearPosition;
    private final double nearLinkageLengthInches = 11.6875;
    private final double farLinkageLengthInches = 12.625;

    public Extendo(HardwareMap hardwareMap, Telemetry telemetry, String extendoName) {
        super(telemetry);
        servo = new ServoAngular(hardwareMap, telemetry, extendoName, 1, 1, 1, 1);
        servoDegreesAtMinLinearPosition = 0000;
        servoDegreesAtMaxLinearPosition = 0000;
    }

    public void goToLinearPosition(double targetPosInches) {
    }

    public void moveLinearDistance(double distanceInches) {
    }
}