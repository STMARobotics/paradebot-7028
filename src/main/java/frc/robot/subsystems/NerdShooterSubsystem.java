package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.NerdShooters;

public class NerdShooterSubsystem extends SubsystemBase {

  private final WPI_TalonSRX flywheel;
  private final DoubleSolenoid pusher;
  
  public NerdShooterSubsystem(NerdShooters shooter) {
    this.flywheel = new WPI_TalonSRX(shooter.getflywheelId());
    this.pusher = new DoubleSolenoid(shooter.getPusherForwardId(), shooter.getPusherReverseId());
  }

  public void setPusherOut() {
    pusher.set(Value.kForward);
  }

  public void setPusherIn() {
    pusher.set(Value.kReverse);
  }

  public void setFlywheelPower(double power) {
    flywheel.set(power);
  }

}
