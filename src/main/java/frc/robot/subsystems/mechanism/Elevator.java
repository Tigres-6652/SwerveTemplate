package frc.robot.subsystems.mechanism;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  SparkMax RightElevatorMotor = new SparkMax(14, MotorType.kBrushless);
  SparkMax LeftElevatorMotor = new SparkMax(15, MotorType.kBrushless);

  double RightElevatorPosition =
      RightElevatorMotor.configAccessor.encoder.getPositionConversionFactor();
  double LeftElevatorPosition =
      LeftElevatorMotor.configAccessor.encoder.getPositionConversionFactor();

  DigitalInput limit = new DigitalInput(1);

  PIDController PIDConfig = new PIDController(0.1, 0, 0.001);

  double PulleyRad = 0.75; // radio de la polea
  double Reduction = 0.083; // Reduccion

  public double getElevatorHeight() {
    return (RightElevatorMotor.getEncoder().getPosition() * Reduction) * (2 * Math.PI * PulleyRad);
  }

  public double distanciaARevoluciones(double distancia) {
    return (distancia / (2 * Math.PI * PulleyRad)) * Reduction;
  }

  public void PstDist(double distancia) {
    RightElevatorMotor.set(PIDConfig.calculate(getElevatorHeight(), distancia));
  }

  public void freeMovement(double FreeSpeed) {
    RightElevatorMotor.set(FreeSpeed * 0.5);
  }

  public void Limite(double SpdArm) {
    if (getElevatorHeight() > 20 && SpdArm > -1) {
      RightElevatorMotor.set(-SpdArm);
      LeftElevatorMotor.set(-SpdArm);

    } else if (getElevatorHeight() < 20 && getElevatorHeight() > 4) {
      RightElevatorMotor.set(-SpdArm);
      LeftElevatorMotor.set(-SpdArm);

    } else if (getElevatorHeight() < 5 && SpdArm < -1) {
      RightElevatorMotor.set(-SpdArm);
      LeftElevatorMotor.set(-SpdArm);

    } else {
      RightElevatorMotor.set(0);
      LeftElevatorMotor.set(0);
    }
  }

  public void PosicionLimitada(double pulgadas) {
    Limite(-PIDConfig.calculate(getElevatorHeight(), pulgadas));
  }

  public Elevator() {
    SparkMaxConfig leaderConfig = new SparkMaxConfig();
    leaderConfig.inverted(true).idleMode(IdleMode.kBrake);
    RightElevatorMotor.configure(
        leaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig followerConfig = new SparkMaxConfig();
    followerConfig.inverted(false).idleMode(IdleMode.kBrake).follow(RightElevatorMotor);
    LeftElevatorMotor.configure(
        followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SmartDashboard.putNumber("Distancia", getElevatorHeight());

    if (limit.get() == true) {
      leaderConfig.signals.primaryEncoderPositionPeriodMs(0);
      followerConfig.signals.primaryEncoderPositionPeriodMs(0);
    }
  }

  @Override
  public void periodic() {}
}
