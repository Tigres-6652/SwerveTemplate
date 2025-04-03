package frc.robot.subsystems.mechanism;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  WPI_TalonSRX TalonELevatorRigt = new WPI_TalonSRX(13);
  WPI_TalonSRX TalonELevatorLeft = new WPI_TalonSRX(14);

  DigitalInput LimitSwich = new DigitalInput(1);

  double position;

  public Elevator() {
    TalonELevatorRigt.configFactoryDefault();
    TalonELevatorLeft.configFactoryDefault();

    TalonELevatorRigt.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    TalonELevatorRigt.setSensorPhase(true);

    TalonELevatorRigt.configNominalOutputForward(0, 30);
    TalonELevatorRigt.configNominalOutputReverse(0, 30);
    TalonELevatorRigt.configPeakOutputForward(1, 30);
    TalonELevatorRigt.configPeakOutputReverse(-1, 30);

    TalonELevatorRigt.configAllowableClosedloopError(0, 0, 0);

    TalonELevatorRigt.config_kP(3, 0, 30);
    TalonELevatorRigt.config_kI(0, 0, 30);
    TalonELevatorRigt.config_kD(0, 0, 30);
    TalonELevatorRigt.config_kF(0, 0, 30);
  }

  public double absolutePosition() {
    return TalonELevatorRigt.getSensorCollection().getPulseWidthPosition() * 0.5;
  }

  public void elevatorPosition(double input) {
    input = position / 4096 * 0.5;
    TalonELevatorRigt.setSelectedSensorPosition(position, 0, 30);
    TalonELevatorLeft.setSelectedSensorPosition(position, 0, 30);
  }

  public void freePosition(double speed) {
    TalonELevatorLeft.set(speed);
    TalonELevatorRigt.set(speed);
  }

  public void resetPosition() {
    TalonELevatorRigt.configFactoryDefault();
    TalonELevatorLeft.configFactoryDefault();
  }

  public boolean Limit() {
    return LimitSwich.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("absolute position", absolutePosition());
    SmartDashboard.putBoolean("limit", Limit());
  }
}
