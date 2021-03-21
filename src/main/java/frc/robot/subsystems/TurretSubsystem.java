package frc.robot.subsystems;

import static frc.robot.Constants.TurretConstants.ACT_BOUND_CENTER;
import static frc.robot.Constants.TurretConstants.ACT_BOUND_DEAD_MAX;
import static frc.robot.Constants.TurretConstants.ACT_BOUND_DEAD_MIN;
import static frc.robot.Constants.TurretConstants.ACT_BOUND_MAX;
import static frc.robot.Constants.TurretConstants.ACT_BOUND_MIN;
import static frc.robot.Constants.TurretConstants.CLOSED_LOOP_MAX_OUTPUT;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_CANNON_ACTUATOR_ONE;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_CANNON_ACTUATOR_TWO;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_PIGEON;
import static frc.robot.Constants.TurretConstants.DEVICE_ID_TURRET;
import static frc.robot.Constants.TurretConstants.kD;
import static frc.robot.Constants.TurretConstants.kP;

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
    TalonSRXConfiguration talonConfig = new TalonSRXConfiguration();
    talonConfig.slot0.kP = kP;
    talonConfig.slot0.kD = kD;
    talonConfig.remoteFilter0.remoteSensorDeviceID = DEVICE_ID_PIGEON;
    talonConfig.remoteFilter0.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Yaw;
    talonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.RemoteSensor0;
    talonConfig.reverseLimitSwitchNormal = LimitSwitchNormal.NormallyClosed;
    talonConfig.forwardLimitSwitchNormal = LimitSwitchNormal.NormallyClosed;

    turret.configFactoryDefault();
    turret.configAllSettings(talonConfig);
    turret.configClosedLoopPeakOutput(0, CLOSED_LOOP_MAX_OUTPUT);

    actuatorOne.setBounds(ACT_BOUND_MAX, ACT_BOUND_DEAD_MAX, ACT_BOUND_CENTER, ACT_BOUND_DEAD_MIN, ACT_BOUND_MIN);
    actuatorTwo.setBounds(ACT_BOUND_MAX, ACT_BOUND_DEAD_MAX, ACT_BOUND_CENTER, ACT_BOUND_DEAD_MIN, ACT_BOUND_MIN);
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

  public boolean isAtForwardLimit() {
    return turret.isFwdLimitSwitchClosed() == 0;
  }

  public boolean isAtReverseLimit() {
    return turret.isRevLimitSwitchClosed() == 0;
  }

}
