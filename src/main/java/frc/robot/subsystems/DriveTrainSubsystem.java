package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  private final WPI_TalonFX leftLeader = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_LEFT_LEADER);
  private final WPI_TalonFX leftFollower = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_LEFT_FOLLOWER);
  private final WPI_TalonFX rightLeader = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_RIGHT_LEADER);
  private final WPI_TalonFX rightFollower = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_RIGHT_FOLLOWER);

  private DifferentialDrive differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

  public DriveTrainSubsystem() {
    leftLeader.setInverted(true);
    rightLeader.setInverted(true);
    leftFollower.setInverted(true);
    rightFollower.setInverted(true);
    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    leftLeader.configFactoryDefault();
    leftFollower.configFactoryDefault();
    rightLeader.configFactoryDefault();
    rightFollower.configFactoryDefault();
    leftLeader.setNeutralMode(NeutralMode.Brake);
    leftFollower.setNeutralMode(NeutralMode.Brake);
    rightLeader.setNeutralMode(NeutralMode.Brake);
    rightFollower.setNeutralMode(NeutralMode.Brake);
  }

  public void drive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation, true);
  }

}
