package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mechanism.Elevator;
import java.util.function.Supplier;

public class ElevatorCommand extends Command {
  private final Elevator elevator;
  private Supplier<Boolean> Home, Pstn1, Pstn2, BlqFree, setZero;
  private Supplier<Double> Free;

  public ElevatorCommand(
      Elevator elevator,
      Supplier<Boolean> Home,
      Supplier<Boolean> Pstn1,
      Supplier<Boolean> Pstn2,
      Supplier<Boolean> BlqFree,
      Supplier<Boolean> setZero,
      Supplier<Double> Free) {
    this.Home = Home;
    this.Pstn1 = Pstn1;
    this.Pstn2 = Pstn2;
    this.BlqFree = BlqFree;
    this.setZero = setZero;
    this.Free = Free;
    this.elevator = elevator;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    elevator.ConfiguracionMotor();
    elevator.ResetEncoderLimit(true);
  }

  @Override
  public void execute() {
    if (Home.get()) {
      elevator.MovimientoElevador(0);

    } else if (Pstn1.get()) {
      elevator.MovimientoElevador(-110);

    } else if (Pstn2.get()) {
      elevator.MovimientoElevador(-300);

    } else if (BlqFree.get()) {
      elevator.speed(Free.get());

    } else {
      elevator.EjeElevador(0);
    }

    elevator.ResetEncoderLimit(setZero.get());
  }

  @Override
  public void end(boolean interrupted) {
    elevator.EjeElevador(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
