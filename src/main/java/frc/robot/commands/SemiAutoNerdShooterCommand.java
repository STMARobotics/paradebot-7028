package frc.robot.commands;

import static frc.robot.Constants.NerdShooterConstants.PUSHER_CYCLE_TIME;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class SemiAutoNerdShooterCommand extends CommandBase {

  private final NerdShooterSubsystem nerdShooterSubsystem;
  private final XboxController controller;
  private boolean firing = false;
  private Timer timer = new Timer();

  public SemiAutoNerdShooterCommand(NerdShooterSubsystem nerdShooterSubsystem, XboxController controller) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;
    this.controller = controller;

    addRequirements(nerdShooterSubsystem);
  }

  @Override
  public void initialize() {
    nerdShooterSubsystem.setPusherIn();
    timer.reset();
  }

  @Override
  public void execute() {
    if (controller.getYButton()) {
      nerdShooterSubsystem.setFlywheelPower(1.0);
    } else {
      nerdShooterSubsystem.setFlywheelPower(0.0);
    }
    if (!firing && controller.getXButtonPressed()) {
      nerdShooterSubsystem.setPusherOut();
      firing = true;
      timer.start();
    } else if (firing && timer.get() >= PUSHER_CYCLE_TIME) {
      nerdShooterSubsystem.setPusherIn();
      firing = false;
      timer.stop();
      timer.reset();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    nerdShooterSubsystem.setFlywheelPower(0.0);
    nerdShooterSubsystem.setPusherIn();
    timer.stop();
  }

}
