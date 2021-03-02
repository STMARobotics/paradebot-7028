package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class BurstNerdShooterCommand extends CommandBase {

  private final NerdShooterSubsystem nerdShooterSubsystem;
  private final XboxController controller;
  private final int shotsPerBurst;
  private int counter = 0;
  private boolean firing = false;
  private boolean inBurst = false;
  
  public BurstNerdShooterCommand(NerdShooterSubsystem nerdShooterSubsystem, XboxController controller, int shotsPerBurst) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;
    this.controller = controller;
    this.shotsPerBurst = shotsPerBurst;

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
      counter = 0;
      inBurst = true;
    }
    if (inBurst) {
      if (counter == shotsPerBurst && nerdShooterSubsystem.isPusherAtMin()) {
        inBurst = false;
        firing = false;
      } else if (!firing && nerdShooterSubsystem.isPusherAtMin()) {
        firing = true;
        nerdShooterSubsystem.setPusherOut();
        counter++;
      } else if (firing && nerdShooterSubsystem.isPusherAtMax()) {
        firing = false;
        nerdShooterSubsystem.setPusherIn();
      }
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
