package frc.robot.Controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Utilities.Utilities;

public class Controller {

    private static XboxController controller;

    private static double l_x_value;
    private static double l_y_value;

    private static double r_x_value;
    private static double r_y_value;

    public Controller() {
        controller = new XboxController(0);
    }

    public XboxController get() {
        return controller;
    }

    public double getRTrigger()
    {
        return controller.getTriggerAxis(Hand.kRight);
    }

    public static class l_stick {

        public static double getStickX() { // l_stick_x
            return controller.getX(Hand.kLeft);
        }

        public static double getStickY() { // l_stick_y
            return controller.getY(Hand.kLeft);
        }

        public static double getXValue() // l_x_value
        {
            double l_stick_x = getStickX();

            if ((l_stick_x < .2 && l_stick_x > 0) || (l_stick_x > -.2 && l_stick_x < 0)) {
                l_x_value = 0;
            } else {
                l_x_value = l_stick_x;
            }

            return l_x_value;
        }

        public static double getYValue() // l_y_value
        {
            double l_stick_y = getStickY();

            if ((l_stick_y < .2 && l_stick_y > 0) || (l_stick_y > -.2 && l_stick_y < 0)) {
                l_y_value = 0;
            } else {
                l_y_value = l_stick_y;
            }

            return l_y_value;
        }

        public static double getAngle() {
            return Utilities.getAngleFromXY(getStickX(), getStickY());
        }

    }

    public static class r_stick {

        public static double getStickX() { // r_stick_x
            return controller.getX(Hand.kRight);
        }

        public static double getStickY() { // r_stick_y
            return controller.getY(Hand.kRight);
        }

        public static double getXValue() // r_x_value
        {
            double r_stick_x = getStickX();

            if ((r_stick_x < .2 && r_stick_x > 0) || (r_stick_x > -.2 && r_stick_x < 0)) {
                r_x_value = 0;
            } else {
                r_x_value = r_stick_x;
            }

            return r_x_value;
        }

        public static double getYValue() // r_y_value
        {
            double r_stick_y = getStickY();

            if ((r_stick_y < .2 && r_stick_y > 0) || (r_stick_y > -.2 && r_stick_y < 0)) {
                r_y_value = 0;
            } else {
                r_y_value = r_stick_y;
            }

            return r_y_value;
        }

        public static double getAngle() {
            return Utilities.getAngleFromXY(getStickX(), getStickY());
        }

    }
}
