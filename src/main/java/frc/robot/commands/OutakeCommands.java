package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mechanism.Outake;
import java.util.function.Supplier;

public class OutakeCommands extends Command {
  private final Outake outake;
  private Supplier<Boolean> VelMas1, VelMas2, VelMenos1, VelMenos2;
  private Supplier<Double> FreeMot;

  public OutakeCommands(
      Outake outake,
      Supplier<Boolean> VelMas1,
      Supplier<Boolean> VelMas2,
      Supplier<Boolean> VelMenos1,
      Supplier<Boolean> VelMenos2,
      Supplier<Double> FreeMot) {
    this.VelMas1 = VelMas1;
    this.VelMas2 = VelMas2;
    this.VelMenos1 = VelMenos1;
    this.VelMenos2 = VelMenos2;
    this.FreeMot = FreeMot;
    this.outake = outake;
    addRequirements(outake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    outake.OutakeController(FreeMot.get());

    if (VelMas1.get()) {
      outake.OutakeController(1);

    } else if (VelMas2.get()) {
      outake.OutakeController(0.5);

    } else if (VelMenos1.get()) {
      outake.OutakeController(-1);

    } else if (VelMenos2.get()) {
      outake.OutakeController(-0.5);

    } else {
      outake.OutakeController(0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
