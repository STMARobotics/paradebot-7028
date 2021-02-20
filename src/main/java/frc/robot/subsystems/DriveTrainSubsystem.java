package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrainSubsystem extends SubsystemBase {

    private final WPI_TalonFX leftMaster = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_LEFT_MASTER);
    private final WPI_TalonFX leftSlave = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_LEFT_SLAVE);
    private final WPI_TalonFX rightMaster = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_RIGHT_MASTER);
    private final WPI_TalonFX rightSlave = new WPI_TalonFX(DriveTrainConstants.DEVICE_ID_RIGHT_SLAVE);

    private DifferentialDrive differentialDrive = new DifferentialDrive(leftMaster, rightMaster);

    public DriveTrainSubsystem() {

        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);
    }

    public void drive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
    }
    
}
