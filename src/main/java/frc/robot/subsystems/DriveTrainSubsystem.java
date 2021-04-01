package frc.robot.subsystems;

import static frc.robot.Constants.ArcadeConstants.MAX_ANGULAR_VEL_ARCADE;
import static frc.robot.Constants.ArcadeConstants.MAX_SPEED_ARCADE;
import static frc.robot.Constants.ArcadeConstants.ROTATE_RATE_LIMIT_ARCADE;
import static frc.robot.Constants.ArcadeConstants.SPEED_RATE_LIMIT_ARCADE;
import static frc.robot.Constants.DriveTrainConstants.DEVICE_ID_LEFT_FOLLOWER;
import static frc.robot.Constants.DriveTrainConstants.DEVICE_ID_LEFT_LEADER;
import static frc.robot.Constants.DriveTrainConstants.DEVICE_ID_RIGHT_FOLLOWER;
import static frc.robot.Constants.DriveTrainConstants.DEVICE_ID_RIGHT_LEADER;
import static frc.robot.Constants.DriveTrainConstants.DRIVE_KINEMATICS;
import static frc.robot.Constants.DriveTrainConstants.EDGES_PER_ROTATION;
import static frc.robot.Constants.DriveTrainConstants.FEED_FORWARD;
import static frc.robot.Constants.DriveTrainConstants.GEARING;
import static frc.robot.Constants.DriveTrainConstants.OPEN_LOOP_RAMP;
import static frc.robot.Constants.DriveTrainConstants.WHEEL_CIRCUMFERENCE;
import static frc.robot.Constants.DriveTrainConstants.kD;
import static frc.robot.Constants.DriveTrainConstants.kI;
import static frc.robot.Constants.DriveTrainConstants.kP;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpiutil.math.MathUtil;

public class DriveTrainSubsystem extends SubsystemBase {
  
  /** Constant for converting from encoder edges to meters */
  private static final double ENCODER_EDGES_TO_METERS = (1 / GEARING) * (1 / EDGES_PER_ROTATION) * WHEEL_CIRCUMFERENCE;

  /** Constant for converting from encoder velocity (edges per decisecond) to ground velocity (meters per second)) */
  private static final double ENCODER_VEL_TO_GROUND = ENCODER_EDGES_TO_METERS * 10d;

  private final WPI_TalonFX leftLeader = new WPI_TalonFX(DEVICE_ID_LEFT_LEADER);
  private final WPI_TalonFX leftFollower = new WPI_TalonFX(DEVICE_ID_LEFT_FOLLOWER);
  private final WPI_TalonFX rightLeader = new WPI_TalonFX(DEVICE_ID_RIGHT_LEADER);
  private final WPI_TalonFX rightFollower = new WPI_TalonFX(DEVICE_ID_RIGHT_FOLLOWER);

  private SlewRateLimiter speedRateLimiter = new SlewRateLimiter(SPEED_RATE_LIMIT_ARCADE);
  private SlewRateLimiter rotationRateLimiter = new SlewRateLimiter(ROTATE_RATE_LIMIT_ARCADE);

  public DriveTrainSubsystem() {
    TalonFXConfiguration talonConfig = new TalonFXConfiguration();
    talonConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    talonConfig.slot0.kP = kP;
    talonConfig.slot0.kI = kI;
    talonConfig.slot0.kD = kD;
    talonConfig.openloopRamp = OPEN_LOOP_RAMP;

    leftLeader.configFactoryDefault();
    leftLeader.configAllSettings(talonConfig);
    leftFollower.configFactoryDefault();
    rightLeader.configFactoryDefault();
    rightLeader.configAllSettings(talonConfig);
    rightFollower.configFactoryDefault();

    leftLeader.setInverted(true);
    leftFollower.setInverted(true);
    
    leftLeader.setNeutralMode(NeutralMode.Coast);
    leftFollower.setNeutralMode(NeutralMode.Coast);
    rightLeader.setNeutralMode(NeutralMode.Coast);
    rightFollower.setNeutralMode(NeutralMode.Coast);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    new Trigger(RobotState::isEnabled).whenActive(new StartEndCommand(() -> {
      leftLeader.setNeutralMode(NeutralMode.Brake);
      leftFollower.setNeutralMode(NeutralMode.Brake);
      rightLeader.setNeutralMode(NeutralMode.Brake);
      rightFollower.setNeutralMode(NeutralMode.Brake);
    }, () -> {
      leftLeader.setNeutralMode(NeutralMode.Coast);
      leftFollower.setNeutralMode(NeutralMode.Coast);
      rightLeader.setNeutralMode(NeutralMode.Coast);
      rightFollower.setNeutralMode(NeutralMode.Coast);
    }, this));
  }

