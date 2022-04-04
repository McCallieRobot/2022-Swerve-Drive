package frc.robot.SwerveDrive;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.Controller.Controller;
import frc.robot.SwerveDrive.SwerveDrive.Motor;

import edu.wpi.first.wpilibj.SPI;

public class FieldCentric {

    //To-do: Cleanup this, only have one of these and just replace vars

    private static AHRS gyro; 

    public FieldCentric()
    {
        // Gyroscope
        gyro = new AHRS(SPI.Port.kMXP);
    }

    public static double get_gyro_angle()
    {
        return gyro.getYaw();
    }

    public static void CalculateWheelAngles(double motorSpeed, double rotateSpeed) {

        double stickAngle = Controller.l_stick.getAngle();
        double gyroAngle = (gyro.getYaw() + 180);
        
        double robotDirection = stickAngle - gyroAngle; // This is the field oriented direction of the robot

        // Individually Controlled wheel angles
        double robotDirectionRadians = Math.toRadians(robotDirection);

        double x1 = -motorSpeed * Math.sin(robotDirectionRadians) + rotateSpeed * Math.sin(Math.PI / 4);
        double y1 = -motorSpeed * Math.cos(robotDirectionRadians) + rotateSpeed * Math.cos(Math.PI / 4);
        double x1temp = Math.atan2(x1, y1);
        double x1temp1 = Math.toDegrees(x1temp);
        double flAngle = x1temp1;

        if (x1temp1 < 0) {
            x1temp1 = 360 + x1temp1;
        } else if (x1temp1 > 360) {
            x1temp1 = x1temp1 % 360;
        }

        if (x1temp1 - Motor.frontLeftAngle > 90 || x1temp1 - Motor.frontLeftAngle < -90){

            if (x1temp1 > 180){
                x1temp1 = x1temp1 - 180;
            } else {
                x1temp1 = x1temp1 + 180;
            }

            Motor.frontLeftAngle = x1temp1;
            Motor.frontLeftSpeed = -(Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)));
            
        } else {
            Motor.frontLeftAngle = flAngle;
            Motor.frontLeftSpeed = (Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)));
        }

        double x2 = -motorSpeed * Math.sin(robotDirectionRadians) + rotateSpeed * Math.sin(3 * Math.PI / 4);
        double y2 = -motorSpeed * Math.cos(robotDirectionRadians) + rotateSpeed * Math.cos(3 * Math.PI / 4);
        double x2temp = Math.atan2(x2, y2);
        double x2temp1 = Math.toDegrees(x2temp);
        double frAngle = x2temp1;
        
        if (x2temp1 < 0) {
            x2temp1 = 360 + x2temp1;
        } else if (x2temp1 > 360) {
            x2temp1 = x2temp1 % 360;
        }

        if (x2temp1 - Motor.frontRightAngle > 90 || x2temp1 - Motor.frontRightAngle < -90){
            
            if (x2temp1 > 180){
                x2temp1 = x2temp1 - 180;
            } else {
                x2temp1 = x2temp1 + 180;
            }

            Motor.frontRightAngle = x2temp1;
            Motor.frontRightSpeed = -(Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)));
            
        } else {
            Motor.frontRightAngle = frAngle;
            Motor.frontRightSpeed = (Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)));
        }

        double x3 = -motorSpeed * Math.sin(robotDirectionRadians) + rotateSpeed * Math.sin(5 * Math.PI / 4);
        double y3 = -motorSpeed * Math.cos(robotDirectionRadians) + rotateSpeed * Math.cos(5 * Math.PI / 4);
        double x3temp = Math.atan2(x3, y3);
        double x3temp1 = Math.toDegrees(x3temp);
        double brAngle = x3temp1;

        if (x3temp1 < 0) {
            x3temp1 = 360 + x3temp1;
        } else if (x3temp1 > 360) {
            x3temp1 = x3temp1 % 360;
        }

        if (x3temp1 - Motor.backRightAngle > 90 || x3temp1 - Motor.backRightAngle < -90){
            
            if (x3temp1 > 180){
                x3temp1 = x3temp1 - 180;
            } else {
                x3temp1 = x3temp1 + 180;
            }

            Motor.backRightAngle = x3temp1;
            Motor.backRightSpeed = -(Math.sqrt(Math.pow(x3, 2) + Math.pow(y3, 2)));
            
        } else {
            Motor.backRightAngle = brAngle;
            Motor.backRightSpeed = (Math.sqrt(Math.pow(x3, 2) + Math.pow(y3, 2)));
        }

        double x4 = -motorSpeed * Math.sin(robotDirectionRadians) + rotateSpeed * Math.sin(7 * Math.PI / 4);
        double y4 = -motorSpeed * Math.cos(robotDirectionRadians) + rotateSpeed * Math.cos(7 * Math.PI / 4);
        double x4temp = Math.atan2(x4, y4);
        double x4temp1 = Math.toDegrees(x4temp);
        double blAngle = x4temp1;

        if (x4temp1 < 0) {
            x4temp1 = 360 + x4temp1;
        } else if (x4temp1 > 360) {
            x4temp1 = x4temp1 % 360;
        }

        if (x4temp1 - Motor.backLeftAngle > 90 || x4temp1 - Motor.backLeftAngle < -90){
            
            if (x4temp1 > 180){
                x4temp1 = x4temp1 - 180;
            } else {
                x4temp1 = x4temp1 + 180;
            }

            Motor.backLeftAngle = x4temp1;
            Motor.backLeftSpeed = -(Math.sqrt(Math.pow(x4, 2) + Math.pow(y4, 2)));
            
        } else {
            Motor.backLeftAngle = blAngle;
            Motor.backLeftSpeed = (Math.sqrt(Math.pow(x4, 2) + Math.pow(y4, 2)));
        }
    }
}
