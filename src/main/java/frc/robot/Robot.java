// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.sensors.CANCoder;

import frc.robot.Controller.Controller;
import frc.robot.Controller.SpeedControl;
import frc.robot.SwerveDrive.FieldCentric;
import frc.robot.SwerveDrive.SwerveDrive;
import frc.robot.Utilities.ID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {

    CANCoder backLeftEncoder;
    CANCoder backRightEncoder;
    CANCoder frontRightEncoder;
    CANCoder frontLeftEncoder;

    FieldCentric fieldCentric = new FieldCentric();

    double speedModifier = .1;

    Controller controller = new Controller();
    SwerveDrive swerveDrive = new SwerveDrive(1, controller);

    @Override
    public void robotInit() {
        backLeftEncoder = new CANCoder(ID.backLeftEncoderID);
        backRightEncoder = new CANCoder(ID.backRightEncoderID);
        frontRightEncoder = new CANCoder(ID.frontRightEncoderID);
        frontLeftEncoder = new CANCoder(ID.frontLeftEncoderID);
    }

    @Override
    public void teleopPeriodic() {

        //This value is multiplied by the motor speed to get more control over speed
        speedModifier = SpeedControl.GetSpeedModifier(controller.get().getPOV(), speedModifier);
        swerveDrive.Update(speedModifier);

        //Getting the XY values of both the Left and Right Joysticks
        double l_x_value = Controller.l_stick.getXValue();
        double l_y_value = Controller.l_stick.getYValue();
        double r_x_value = Controller.r_stick.getXValue();
        double r_y_value = Controller.r_stick.getYValue();

        //Speed of all motors
        double motorSpeed = SpeedControl.GetMotorSpeed(l_x_value, l_y_value);

        //Speed of rotation
        double rotateSpeed = SpeedControl.GetRotateSpeed(r_x_value, r_y_value);

        //Calculate Wheel angles so that each wheel has a unique angle
        FieldCentric.CalculateWheelAngles(motorSpeed, rotateSpeed);

        //Get Current Angles of Motors
        double backLeftAbsolutePosition = backLeftEncoder.getAbsolutePosition();
        double backRightAbsolutePosition = backRightEncoder.getAbsolutePosition();
        double frontRightAbsolutePosition = frontRightEncoder.getAbsolutePosition();
        double frontLeftAbsolutePosition = frontLeftEncoder.getAbsolutePosition();

        //Move the motors
        swerveDrive.Drive(l_x_value, l_y_value, rotateSpeed, backLeftAbsolutePosition, backRightAbsolutePosition, frontLeftAbsolutePosition, frontRightAbsolutePosition);
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

    @Override
    public void disabledInit() {

    }
}