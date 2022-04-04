// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.sensors.CANCoder;

import frc.robot.Intake.Intake;
import frc.robot.Controller.Controller;
import frc.robot.Controller.SpeedControl;
import frc.robot.Shooter.Shooter;
import frc.robot.SwerveDrive.FieldCentric;
import frc.robot.SwerveDrive.SwerveDrive;
import frc.robot.Utilities.ID;
import frc.robot.Utilities.Utilities;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Shooter.*;

public class Robot extends TimedRobot {

    CANCoder backLeftEncoder;
    CANCoder backRightEncoder;
    CANCoder frontRightEncoder;
    CANCoder frontLeftEncoder;

    FieldCentric fieldCentric = new FieldCentric();

    double speedModifier = .1;

    Controller controller = new Controller();
    SwerveDrive swerveDrive = new SwerveDrive(1, controller);
    Shooter shooter = new Shooter(controller);
    Intake intake = new Intake(controller);

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

        //Update (Runtime) function on modules
        swerveDrive.Update(speedModifier);
        shooter.Update();
        intake.Update();

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

    double x_drive = 0;
    double y_drive = 0;
    @Override
    public void autonomousPeriodic() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry ty = table.getEntry("ty");
        double targetOffsetAngle_Vertical = ty.getDouble(0.0);

        // how many degrees back is your limelight rotated from perfectly vertical?
        double limelightMountAngleDegrees = 12.0;

        // distance from the center of the Limelight lens to the floor
        double limelightLensHeightInches = 6.5;

        //Angle of goal relative to gyro (gyroAngle of goal)
        double goalAngleDegrees = 0;

        double gyro_angle = FieldCentric.get_gyro_angle();

        // distance from the target to the floor
        double goalHeightInches = 18.0;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        //calculate distance
        double goalDistance = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);

        //System.out.println("Dist: " + goalDistance + " Ang: " + gyro_angle);

        //Get Current Angles of Motors
        double backLeftAbsolutePosition = backLeftEncoder.getAbsolutePosition();
        double backRightAbsolutePosition = backRightEncoder.getAbsolutePosition();
        double frontRightAbsolutePosition = frontRightEncoder.getAbsolutePosition();
        double frontLeftAbsolutePosition = frontLeftEncoder.getAbsolutePosition();

        //Rotate Robot so gyroangle == goalAngleDegrees


        //Drive robot 40 inches away from goal

        if (goalDistance >= 40) {
            if (Utilities.inRange(gyro_angle, 315, 360) || Utilities.inRange(gyro_angle, 0, 45))
            {
                x_drive = 0;
                y_drive = 1;
            }
            else if (Utilities.inRange(gyro_angle, 46, 135))
            {
                x_drive = -1;
                y_drive = 0;
            }
            else if (Utilities.inRange(gyro_angle, 136, 225))
            {
                x_drive = 0;
                y_drive = -1;
            }
            else if (Utilities.inRange(gyro_angle, 226, 314))
            {
                x_drive = 1;
                y_drive = 0;
            }
        }
        else
        {
            x_drive = 0;
            y_drive = 0; 
        }

        System.out.println("X: " + x_drive + " Y: " + y_drive);

        //Move the motors
        swerveDrive.Drive(x_drive, y_drive, 0, backLeftAbsolutePosition, backRightAbsolutePosition, frontLeftAbsolutePosition, frontRightAbsolutePosition);
    }

    @Override
    public void disabledInit() {

    }
}