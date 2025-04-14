package org.firstinspires.ftc.teamcode.common.drive;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.common.Component;
import org.firstinspires.ftc.teamcode.common.hardwareData.DrivetrainData;
import org.firstinspires.ftc.teamcode.common.hardwareData.MotorData;

import org.firstinspires.ftc.teamcode.pinpoint.GoBildaPinpointDriver;


public class Drivetrain extends Component {
    private final DcMotorEx leftFrontDrive;
    private final DcMotorEx rightFrontDrive;
    private final DcMotorEx leftBackDrive;
    private final DcMotorEx rightBackDrive;
    private final TouchSensor noseSwitch;
    private final double maxFastPower;
    private final double maxMediumPower;
    private final double maxSlowPower;
    private double currentPower;
    private double maxVelocity;
    //    private IMU imu;
    private OpMode opMode;
    private final double headingThreshold = 0.5;
    private final double turnGain = 0.02;
    private final double driveGain = 0.03;
    private final double ticksPerInch;
    private PIDFController pidf;
    GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

    double oldTime = 0;


    public Drivetrain(HardwareMap hardwareMap, Telemetry telemetry, DrivetrainData drivetrainData, MotorData motorData) {
        super(telemetry);
        this.opMode = opMode;
        maxFastPower = drivetrainData.maxFastPower;
        maxMediumPower = drivetrainData.maxMediumPower;
        maxSlowPower = drivetrainData.maxSlowPower;
        maxVelocity = motorData.maxTicksPerSec;
        ticksPerInch = drivetrainData.wheelTicksPerInch;

        leftFrontDrive = hardwareMap.get(DcMotorEx.class, "leftFrontDrive");
        leftBackDrive = hardwareMap.get(DcMotorEx.class, "leftBackDrive");
        rightFrontDrive = hardwareMap.get(DcMotorEx.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotorEx.class, "rightBackDrive");

        leftFrontDrive.setDirection(drivetrainData.leftFrontMotorDirection);
        leftBackDrive.setDirection(drivetrainData.leftRearMotorDirection);
        rightFrontDrive.setDirection(drivetrainData.rightFrontMotorDirection);
        rightBackDrive.setDirection(drivetrainData.rightRearMotorDirection);

        stopAndResetEncoders();
        setRunUsingEncoder();
        setBrakingOn();
        setToFastPower();

        noseSwitch = hardwareMap.get(TouchSensor.class, "noseSwitch");

//        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
//        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
//        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

//        imu = opMode.hardwareMap.get(IMU.class, "imu");
//        imu.initialize(new IMU.Parameters(orientationOnRobot));
//        imu.resetYaw();


        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
//        odo.recalibrateIMU();
        odo.resetPosAndIMU();
    }

    protected void setToFastPower() {
        currentPower = maxFastPower;
    }

    protected void setToSlowPower() {
        currentPower = maxSlowPower;
    }

    public void turnToHeading(double targetHeading) {
        double turnSpeed = maxMediumPower;
        double headingError = getHeadingError(targetHeading);

        // keep looping while we are still active, and not on heading.
        while (Math.abs(headingError) > headingThreshold) {
            headingError = getHeadingError(targetHeading);

            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, turnGain);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxMediumPower, maxMediumPower);

            // Pivot in place by applying the turning correction
            moveDirection(0, 0, turnSpeed);

