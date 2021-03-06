package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonSubsystem;

public class TeleopRegulatorCommand extends CommandBase {

  private final CannonSubsystem cannonSubsystem;
  private final Joystick joystick;

  public TeleopRegulatorCommand(CannonSubsystem cannonSubsystem, Joystick joystick) {
    this.cannonSubsystem = cannonSubsystem;
    this.joystick = joystick;

    addRequirements(cannonSubsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    cannonSubsystem.setPressureRegulatorPower(joystick.getZ());
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    cannonSubsystem.setPressureRegulatorPower(0.0);
  }

}
