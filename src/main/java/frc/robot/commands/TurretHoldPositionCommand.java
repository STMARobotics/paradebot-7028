package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurretHoldPositionCommand extends CommandBase {
  
  private final TurretSubsystem turretSubsystem;
  private final XboxController xboxController;
  private double turretPosition;

  public TurretHoldPositionCommand(TurretSubsystem turretSubsystem, XboxController xboxController) {
    this.turretSubsystem = turretSubsystem;
    this.xboxController = xboxController;

    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    turretPosition = turretSubsystem.getGyroPosition();
  }

  @Override
  public void execute() {
    // assume 8096 is 360 degrees
    // Check if we're at limit before changing target
    double rightTrigger = xboxController.getTriggerAxis(Hand.kRight);
    double leftTrigger = xboxController.getTriggerAxis(Hand.kLeft);
    if (rightTrigger > .01) {
      turretPosition += (20 * rightTrigger);
    } else if (leftTrigger > .01) {
      turretPosition -= (20 * leftTrigger);
    }
    turretSubsystem.setPositionWithGyro(turretPosition);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.stopRotation();
  }
}

