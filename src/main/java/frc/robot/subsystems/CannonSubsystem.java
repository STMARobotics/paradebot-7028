package frc.robot.subsystems;

import static frc.robot.Constants.CannonConstants.DEVICE_ID_BLAST_FORWARD;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_BLAST_REVERSE;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_CANNON_VALVE;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_REGULATOR;
import static frc.robot.Constants.CannonConstants.DEVICE_ID_PRESSURE_SENSOR;
import static frc.robot.Constants.CannonConstants.PRESSURE_REGULATOR_SPEED;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PressureSensor;

public class CannonSubsystem extends SubsystemBase {

  private final Spark valve = new Spark(DEVICE_ID_CANNON_VALVE);
  private final WPI_TalonSRX pressureRegulator = new WPI_TalonSRX(DEVICE_ID_PRESSURE_REGULATOR);
  private final PressureSensor pressureSensor = new PressureSensor(DEVICE_ID_PRESSURE_SENSOR);
  private final DoubleSolenoid blastSolenoid = new DoubleSolenoid(DEVICE_ID_BLAST_FORWARD, DEVICE_ID_BLAST_REVERSE);

  public CannonSubsystem() {
    TalonSRXConfiguration talonConfig = new TalonSRXConfiguration();
    talonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;

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

  public void increaseRegulator() {
    pressureRegulator.set(PRESSURE_REGULATOR_SPEED);
  }

  public void decreaseRegulator() {
    pressureRegulator.set(-PRESSURE_REGULATOR_SPEED);
  }

  public double getPressure() {
    return pressureSensor.getPressure();
  }

  @Override
  public void periodic() {
  }

}
