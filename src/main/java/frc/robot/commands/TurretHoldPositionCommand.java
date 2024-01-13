package frc.robot.commands;

import static frc.robot.Constants.TurretConstants.TELEOP_MAX_CHANGE;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.OperatorButton;
import frc.robot.subsystems.TurretSubsystem;

public class TurretHoldPositionCommand extends Command {

  private final TurretSubsystem turretSubsystem;
  private final XboxController cannonController;
  private double turretPosition;

  public TurretHoldPositionCommand(TurretSubsystem turretSubsystem, XboxController cannonController) {
    this.turretSubsystem = turretSubsystem;
    this.cannonController = cannonController;

    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    turretPosition = turretSubsystem.getGyroPosition();
  }

  @Override
  public void execute() {
    if (cannonController.getRawButton(OperatorButton.TURRET_LEFT.getButtonIndex())
        && !turretSubsystem.isAtReverseLimit()) {
      turretPosition -= TELEOP_MAX_CHANGE;
    } else if (cannonController.getRawButton(OperatorButton.TURRET_RIGHT.getButtonIndex())
        && !turretSubsystem.isAtForwardLimit()) {
      turretPosition += TELEOP_MAX_CHANGE;
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

