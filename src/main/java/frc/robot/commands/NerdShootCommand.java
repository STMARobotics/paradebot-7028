package frc.robot.commands;

import static frc.robot.Constants.NerdShooterConstants.PUSHER_CYCLE_TIME;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class NerdShootCommand extends CommandBase {

  private final NerdShooterSubsystem nerdShooterSubsystem;
  private Timer timer = new Timer();

  public NerdShootCommand(NerdShooterSubsystem nerdShooterSubsystem) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;

    addRequirements(nerdShooterSubsystem);
  }

  @Override
  public void initialize() {
    nerdShooterSubsystem.setPusherOut();
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    nerdShooterSubsystem.setFlywheelPower(0.5);
    if (timer.hasElapsed(PUSHER_CYCLE_TIME / 2.0d)) {
      nerdShooterSubsystem.setPusherIn();
    }
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(PUSHER_CYCLE_TIME);
  }

  @Override
  public void end(boolean interrupted) {
    nerdShooterSubsystem.setFlywheelPower(0.0);
    nerdShooterSubsystem.setPusherIn();
    timer.stop();
  }

}
