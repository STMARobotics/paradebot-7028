package frc.robot.commands;

import static frc.robot.Constants.NerdShooterConstants.PUSHER_CYCLE_TIME;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NerdShooterSubsystem;

public class BurstNerdShooterCommand extends CommandBase {

  private final NerdShooterSubsystem nerdShooterSubsystem;
  private final XboxController controller;
  private final int shotsPerBurst;
  private int counter = 0;
  private boolean firing = false;
  private boolean inBurst = false;
  private final Timer timer = new Timer();

  public BurstNerdShooterCommand(NerdShooterSubsystem nerdShooterSubsystem, XboxController controller,
      int shotsPerBurst) {
    this.nerdShooterSubsystem = nerdShooterSubsystem;
    this.controller = controller;
    this.shotsPerBurst = shotsPerBurst;

    addRequirements(nerdShooterSubsystem);
  }

  @Override
  public void initialize() {
    timer.reset();
  }

  @Override
  public void execute() {
    if (controller.getYButton()) {
      nerdShooterSubsystem.setFlywheelPower(1.0);
    } else {
      nerdShooterSubsystem.setFlywheelPower(0.0);
    }
    if (controller.getXButtonPressed() && !inBurst) {
      counter = 0;
      inBurst = true;
    }
    if (inBurst) {
      if (counter == shotsPerBurst && timer.get() == PUSHER_CYCLE_TIME) {
        inBurst = false;
        firing = false;
        timer.stop();
        timer.reset();
      } else if (!firing && controller.getXButtonPressed()) {
        nerdShooterSubsystem.setPusherOut();
        firing = true;
        timer.start();
        counter++;
      } else if (!firing && timer.get() >= PUSHER_CYCLE_TIME) {
        nerdShooterSubsystem.setPusherOut();
        firing = true;
        timer.reset();
        counter++;
      } else if (firing && timer.get() >= PUSHER_CYCLE_TIME) {
        nerdShooterSubsystem.setPusherIn();
        firing = false;
        timer.reset();
      }
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
