package frc.robot.subsystems.mechanism;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Outake extends SubsystemBase {
  WPI_TalonSRX OutakeMotor = new WPI_TalonSRX(13);

  public void OutakeController(double OutakeSpeed) {
    OutakeMotor.set(OutakeSpeed);
  }

  public Outake() {}

  @Override
  public void periodic() {}
}
