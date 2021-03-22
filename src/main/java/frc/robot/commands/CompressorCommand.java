package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CompressorSubsystem;

public class CompressorCommand extends CommandBase {

  private final CompressorSubsystem compressorSubsystem;

  public CompressorCommand(CompressorSubsystem compressorSubsystem) {
    this.compressorSubsystem = compressorSubsystem;
    addRequirements(compressorSubsystem);
  }

  @Override
  public void initialize() {
    compressorSubsystem.startCompressor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    compressorSubsystem.stopCompressor();
  }
  
}
