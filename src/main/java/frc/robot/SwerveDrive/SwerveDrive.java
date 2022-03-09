package frc.robot.SwerveDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stormbots.MiniPID;

import frc.robot.Controller.Controller;

import frc.robot.Utilities.ID;

public class SwerveDrive {

    private Controller controller;

    public static class PID {
        public static double P = 0.01;
        public static double I = 0;
        public static double D = 0;

        public static double minPid = -.5;
        public static double maxPid = .5;

        public static MiniPID backLeftPid = new MiniPID(P, I, D);
        public static MiniPID backRightPid = new MiniPID(P, I, D);
        public static MiniPID frontRightPid = new MiniPID(P, I, D);
        public static MiniPID frontLeftPid = new MiniPID(P, I, D);
    }

    public static class Motor {
        public static CANSparkMax backLeftSpeedMotor = new CANSparkMax(ID.backLeftSpeedID, MotorType.kBrushless);
        public static CANSparkMax backRightSpeedMotor = new CANSparkMax(ID.backRightSpeedID, MotorType.kBrushless);;
        public static CANSparkMax frontLeftSpeedMotor = new CANSparkMax(ID.frontLeftSpeedID, MotorType.kBrushless);;
        public static CANSparkMax frontRightSpeedMotor = new CANSparkMax(ID.frontRightSpeedID, MotorType.kBrushless);;

        public static CANSparkMax backLeftAngleMotor = new CANSparkMax(ID.backLeftAngleID, MotorType.kBrushless);
        public static CANSparkMax backRightAngleMotor = new CANSparkMax(ID.backRightAngleID, MotorType.kBrushless);
        public static CANSparkMax frontLeftAngleMotor = new CANSparkMax(ID.frontLeftAngleID, MotorType.kBrushless);
        public static CANSparkMax frontRightAngleMotor = new CANSparkMax(ID.frontRightAngleID, MotorType.kBrushless);

        public static double frontLeftAngle;
        public static double frontRightAngle;
        public static double backLeftAngle;
        public static double backRightAngle;

        public static double backLeftSpeed;
        public static double backRightSpeed;
        public static double frontLeftSpeed;
        public static double frontRightSpeed;
        public static double speedModifier;
    }

    public SwerveDrive(int defaultDriveMode, Controller _controller) {
        controller = _controller;

        PID.frontLeftPid.setOutputLimits(PID.minPid, PID.maxPid);
        PID.frontRightPid.setOutputLimits(PID.minPid, PID.maxPid);
        PID.backLeftPid.setOutputLimits(PID.minPid, PID.maxPid);
        PID.backRightPid.setOutputLimits(PID.minPid, PID.maxPid);

    }

    public void Update(double speedModifier) {
        Motor.speedModifier = speedModifier;
    }

    public void Drive(double l_x_value, double l_y_value, double rotateSpeed, double backLeftAbsolutePosition, double backRightAbsolutePosition, double frontLeftAbsolutePosition, double frontRightAbsolutePosition) {

        //Setting Angle/Speed Motors
        double backLeftOutput = PID.backLeftPid.getOutput(backLeftAbsolutePosition, Motor.backLeftAngle);
        if (l_x_value == 0 && l_y_value == 0 && rotateSpeed == 0) {
            Motor.backLeftAngleMotor.set(0);
        } else {
            Motor.backLeftAngleMotor.set(backLeftOutput);
        }

        double backRightOutput = PID.backRightPid.getOutput(backRightAbsolutePosition, Motor.backRightAngle);
        if (l_x_value == 0 && l_y_value == 0 && rotateSpeed == 0) {
            Motor.backRightAngleMotor.set(0);
        } else {
            Motor.backRightAngleMotor.set(backRightOutput);
        }

        double frontRightOutput = PID.frontRightPid.getOutput(frontRightAbsolutePosition, Motor.frontRightAngle);
        if (l_x_value == 0 && l_y_value == 0 && rotateSpeed == 0) {
            Motor.frontRightAngleMotor.set(0);
        } else {
            Motor.frontRightAngleMotor.set(frontRightOutput);
        }

        double frontLeftOutput = PID.frontLeftPid.getOutput(frontLeftAbsolutePosition, Motor.frontLeftAngle);
        if (l_x_value == 0 && l_y_value == 0 && rotateSpeed == 0) {
            Motor.frontLeftAngleMotor.set(0);
        } else {
            Motor.frontLeftAngleMotor.set(frontLeftOutput);
        }

        //Setting Speed/Drive Motors
        Motor.backLeftSpeedMotor.set(Motor.backLeftSpeed * Motor.speedModifier);
        Motor.backRightSpeedMotor.set(Motor.backRightSpeed * Motor.speedModifier);
        Motor.frontLeftSpeedMotor.set(Motor.frontLeftSpeed * Motor.speedModifier);
        Motor.frontRightSpeedMotor.set(Motor.frontRightSpeed * Motor.speedModifier);

    }

}
