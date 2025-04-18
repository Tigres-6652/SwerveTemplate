package frc.robot.subsystems.mechanism;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
  WPI_TalonSRX TalonELevatorRigt = new WPI_TalonSRX(13);
  WPI_TalonSRX TalonELevatorLeft = new WPI_TalonSRX(14);

  DigitalInput LimitSwich = new DigitalInput(0);

  public void EjeElevador(double velinf) {
    if (LimitSwich.get() && DistanciaElevador() < -350) {
      TalonELevatorLeft.set(-velinf);

    } else if (!LimitSwich.get() && velinf < 0.001) {
      TalonELevatorLeft.set(-velinf);

    } else if (!LimitSwich.get() && velinf > 0.001) {
      TalonELevatorLeft.set(0);

    } else if (DistanciaElevador() > -300 && velinf < 0.001) {
      TalonELevatorLeft.set(0);

    } else if (DistanciaElevador() > -300 && velinf > 0.001 && LimitSwich.get()) {
      TalonELevatorLeft.set(-velinf);
    }
    if (!LimitSwich.get()) {
      TalonELevatorLeft.setSelectedSensorPosition(0);
    }
  }

  public double DistanciaElevador() {
    double pulsosSensor = TalonELevatorLeft.getSelectedSensorPosition();
    double vuelta_eje_con_reduccion = ((pulsosSensor) / (4096));
    double Distancia = (vuelta_eje_con_reduccion / 4.5714) * (360);
    return -Distancia;
  }

  public double DistanciaElevadorPulsos(double gradosInf) {
    double pulsos = (((((gradosInf) / 360) * 4.5714) * 4096));
    return pulsos;
  }

  public void returnhome(boolean status) {
    if (status) {
      if (!LimitSwich.get()) {
        TalonELevatorLeft.set(0);

      } else {
        TalonELevatorLeft.set(-0.8);
      }
    }
  }

  public void speed(double v) {
    TalonELevatorRigt.set(v);
    TalonELevatorLeft.set(v);
  }

  public void MovimientoElevador(double Posiscion) {
    double posicion_inferior = DistanciaElevadorPulsos(Posiscion);
    TalonELevatorLeft.set(ControlMode.Position, posicion_inferior);
  }

  public void ResetEncoderLimit(Boolean SetZero) {
    if (SetZero) {
      TalonELevatorLeft.setSelectedSensorPosition(0);
    }
  }

  public void ConfiguracionMotor() {
    TalonELevatorLeft.configFactoryDefault();

    TalonELevatorLeft.configNominalOutputForward(0, 30);
    TalonELevatorLeft.configNominalOutputReverse(0, 30);
    TalonELevatorLeft.configPeakOutputForward(1, 30);
    TalonELevatorLeft.configPeakOutputReverse(-1, 30);

    TalonELevatorLeft.config_kF(0, 0, 30);
    TalonELevatorLeft.config_kP(0, 1, 30);
    TalonELevatorLeft.config_kI(0, 0, 30);
    TalonELevatorLeft.config_kD(0, 0, 30);

    TalonELevatorLeft.setSensorPhase(true);

    TalonELevatorRigt.follow(TalonELevatorLeft);
  }

  public Elevator() {}

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distancia", DistanciaElevador());
    SmartDashboard.putBoolean("Limitdirect", LimitSwich.get());
  }
}
