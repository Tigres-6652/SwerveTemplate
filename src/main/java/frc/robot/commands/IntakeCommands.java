package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mechanism.Intake;
import java.util.function.Supplier;

public class IntakeCommands extends Command {
  private final Intake intake;
  private Supplier<Boolean> Vel1, Vel2;

  public IntakeCommands(Intake intake, Supplier<Boolean> Vel1, Supplier<Boolean> Vel2) {
    this.Vel1 = Vel1;
    this.Vel2 = Vel2;
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (Vel1.get()) {
      intake.IntakeController(0.5);
    } else if (Vel2.get()) {
      intake.IntakeController(-0.5);
    } else {
      intake.IntakeController(0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
