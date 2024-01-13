package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NerdFlywheelSubsystem;

public class NerdRevCommand extends Command {
  
  private final NerdFlywheelSubsystem nerdFlywheelSubsystem;

  public NerdRevCommand(NerdFlywheelSubsystem nerdFlywheelSubsystem) {
    this.nerdFlywheelSubsystem = nerdFlywheelSubsystem;

    addRequirements(nerdFlywheelSubsystem);
  }

  @Override
  public void execute() {
    nerdFlywheelSubsystem.startFlywheel();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    nerdFlywheelSubsystem.stopFlywheel();
  }

}
