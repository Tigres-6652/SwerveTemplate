package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.vision.WebCam;

public class VisionCommands extends Command {
  private final WebCam webCam;

  public VisionCommands(WebCam webCam) {
    this.webCam = webCam;
    addRequirements(webCam);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    webCam.periodic();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
