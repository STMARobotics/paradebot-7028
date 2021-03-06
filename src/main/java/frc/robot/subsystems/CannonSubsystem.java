package frc.robot.subsystems;

import static frc.robot.Constants.CannonConstants.DEVICE_ID_BLAST_FORWARD;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_BLAST_REVERSE;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_CANNON_VALVE;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_REGULATOR;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_SENSOR;
import static frc.robot.Constants.CannonConstants.PRESSURE_REGULATOR_KP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CannonSubsystem extends SubsystemBase {

  private final Spark valve = new Spark(DEVICE_ID_CANNON_VALVE);
  private final WPI_TalonSRX pressureRegulator = new WPI_TalonSRX(DEVICE_ID_PRESSURE_REGULATOR);
  private final AnalogInput pressureSensor = new AnalogInput(DEVICE_ID_PRESSURE_SENSOR);
  private final DoubleSolenoid blastSolenoid = new DoubleSolenoid(DEVICE_ID_BLAST_FORWARD, DEVICE_ID_BLAST_REVERSE);

  public CannonSubsystem() {
    TalonSRXConfiguration talonConfig = new TalonSRXConfiguration();
    talonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    talonConfig.slot0.kP = PRESSURE_REGULATOR_KP;

    pressureRegulator.configFactoryDefault();
    pressureRegulator.configAllSettings(talonConfig);
  }
  public void openValve() {
    valve.set(1);
  }

  public void closeValve() {
    valve.set(0);
  }

  public void openBlastTank() {
    blastSolenoid.set(Value.kForward);
  }

  public void closeBlastTank() {
    blastSolenoid.set(Value.kReverse);
  }

  public void setPressureRegulatorPosition(double target) {
    pressureRegulator.set(ControlMode.Position, target);
  }

  public void setPressureRegulatorPower(double power) {
    pressureRegulator.set(ControlMode.PercentOutput, power);
  }

  public double getPressure() {
    return (250 * (pressureSensor.getVoltage() / 5) - 25);
  }

  @Override
  public void periodic() {
  }

}
