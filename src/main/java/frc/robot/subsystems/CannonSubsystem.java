package frc.robot.subsystems;

import static frc.robot.Constants.CannonConstants.DEVICE_ID_CANNON_VALVE;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CannonSubsystem extends SubsystemBase {

  private final Spark valve = new Spark(DEVICE_ID_CANNON_VALVE);
    
    public void openValve() {
        valve.set(1);
    }

    public void closeValve() {
        valve.set(0);
    }

}
