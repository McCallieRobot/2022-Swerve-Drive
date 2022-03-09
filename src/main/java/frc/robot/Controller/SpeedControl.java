package frc.robot.Controller;

public class SpeedControl {

    public static double GetSpeedModifier(int dpad, double currentSpeedModifier) 
    {
        if (dpad != -1) {
            // Up is 0, down is 180, left is 270, right is 90 -1 is inactive
            if (dpad == 0) {
                return 1;
            } else if (dpad == 90) {
                return .5;
            } else if (dpad == 180) {
                return .25;
            } else if (dpad == 270) {
                return .1;
            }
        }
        return currentSpeedModifier;
    }

    public static double GetRotateSpeed(double r_x_value, double r_y_value)
    {
        //Distance from center of joystick
        double rotateSpeed = Math.sqrt(Math.pow(r_x_value - 0, 2) + Math.pow(r_y_value - 0, 2));

        // Make rotateSpeed go from -1 to 1
        if (r_x_value > 0) {
            rotateSpeed = -rotateSpeed;
        } else if (r_x_value < 0) {
            rotateSpeed = Math.abs(rotateSpeed);
        }

        if (rotateSpeed > 1) // Cap the rotate speed at 1
            rotateSpeed = 1;
        else if (rotateSpeed < -1) {
            rotateSpeed = -1;
        }

        return rotateSpeed;
    }

    public static double GetMotorSpeed(double l_x_value, double l_y_value)
    {
        double motorSpeed = Math.sqrt(Math.pow(l_x_value - 0, 2) + Math.pow(l_y_value - 0, 2));
        if (motorSpeed > 1)
            motorSpeed = 1;
        
        return motorSpeed;
    }
}
