package frc.robot.subsystems;

import static frc.robot.Constants.NerdShooterConstants.FLYWHEEL_POWER;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.NerdFlywheel;

public class NerdFlywheelSubsystem extends SubsystemBase {

  private final WPI_TalonSRX flywheel;

  public NerdFlywheelSubsystem(NerdFlywheel nerdFlywheel) {
    this.flywheel = new WPI_TalonSRX(nerdFlywheel.getflywheelId());
  }

  public void startFlywheel() {
    flywheel.set(FLYWHEEL_POWER);
  }

  public void stopFlywheel() {
    flywheel.set(0.0);
  }

}
