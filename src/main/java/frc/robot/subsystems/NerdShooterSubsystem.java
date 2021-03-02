package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.NerdShooters;

public class NerdShooterSubsystem extends SubsystemBase {

  private final WPI_TalonSRX flywheel;
  private final Servo pusher;
  private boolean targetOut = false;
  
  public NerdShooterSubsystem(NerdShooters shooter) {
    this.flywheel = new WPI_TalonSRX(shooter.getflywheelId());
    this.pusher = new Servo(shooter.getPuhserId());

    pusher.setBounds(1.2, 1.8, 1.1, 1.25, 1.0);
  }

  public void setPusherOut() {
    pusher.set(1.0);
    targetOut = true;
  }

  public void setPusherIn() {
    pusher.set(-1.0);
    targetOut = false;
  }

  public boolean isPusherAtMax() {
    return pusher.get() == 1.2;
  }

  public boolean isPusherAtMin() {
    return pusher.get() == 1.0;
  }

  public boolean isTargetOut() {
    return targetOut;
  }

  public void setFlywheelPower(double power) {
    flywheel.set(power);
  }

}
