package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonSubsystem;

public class ShootCommand extends CommandBase {

    private final CannonSubsystem cannonSubsystem;
    
    public ShootCommand(CannonSubsystem cannonSubsystem) {
        this.cannonSubsystem = cannonSubsystem;
    }

    @Override
    public void initialize() {
        cannonSubsystem.openValve();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        cannonSubsystem.closeValve();
    }

}
