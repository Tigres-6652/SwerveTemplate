package frc.robot.subsystems.mechanism;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  SparkMax IntakeElevatorMotor = new SparkMax(16, MotorType.kBrushless);

  public void IntakeController(double IntakeSpeed) {
    IntakeElevatorMotor.set(IntakeSpeed);
  }

  public Intake() {}

  @Override
  public void periodic() {}
}
