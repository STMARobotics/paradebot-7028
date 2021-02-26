package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
  
  WPI_TalonSRX turret = new WPI_TalonSRX(TurretConstants.DEVICE_ID_TURRET);
  PigeonIMU pigeon = new PigeonIMU(turret);

  public TurretSubsystem() {
    turret.configFactoryDefault();
    turret.configSelectedFeedbackSensor(RemoteFeedbackDevice.RemoteSensor0);
    turret.configRemoteFeedbackFilter(pigeon.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw, 0);
  }

  @Override
  public void periodic() {
    double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    SmartDashboard.putNumber("Yaw", ypr[0]);
  }

  public double getPosition() {
    double[] position = new double[3];
    pigeon.getYawPitchRoll(position);
    return position[0];
  }

  public void setPosition(double position) {
    turret.set(ControlMode.Position, position);    
  }

  public void rotate(double speed) {
    turret.set(speed);
  }

  public void stop() {
    turret.set(ControlMode.PercentOutput, 0);
  }

}
