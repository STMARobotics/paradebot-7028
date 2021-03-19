package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TeleOpDriveCommand extends CommandBase {

  private final DriveTrainSubsystem driveTrainSubsystem;
  private final XboxController xboxController;
  private PlaySoundContinuousCommand fastSound = new PlaySoundContinuousCommand("fast");
  private PlaySoundContinuousCommand slowSound = new PlaySoundContinuousCommand("slow");
  private PlaySoundContinuousCommand idleSound = new PlaySoundContinuousCommand("idle");

  public TeleOpDriveCommand(DriveTrainSubsystem driveTrainSubsystem, XboxController xboxController) {
    addRequirements(driveTrainSubsystem);
    this.driveTrainSubsystem = driveTrainSubsystem;
    this.xboxController = xboxController;
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    driveTrainSubsystem.drive(-xboxController.getY(Hand.kLeft), xboxController.getX(Hand.kRight));
    if (Math.abs(xboxController.getY(Hand.kLeft)) > 0.6 || Math.abs(xboxController.getX(Hand.kRight)) > 0.5) {
      fastSound.schedule();
      slowSound.cancel();
      idleSound.cancel();
    } else if (Math.abs(xboxController.getY(Hand.kLeft)) > 0.25 || Math.abs(xboxController.getX(Hand.kRight)) > 0.34) {
      fastSound.cancel();
      slowSound.schedule();
      idleSound.cancel();
    } else {
      fastSound.cancel();
      slowSound.cancel();
      idleSound.schedule();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    driveTrainSubsystem.drive(0, 0);
    fastSound.cancel();
    slowSound.cancel();
    idleSound.cancel();
  }

}
