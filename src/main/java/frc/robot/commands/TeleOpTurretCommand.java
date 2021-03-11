package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TeleOpTurretCommand extends CommandBase {

  private PlaySound rotationSound;

  private final TurretSubsystem turretSubsystem;

  private final XboxController xboxController;

  public TeleOpTurretCommand(TurretSubsystem turretSubsystem, XboxController xboxController) {
    this.turretSubsystem = turretSubsystem;
    this.xboxController = xboxController;
    addRequirements(turretSubsystem);
  }

  @Override
  public void execute() {
    double leftTrigger = xboxController.getTriggerAxis(Hand.kLeft);
    double rightTrigger =  xboxController.getTriggerAxis(Hand.kRight);

    if (leftTrigger > 0.08 || rightTrigger > 0.08){
      rotationSound = new PlaySound("rotation");
      rotationSound.schedule();
    }
    else if (rotationSound != null) {
      rotationSound.cancel();
    }

    if (leftTrigger > rightTrigger) {
      turretSubsystem.rotate(-leftTrigger / 12);
    } else {
      turretSubsystem.rotate(rightTrigger / 12);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.rotate(0);
  }
  
}
