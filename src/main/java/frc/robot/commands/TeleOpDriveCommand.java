package frc.robot.commands;

import static frc.robot.Constants.DriverConstants.DEADBAND_HIGH;
import static frc.robot.Constants.DriverConstants.DEADBAND_LOW;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DeadbandFilter;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TeleOpDriveCommand extends CommandBase {

  private final DriveTrainSubsystem driveTrainSubsystem;
  private final XboxController xboxController;
  private final DeadbandFilter deadbandFilter = new DeadbandFilter(DEADBAND_LOW, DEADBAND_HIGH);
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
    double controllerY = deadbandFilter.calculate(-xboxController.getY(Hand.kLeft));
    double controllerX = deadbandFilter.calculate(xboxController.getX(Hand.kRight));
    driveTrainSubsystem.arcadeDrive(controllerY, controllerX);
    if (Math.abs(xboxController.getY(Hand.kLeft)) > 0.6 || Math.abs(xboxController.getX(Hand.kRight)) > 0.5) {
      fastSound.schedule();
      slowSound.cancel();
      idleSound.cancel();
    } else if (controllerX != 0 || controllerY != 0) {
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
    driveTrainSubsystem.arcadeDrive(0, 0);
    fastSound.cancel();
    slowSound.cancel();
    idleSound.cancel();
  }

}
