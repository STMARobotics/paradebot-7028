package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.DEVICE_ID_CANNON_ACTUATOR_ONE;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_CANNON_ACTUATOR_TWO;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_PIGEON;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_TURRET;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class TurretSubsystem extends SubsystemBase {

  private final WPI_TalonSRX turret = new WPI_TalonSRX(DEVICE_ID_TURRET);
  private final Servo actuatorOne = new Servo(DEVICE_ID_CANNON_ACTUATOR_ONE);
  private final Servo actuatorTwo = new Servo(DEVICE_ID_CANNON_ACTUATOR_TWO);

  public TurretSubsystem() {
    TalonSRXConfiguration turretTalonConfig = new TalonSRXConfiguration();
    // Set encoder as primary so soft limits can be used
    // turretTalonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    // Primary PID all zeros to cancel out PID with encoder
    turretTalonConfig.slot0.kP = 1d;
    turretTalonConfig.slot0.kI = 0d;
    turretTalonConfig.slot0.kD = 0d;
    // turretTalonConfig.closedloopRamp = TurretConstants.CLOSED_LOOP_RAMP;

    // Aux PID for Pigeon
    turretTalonConfig.remoteFilter0.remoteSensorDeviceID = DEVICE_ID_PIGEON;
    turretTalonConfig.remoteFilter0.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Yaw;
    turretTalonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
    // turretTalonConfig.slot1.kP = .1;
    // turretTalonConfig.slot1.kI = 0d;
    // turretTalonConfig.slot1.kD = 0d;
    
    turretTalonConfig.reverseLimitSwitchNormal = LimitSwitchNormal.NormallyClosed;
    turretTalonConfig.forwardLimitSwitchNormal = LimitSwitchNormal.NormallyClosed;

    turret.configFactoryDefault();
    turret.configAllSettings(turretTalonConfig);
    // turret.setSensorPhase(true);
    turret.configClosedLoopPeakOutput(0, .3);

    actuatorOne.setBounds(2.0, 1.8, 1.525, 1.25, 1.05);
    actuatorTwo.setBounds(2.0, 1.8, 1.525, 1.25, 1.05);
  }

  /**
   * Gets the gyro position in sensor native units (8192 units per rotation)
   * @return position
   */
  public double getGyroPosition() {
    return turret.getSelectedSensorPosition(0);
  }

  /**
   * Sets the position based in the gyro native units
   * @param position position to set in gyro native units
   */
  public void setPositionWithGyro(double position) {
    // Only aux PID is used since that's where the Pigeon is selected
    turret.set(ControlMode.Position, position);
  }

  public void rotate(double speed) {
    turret.set(speed);
  }

  public void stopRotation() {
    turret.set(ControlMode.PercentOutput, 0);
  }

  public void raiseCannonToMax() {
    actuatorOne.setSpeed(1.0);
    actuatorTwo.setSpeed(1.0);
  }

  public void lowerCannonToMin() {
    actuatorOne.setSpeed(-1.0);
    actuatorTwo.setSpeed(-1.0);
  }

}
