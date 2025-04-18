package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mechanism.Outake;
import java.util.function.Supplier;

public class OutakeCommands extends Command {
  private final Outake outake;
  private Supplier<Double> Disp, Apunt;

  public OutakeCommands(Outake outake, Supplier<Double> Disp, Supplier<Double> Apunt) {
    this.Disp = Disp;
    this.Apunt = Apunt;
    this.outake = outake;
    addRequirements(outake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    outake.OutakeControl(Disp.get() * 0.5);
    outake.OutakeControl(Apunt.get() * 0.7);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
