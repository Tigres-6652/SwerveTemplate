package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mechanism.Elevator;
import java.util.function.Supplier;

public class ElevatorCommands extends Command {
  private final Elevator elevator;
  private Supplier<Double> FreeMovement;
  private Supplier<Boolean> Home, Coral1, Coral2;

  public ElevatorCommands(
      Elevator elevator,
      Supplier<Double> FreeMovement,
      Supplier<Boolean> Home,
      Supplier<Boolean> Coral1,
      Supplier<Boolean> Coral2) {
    this.FreeMovement = FreeMovement;
    this.Home = Home;
    this.Coral1 = Coral1;
    this.Coral2 = Coral2;
    this.elevator = elevator;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    SmartDashboard.putNumber("Distancia", elevator.getElevatorHeight());
    elevator.freeMovement(FreeMovement.get());

    /*if (Home.get()) {
      elevator.PosicionLimitada(0);

    } else if (Coral1.get()) {
      elevator.PosicionLimitada(-1);

    } else if (Coral2.get()) {
      elevator.PosicionLimitada(20);
    }*/
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
