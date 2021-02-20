package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TeleOpDriveCommand extends CommandBase {
    
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final XboxController xboxController;
    
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
        driveTrainSubsystem.drive(-xboxController.getY(Hand.kLeft), xboxController.getX(Hand.kLeft));
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
