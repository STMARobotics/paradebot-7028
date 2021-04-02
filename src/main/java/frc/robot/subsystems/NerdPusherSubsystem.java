package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.NerdPusher;

public class NerdPusherSubsystem extends SubsystemBase {

  private final DoubleSolenoid pusher;

  public NerdPusherSubsystem(NerdPusher nerdPusher) {
    this.pusher = new DoubleSolenoid(nerdPusher.getForwardId(), nerdPusher.getReverseId());
  }

  public void setPusherOut() {
    pusher.set(Value.kForward);
  }

  public void setPusherIn() {
    pusher.set(Value.kReverse);
  }
  
}
