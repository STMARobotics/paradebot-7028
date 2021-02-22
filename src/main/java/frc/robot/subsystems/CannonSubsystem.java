package frc.robot.subsystems;

import static frc.robot.Constants.CannonConstants.DEVICE_ID_CANNON_VALVE;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_REGULATOR;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_SENSOR;
import static frc.robot.Constants.CannonConstants.PRESSURE_REGULATOR_KP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CannonSubsystem extends SubsystemBase {

  private final Spark valve = new Spark(DEVICE_ID_CANNON_VALVE);
  private final WPI_TalonSRX pressureRegulator = new WPI_TalonSRX(DEVICE_ID_PRESSURE_REGULATOR);
  private final AnalogInput pressureSensor = new AnalogInput(DEVICE_ID_PRESSURE_SENSOR);

  public CannonSubsystem() {
    TalonSRXConfiguration talonConfig = new TalonSRXConfiguration();

    talonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    talonConfig.slot0.kP = PRESSURE_REGULATOR_KP;
  }

  public void openValve() {
    valve.set(1);
  }

  public void closeValve() {
    valve.set(0);
  }

  public void setPressureRegulatorPosition(int target) {
    pressureRegulator.set(ControlMode.Position, target);
  }

  public double getPressure() {
    return (250 * (pressureSensor.getVoltage() / 5) - 25);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pressure", 250 * (pressureSensor.getVoltage() / 5) - 25);
  }

}
