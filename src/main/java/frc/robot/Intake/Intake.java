package frc.robot.Intake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stormbots.MiniPID;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.Controller.Controller;
import frc.robot.Utilities.ID;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.PnuematicHub;

public class Intake {
    private Controller controller;

    public static DoubleSolenoid arm0 = new DoubleSolenoid(ID.arm0ID, 0, 1);
    public static DoubleSolenoid arm1 = new DoubleSolenoid(ID.arm1ID, 2, 3);

    public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(ID.intakeMotorID);
    public static WPI_TalonSRX loadMotor = new WPI_TalonSRX(ID.loadMotorID);

    public Intake(Controller _controller) {
        controller = _controller;
    }

    public void Update()
    {
        if (controller.get().getYButton())
        {
            loadMotor.set(.5);
            intakeMotor.set(-.7);
        }
        else
        {
            intakeMotor.set(0);
            loadMotor.set(0);
        }

        if (controller.get().getBumper(Hand.kRight))
        {
            arm0.set(Value.kForward);
            arm1.set(Value.kForward);
        }
        if (controller.get().getBumper(Hand.kLeft))
        {
            arm0.set(Value.kReverse);
            arm1.set(Value.kReverse);
        }
    }
}
