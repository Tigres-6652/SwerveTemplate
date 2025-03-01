package frc.robot.subsystems.mechanism;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Outake extends SubsystemBase {
  WPI_TalonSRX RightOutakeMotor = new WPI_TalonSRX(13);
  WPI_TalonSRX LeftOutakeMotor = new WPI_TalonSRX(14);

  public void OutakeController(double OutakeSpeed) {
    RightOutakeMotor.set(OutakeSpeed);
    LeftOutakeMotor.set(OutakeSpeed);
  }

  public Outake() {}

  @Override
  public void periodic() {}
}
