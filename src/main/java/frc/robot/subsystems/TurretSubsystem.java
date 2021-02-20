package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  
  WPI_TalonSRX turret = new WPI_TalonSRX(TurretConstants.DEVICE_ID_TURRET);
  PigeonIMU pigeon = new PigeonIMU(turret);

  public TurretSubsystem() {

  }

  @Override
  public void periodic() {
    double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    SmartDashboard.putNumber("Yaw", ypr[0]);
  }
}
