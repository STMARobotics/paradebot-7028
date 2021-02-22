package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class HoldPositionCommand extends CommandBase {
  
  private final TurretSubsystem turretSubsystem;
  private final XboxController xboxController;
  private double turretPosition;

  public HoldPositionCommand(TurretSubsystem turretSubsystem, XboxController xboxController) {
    this.turretSubsystem = turretSubsystem;
    this.xboxController = xboxController;

    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    turretPosition = turretSubsystem.getPosition();
  }

  @Override
  public void execute() {
    turretSubsystem.setPosition(turretPosition);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.stop();
  }
}

