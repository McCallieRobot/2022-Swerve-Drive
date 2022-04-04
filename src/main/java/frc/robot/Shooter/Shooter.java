package frc.robot.Shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stormbots.MiniPID;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Controller.Controller;

import frc.robot.Utilities.ID;

public class Shooter {
    private Controller controller;
    public static CANSparkMax shooterMotor = new CANSparkMax(ID.shooterMotorID, MotorType.kBrushless);

    public static WPI_TalonSRX talonMotor = new WPI_TalonSRX(ID.talonMotorID);

    public Shooter(Controller _controller) {
        controller = _controller;
    }

    public void Update()
    {

        if (controller.get().getXButton())
        {
            shooterMotor.set(-0.65);
            talonMotor.set(.25);
            controller.get().setRumble(RumbleType.kLeftRumble, 1.0);
            controller.get().setRumble(RumbleType.kRightRumble, 1.0);
        }
        else
        {
            shooterMotor.set(0);
            talonMotor.set(0);
            controller.get().setRumble(RumbleType.kLeftRumble, 0.0);
            controller.get().setRumble(RumbleType.kRightRumble, 0.0);
        }
    }
}
