package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TeleOpDriveCommand extends CommandBase {
    
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final XboxController xboxController;
    private PlaySound activePlaySound;
    public TeleOpDriveCommand(DriveTrainSubsystem driveTrainSubsystem, XboxController xboxController) {
        addRequirements(driveTrainSubsystem);
        this.driveTrainSubsystem = driveTrainSubsystem;
        this.xboxController = xboxController;
    }

    @Override
    public void initialize() {
       
    }
    public void playAudio(String clip){
      if (activePlaySound == null || !activePlaySound.getClip().equals(clip)){
        if (activePlaySound != null){
          activePlaySound.cancel();
        }
        activePlaySound = new PlaySound(clip);
        activePlaySound.schedule();
      }
    }
    @Override
    public void execute() {
        driveTrainSubsystem.drive(-xboxController.getY(Hand.kLeft), xboxController.getX(Hand.kRight));
        if (Math.abs(xboxController.getY(Hand.kLeft)) > 0.7){
          playAudio("fast");
        }
        else if (Math.abs(xboxController.getY(Hand.kLeft)) > 0.25){
          playAudio("slow");
        }
        else {
          playAudio("idle");
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrainSubsystem.drive(0, 0);
    }

}
