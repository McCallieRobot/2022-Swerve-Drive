package frc.robot.Climber;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stormbots.MiniPID;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Controller.Controller;
import frc.robot.Utilities.ID;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber {
    private Controller controller;

    public static WPI_TalonSRX climberRight = new WPI_TalonSRX(ID.climberRightID);

    public Climber(Controller _controller) {
        controller = _controller;
    }

    public void Update()
    {
        if (controller.get().getAButton())
        {
            climberRight.set(1);
        }
        else if (controller.get().getBButton())
        {
            climberRight.set(-1);
        }
        else
        {
            climberRight.set(0);
        }
    }
}
