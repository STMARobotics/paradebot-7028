package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CannonSubsystem;

public class TeleopRegulatorCommand extends CommandBase {

  private final CannonSubsystem cannonSubsystem;
  private final XboxController xboxController;

  public TeleopRegulatorCommand(CannonSubsystem cannonSubsystem, XboxController joystick) {
    this.cannonSubsystem = cannonSubsystem;
    this.xboxController = joystick;

    addRequirements(cannonSubsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    cannonSubsystem.setPressureRegulatorPower(xboxController.getX(Hand.kRight));
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
