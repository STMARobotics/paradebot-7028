package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.DEVICE_ID_CANNON_ACTUATOR;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_PIGEON;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_TURRET;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

  private final WPI_TalonSRX turret = new WPI_TalonSRX(DEVICE_ID_TURRET);
  private final PigeonIMU pigeon = new PigeonIMU(DEVICE_ID_PIGEON);
  private final Servo actuator = new Servo(DEVICE_ID_CANNON_ACTUATOR);

  public TurretSubsystem() {
    turret.configFactoryDefault();
    turret.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    turret.setSensorPhase(true);

    turret.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, 1, 0);
    turret.configRemoteFeedbackFilter(pigeon.getDeviceID(), RemoteSensorSource.GadgeteerPigeon_Yaw, 0);

    actuator.setBounds(2.0, 1.8, 1.525, 1.25, 1.05);
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

  public void stopRotation() {
    turret.set(ControlMode.PercentOutput, 0);
  }

  public void raiseCannonToMax() {
    actuator.setSpeed(1.0);
  }

  public void lowerCannonToMin() {
    actuator.setSpeed(-1.0);
  }

}
