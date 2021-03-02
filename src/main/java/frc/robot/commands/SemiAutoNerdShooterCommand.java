package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class SemiAutoNerdShooterCommand extends CommandBase {
  
  private final NerdShooterSubsystem nerdShooterSubsystem;
  private final XboxController controller;
  private boolean firing = false;

  public SemiAutoNerdShooterCommand(NerdShooterSubsystem nerdShooterSubsystem, XboxController controller) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;
    this.controller = controller;

    addRequirements(nerdShooterSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (controller.getYButton()) {
      nerdShooterSubsystem.setFlywheelPower(1.0);
    } else {
      nerdShooterSubsystem.setFlywheelPower(0.0);
    }
    if (controller.getXButtonPressed()) {
      nerdShooterSubsystem.setPusherOut();
      firing = true;
    }
    if (firing && nerdShooterSubsystem.isPusherAtMax()) {
      nerdShooterSubsystem.setPusherIn();
      firing = false;
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    nerdShooterSubsystem.setFlywheelPower(0.0);
    nerdShooterSubsystem.setPusherIn();
  }

}
