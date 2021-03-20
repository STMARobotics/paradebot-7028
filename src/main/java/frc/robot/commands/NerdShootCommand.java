package frc.robot.commands;

import static frc.robot.Constants.NerdShooterConstants.PUSHER_CYCLE_TIME;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class NerdShootCommand extends CommandBase {

  private final NerdShooterSubsystem nerdShooterSubsystem;
  private Timer timer = new Timer();
  private boolean firing = false;

  public NerdShootCommand(NerdShooterSubsystem nerdShooterSubsystem) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;

    addRequirements(nerdShooterSubsystem);
  }

  @Override
  public void initialize() {
    nerdShooterSubsystem.setPusherOut();
    firing = true;
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    nerdShooterSubsystem.setFlywheelPower(0.5);
    if (firing && timer.hasElapsed(PUSHER_CYCLE_TIME)) {
      nerdShooterSubsystem.setPusherIn();
      firing = false;
      timer.reset();
    }
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(PUSHER_CYCLE_TIME) && !firing;
  }

  @Override
  public void end(boolean interrupted) {
    nerdShooterSubsystem.setFlywheelPower(0.0);
    nerdShooterSubsystem.setPusherIn();
    timer.stop();
  }

}