            telemetry.addData("Actual Heading: ", getHeading());
            telemetry.addData("Target Heading: ", targetHeading);
            telemetry.addData("headingError: ", headingError);
            telemetry.addData("turnSpeed: ", turnSpeed);
            telemetry.update();
        }
        stop();
    }

    public void turnForDistance(double distance) {
        double targetHeading = getHeading() + distance;
        turnToHeading(targetHeading);
    }

    public void creepDirection(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void creepDirectionNoEnc(double axial, double strafe, double yaw) {
        moveDirection(axial * maxSlowPower, strafe * maxSlowPower, yaw * maxSlowPower);
    }

    public void moveDirection(double axial, double strafe, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = axial + strafe + yaw;
        double rightFrontPower = axial - strafe - yaw;
        double leftBackPower = axial - strafe + yaw;
        double rightBackPower = axial + strafe - yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }
/*
        telemetry.addData("leftFrontPower: ", leftFrontPower);
        telemetry.addData("currentPower: ", currentPower);
        telemetry.addData("maxVelocity: ", maxVelocity);
        telemetry.update();
*/
        leftFrontDrive.setVelocity(leftFrontPower * currentPower * maxVelocity);
        rightFrontDrive.setVelocity(rightFrontPower * currentPower * maxVelocity);
        leftBackDrive.setVelocity(leftBackPower * currentPower * maxVelocity);
        rightBackDrive.setVelocity(rightBackPower * currentPower * maxVelocity);
//        log();
    }

    public void strafeRightForDistance(double distance) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double strafeSpeed = maxMediumPower;

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() - targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() - targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy()) {
            moveDirection(0, strafeSpeed, 0);
        }
        stop();
        setRunUsingEncoder();
    }

    public void moveForwardForDistance(double distance) {
        moveForwardForDistance(distance, maxMediumPower);
    }

    public void creepForwardForDistance(double distance) {
        moveForwardForDistance(distance, maxSlowPower);
    }

    private void moveForwardForDistance(double distance, double driveSpeed) {
        int targetCounts = (int) (-distance * ticksPerInch);
        int leftFrontTarget = 0;
        int leftBackTarget = 0;
        int rightFrontTarget = 0;
        int rightBackTarget = 0;
        double turnSpeed = 0;
        double headingError = 0;
        double targetHeading = getHeading();

        leftFrontTarget = leftFrontDrive.getCurrentPosition() + targetCounts;
        leftBackTarget = leftBackDrive.getCurrentPosition() + targetCounts;
        rightFrontTarget = rightFrontDrive.getCurrentPosition() + targetCounts;
        rightBackTarget = rightBackDrive.getCurrentPosition() + targetCounts;

        leftFrontDrive.setTargetPosition(leftFrontTarget);
        leftBackDrive.setTargetPosition(leftBackTarget);
        rightFrontDrive.setTargetPosition(rightFrontTarget);
        rightBackDrive.setTargetPosition(rightBackTarget);

        setRunToPosition();

        while (leftFrontDrive.isBusy() && leftBackDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy()) {
            //while (leftFrontDrive.isBusy()) {
            headingError = getHeadingError(targetHeading);
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(headingError, driveGain);
            // if driving in reverse, the motor correction also needs to be reversed
            if (distance < 0)
                turnSpeed *= -1.0;

            // Apply the turning correction to the current driving speed.
            moveDirection(driveSpeed, 0.0, -turnSpeed);
            //           log();
        }
        stop();
        setRunUsingEncoder();
    }

    private double getHeadingError(double targetHeading) {
        return (targetHeading - getHeading());
    }

    private double getSteeringCorrection(double headingError, double gain) {
        // Determine the heading current error

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180) headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * gain, -1, 1);
    }


    protected double getHeading() {
        //       return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        odo.update(GoBildaPinpointDriver.ReadData.ONLY_UPDATE_HEADING);
        double heading = odo.getHeading(AngleUnit.DEGREES);
        while (heading > 180) heading -= 360;
        while (heading <= -180) heading += 360;
        return(heading);
    }

    public void stop() {
        leftFrontDrive.setVelocity(0.0);
        leftBackDrive.setVelocity(0.0);
        rightFrontDrive.setVelocity(0.0);
        rightBackDrive.setVelocity(0.0);
    }

    public void touchNoseSwitch() {
        moveDirection(0.2, 0, 0);
        while (!noseSwitch.isPressed()) {
        }
        stop();
    }

    private void setBrakingOn() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private void setBrakingOff() {
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    private void stopAndResetEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setRunWithoutEncoders() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setRunUsingEncoder() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void setRunToPosition() {
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void log() {
        telemetry.addData("leftFrontDrive Position: ", leftFrontDrive.getCurrentPosition());
        telemetry.addData("leftFrontDrive Target: ", leftFrontDrive.getTargetPosition());
        telemetry.addData("leftFrontDrive Velocity: ", leftFrontDrive.getVelocity());
        telemetry.update();
    }
}