  public void arcadeDrive(double speed, double rotation) {
    arcadeDrive(speed, rotation, true);
  }

  public void arcadeDrive(double speed, double rotation, boolean squareInputs) {
    var xSpeed = speed;
    var zRotation = rotation;
    if (squareInputs) {
      xSpeed *= Math.abs(xSpeed);
      zRotation *= Math.abs(zRotation);
    }
    xSpeed = speedRateLimiter.calculate(safeClamp(speed));
    zRotation = -rotationRateLimiter.calculate(safeClamp(rotation));
    xSpeed *= MAX_SPEED_ARCADE;
    zRotation *= MAX_ANGULAR_VEL_ARCADE;
    var wheelSpeeds = DRIVE_KINEMATICS.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, zRotation));
    tankDrive(wheelSpeeds.leftMetersPerSecond, wheelSpeeds.rightMetersPerSecond);
  }

  /**
   * Controls the left and right side of the drive using Talon SRX closed-loop
   * velocity.
   * 
   * @param leftVelocity  left velocity in meters per second
   * @param rightVelocity right velocity in meters per second
   */
  public void tankDrive(double leftVelocity, double rightVelocity) {
    var leftFeedForwardVolts = FEED_FORWARD.calculate(leftVelocity);
    var rightFeedForwardVolts = FEED_FORWARD.calculate(rightVelocity);

    leftLeader.set(
        ControlMode.Velocity, 
        metersPerSecToEdgesPerDecisec(leftVelocity), 
        DemandType.ArbitraryFeedForward,
        leftFeedForwardVolts / 12);
    rightLeader.set(
        ControlMode.Velocity,
        metersPerSecToEdgesPerDecisec(rightVelocity),
        DemandType.ArbitraryFeedForward,
        rightFeedForwardVolts / 12);
  }
  
  /**
   * Returns value clamped between [-1, 1]. Not-a-number (NaN) returns 0;
   * @param input value to clamp
   * @return clamped value
   */
  private double safeClamp(double input) {
    if (Double.isNaN(input)) {
      return 0;
    }
    return MathUtil.clamp(input, -1, 1);
  }
  
  /**
   * Converts from encoder edges to meters.
   * 
   * @param steps encoder edges to convert
   * @return meters
   */
  public static double edgesToMeters(double steps) {
    return ENCODER_EDGES_TO_METERS * steps;
  }

  /**
   * Converts from encoder edges per 100 milliseconds to meters per second.
   * @param stepsPerDecisec edges per decisecond
   * @return meters per second
   */
  public static double edgesPerDecisecToMetersPerSec(double stepsPerDecisec) {
    return stepsPerDecisec * ENCODER_VEL_TO_GROUND;
  }

  /**
   * Converts from meters to encoder edges.
   * @param meters meters
   * @return encoder edges
   */
  public static double metersToEdges(double meters) {
    return meters / ENCODER_EDGES_TO_METERS;
  }

  /**
   * Converts from meters per second to encoder edges per 100 milliseconds.
   * @param metersPerSec meters per second
   * @return encoder edges per decisecond
   */
  public static double metersPerSecToEdgesPerDecisec(double metersPerSec) {
    return metersPerSec / ENCODER_VEL_TO_GROUND;
  }

}
