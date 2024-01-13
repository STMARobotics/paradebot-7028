package frc.robot.subsystems;

import static frc.robot.Constants.CompressorConstants.DEVICE_ID_COMPRESSOR;
import static frc.robot.Constants.CompressorConstants.DEVICE_ID_SYSTEM_PRESSURE_SENSOR;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PressureSensor;

public class CompressorSubsystem extends SubsystemBase {

  private final Spark compressor = new Spark(DEVICE_ID_COMPRESSOR);
  private final PressureSensor pressureSensor = new PressureSensor(DEVICE_ID_SYSTEM_PRESSURE_SENSOR);

  public void startCompressor() {
    compressor.set(1.0);
  }

  public void stopCompressor() {
    compressor.set(0.0);
  }

  public double getPressure() {
    return pressureSensor.getPressure();
  }
}
