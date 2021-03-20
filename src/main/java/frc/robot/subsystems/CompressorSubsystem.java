package frc.robot.subsystems;

import static frc.robot.Constants.CompressorConstants.DEVICE_ID_COMPRESSOR;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CompressorSubsystem extends SubsystemBase {

  private final Spark compressor = new Spark(DEVICE_ID_COMPRESSOR);

  public CompressorSubsystem() {
    
  }

  public void startCompressor() {
    compressor.set(1.0);
  }

  public void stopCompressor() {
    compressor.set(0.0);
  }

}
