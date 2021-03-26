package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class PressureSensor {

  private final AnalogInput input;

  public PressureSensor(int channel) {
    input = new AnalogInput(channel);
  }

  public double getPressure() {
    return (250 * (input.getVoltage() / 5) - 25);
  }
  
}
