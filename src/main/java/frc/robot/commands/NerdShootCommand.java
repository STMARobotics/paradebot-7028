package frc.robot.commands;

import static frc.robot.Constants.NerdShooterConstants.PUSHER_FULL_CYCLE_TIME;
import static frc.robot.Constants.NerdShooterConstants.PUSHER_OUT_TIME;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NerdPusherSubsystem;

public class NerdShootCommand extends Command {

  private final NerdPusherSubsystem nerdPusherSubsystem;
  private Timer timer = new Timer();

  public NerdShootCommand(NerdPusherSubsystem nerdPusherSubsystem) {
    this.nerdPusherSubsystem = nerdPusherSubsystem;

    addRequirements(nerdPusherSubsystem);
  }

  @Override
  public void initialize() {
    nerdPusherSubsystem.setPusherOut();
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    if (timer.hasElapsed(PUSHER_OUT_TIME)) {
      nerdPusherSubsystem.setPusherIn();
    }
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(PUSHER_FULL_CYCLE_TIME);
  }

  @Override
  public void end(boolean interrupted) {
    nerdPusherSubsystem.setPusherIn();
    timer.stop();
  }

}
