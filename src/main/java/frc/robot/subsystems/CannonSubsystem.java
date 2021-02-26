package frc.robot.subsystems;

import static frc.robot.Constants.CannonConstants.DEVICE_ID_ACTUATOR;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_CANNON_VALVE;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CannonSubsystem extends SubsystemBase {

  private final Spark valve = new Spark(DEVICE_ID_CANNON_VALVE);
  private final Servo actuator = new Servo(DEVICE_ID_ACTUATOR);
    
    public CannonSubsystem() {
      actuator.setBounds(2.0, 1.8, 1.525, 1.25, 1.05);
    }

    public void openValve() {
        valve.set(1);
    }

    public void closeValve() {
        valve.set(0);
    }

    public void raiseCannonToMax() {
      actuator.setSpeed(1.0);
    }

    public void lowerCannonToMin() {
      actuator.setSpeed(-1.0);
    }

}
