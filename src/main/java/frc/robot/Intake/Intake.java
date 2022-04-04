package frc.robot.Intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stormbots.MiniPID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Controller.Controller;
import frc.robot.Utilities.ID;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake {
    private Controller controller;

    public static DoubleSolenoid arm = new DoubleSolenoid(ID.armID, 1);
    private boolean isExtended = false;

    public Intake(Controller _controller) {
        controller = _controller;
    }

    public void Update()
    {
        if (controller.get().getYButton())
        {
            if (isExtended == false) {
                arm.set(Value.kForward);
                isExtended = true;
            }
            else
            {
                arm.set(Value.kReverse);
                isExtended = false;
            }

        }
    }
}
