package frc.robot.commands;

import static frc.robot.Constants.TurretConstants.TELEOP_MAX_OUTPUT;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TeleOpTurretCommand extends CommandBase {

  private PlaySoundContinuousCommand rotationSound = new PlaySoundContinuousCommand("rotation");

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
    double rightTrigger = xboxController.getTriggerAxis(Hand.kRight);

    if (leftTrigger > 0.4 || rightTrigger > 0.4) {
      rotationSound.schedule();
    } else {
      rotationSound.cancel();
    }

    if (leftTrigger > rightTrigger) {
      turretSubsystem.rotate(-leftTrigger * TELEOP_MAX_OUTPUT);
    } else {
      turretSubsystem.rotate(rightTrigger * TELEOP_MAX_OUTPUT);
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
