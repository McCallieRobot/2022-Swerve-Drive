package frc.robot.Utilities;

public class Utilities {

    public static double getAngleFromXY(double XAxisValue, double YAxisValue) {
        // Normally Atan2 takes Y,X, not X,Y. We switch these around since we want 0
        // degrees to be straight up, not to the right like the unit circle;
        double angleInRadians = Math.atan2(-XAxisValue, YAxisValue);

        // Atan2 gives us a negative value for angles in the 3rd and 4th quadrants.
        // We want a full 360 degrees, so we will add 2 PI to negative values.
        if (angleInRadians < 0.0f)
            angleInRadians += (Math.PI * 2.0f);

        // Convert the radians to degrees. Degrees are easier to visualize.
        double angleInDegrees = (180.0f * angleInRadians / Math.PI);

        return angleInDegrees;
    }

    public static boolean inRange(double theta, double r1, double r2)
    {
        if (theta >= r1 && theta <= r2)
        {
            return true;
        }
        return false;
    }
}
