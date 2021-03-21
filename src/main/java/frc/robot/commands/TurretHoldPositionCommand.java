package frc.robot.commands;

import static frc.robot.Constants.TurretConstants.TELEOP_MAX_CHANGE;

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
    double rightTrigger = xboxController.getTriggerAxis(Hand.kRight);
    double leftTrigger = xboxController.getTriggerAxis(Hand.kLeft);
    if (leftTrigger > rightTrigger && !turretSubsystem.isAtReverseLimit()) {
      turretPosition -= (TELEOP_MAX_CHANGE * leftTrigger);
    } else if (!turretSubsystem.isAtForwardLimit()) {
      turretPosition += (TELEOP_MAX_CHANGE * rightTrigger);
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

