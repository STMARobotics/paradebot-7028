package frc.robot.commands;

import static frc.robot.Constants.CannonConstants.PRESSURE_REGULATOR_MAX;
import static frc.robot.Constants.CannonConstants.PRESSURE_REGULATOR_MIN;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonSubsystem;

public class ManageRegulatorCommand extends CommandBase {

  private final CannonSubsystem cannonSubsystem;
  private final Joystick joystick;

  public ManageRegulatorCommand(CannonSubsystem cannonSubsystem, Joystick joystick) {
    this.cannonSubsystem = cannonSubsystem;
    this.joystick = joystick;

    addRequirements(cannonSubsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    cannonSubsystem.setPressureRegulatorPosition(
      ((PRESSURE_REGULATOR_MAX + PRESSURE_REGULATOR_MIN) / 2) * (1 + joystick.getZ()));
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    
  }
  
}