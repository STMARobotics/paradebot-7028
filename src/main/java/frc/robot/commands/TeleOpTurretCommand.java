package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.OperatorButton;
import frc.robot.subsystems.TurretSubsystem;

public class TeleOpTurretCommand extends Command {

  private PlaySoundContinuousCommand rotationSound = new PlaySoundContinuousCommand("rotation");

  private final TurretSubsystem turretSubsystem;

  private final XboxController cannonController;

  public TeleOpTurretCommand(TurretSubsystem turretSubsystem, XboxController cannonController) {
    this.turretSubsystem = turretSubsystem;
    this.cannonController = cannonController;
    addRequirements(turretSubsystem);
  }

  @Override
  public void execute() {
    if (cannonController.getRawButton(OperatorButton.TURRET_LEFT.getButtonIndex())) {
      turretSubsystem.rotateLeft();
      rotationSound.schedule();
    } else if (cannonController.getRawButton(OperatorButton.TURRET_RIGHT.getButtonIndex())) {
      turretSubsystem.rotateRight();
      rotationSound.schedule();
    } else {
      turretSubsystem.stopRotation();
      rotationSound.cancel();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.stopRotation();;
  }
  
}
