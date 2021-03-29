// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class ControllerConstants {
    public static final int DEVICE_ID_DRIVER_CONTROLLER = 0;
    public static final int DEVICE_ID_CANNON_CONTROLLER = 1;
    public static final int DEVICE_ID_NERD_CONTROLLER = 2;

    public static final int LEFT_NERD_SHOOTER_REV_INDEX = 0;
    public static final int LEFT_NERD_SHOOTER_FIRE_INDEX = 1;
    public static final int RIGHT_NERD_SHOOTER_REV_INDEX = 2;
    public static final int RIGHT_NERD_SHOOTER_FIRE_INDEX = 3;
  }

  public static final class CompressorConstants {
    public static final int DEVICE_ID_COMPRESSOR = 3;
    public static final int DEVICE_ID_SYSTEM_PRESSURE_SENSOR = 0;
  }

  public static final class CannonConstants {
    public static final int DEVICE_ID_CANNON_VALVE = 0;
    public static final int DEVICE_ID_PRESSURE_REGULATOR = 4;
    public static final int DEVICE_ID_PRESSURE_SENSOR = 1;
    public static final int DEVICE_ID_BLAST_FORWARD = 0;
    public static final int DEVICE_ID_BLAST_REVERSE = 1;
    public static final double VALVE_OPEN_TIME = 0.2;
    
    public static final double PRESSURE_REGULATOR_SPEED = 1.0;
  }

  public static final class DriveTrainConstants {
    public static final int DEVICE_ID_LEFT_LEADER = 0;
    public static final int DEVICE_ID_LEFT_FOLLOWER = 1;
    public static final int DEVICE_ID_RIGHT_LEADER = 2;
    public static final int DEVICE_ID_RIGHT_FOLLOWER = 3;

    public static final double EDGES_PER_ROTATION = 2048d;
    
    /** Gear ratio. A.K.A. motor rotations per wheel rotation. */
    public static final double GEARING = 7.75d;

    public static final double WHEEL_DIAMETER_INCHES = 8d;
    public static final double WHEEL_CIRCUMFERENCE = Units.inchesToMeters(WHEEL_DIAMETER_INCHES) * Math.PI;

    public static final double TRACK_WIDTH_METERS = 0.9130394010278512;
    public static final DifferentialDriveKinematics DRIVE_KINEMATICS = 
        new DifferentialDriveKinematics(TRACK_WIDTH_METERS);

    /** Voltage needed to overcome the motor’s static friction. kS */
    public static final double kS = 0.762;

    /** Voltage needed to hold (or "cruise") at a given constant velocity. kV */
    public static final double kV = 1.3;

    /** Voltage needed to induce a given acceleration in the motor shaft. kA */
    public static final double kA = 0.327;

    public static final SimpleMotorFeedforward FEED_FORWARD = new SimpleMotorFeedforward(kS, kV, kA);

    public static final double kP = 0.1;
    public static final double kI = 0d;
    public static final double kD = 0d;

    public static final double OPEN_LOOP_RAMP = .25;

  }

  public static final class TurretConstants {
    public static final int DEVICE_ID_CANNON_ACTUATOR_ONE = 1;
    public static final int DEVICE_ID_CANNON_ACTUATOR_TWO = 2;
    public static final int DEVICE_ID_TURRET = 5;
    public static final int DEVICE_ID_PIGEON = 7;

    public static final double CLOSED_LOOP_MAX_OUTPUT = 0.3;
    public static final double kP = 1d;
    public static final double kD = 0d;

    /** Max teleop output percentage */
    public static final double TELEOP_MAX_OUTPUT = 1d / 6d;

    /** Max change in native units per iteration (20ms) */
    public static final double TELEOP_MAX_CHANGE = 20d;

    public static final double TURRET_POWER = 0.4;

    public static final double ACT_BOUND_MAX = 2.0;
    public static final double ACT_BOUND_DEAD_MAX = 1.8;
    public static final double ACT_BOUND_CENTER = 1.525;
    public static final double ACT_BOUND_DEAD_MIN = 1.25;
    public static final double ACT_BOUND_MIN = 1.05;

    public static final double ACT_INCREMENT = 0.5;
  }

  public static final class NerdShooterConstants {
    public static final int DEVICE_ID_LEFT_FLYWHEEL = 6;
    public static final int DEVICE_ID_LEFT_PUSHER_FORWARD = 2;
    public static final int DEVICE_ID_LEFT_PUSHER_REVERSE = 3;

    public static final int DEVICE_ID_RIGHT_FLYWHEEL = 7;
    public static final int DEVICE_ID_RIGHT_PUSHER_FORWARD = 4;
    public static final int DEVICE_ID_RIGHT_PUSHER_REVERSE = 5;

    public static final double FLYWHEEL_POWER = 0.4;

    /**
     * time it takes pusher to go out, then in
     */
    // public static final double PUSHER_CYCLE_TIME = 0.3;
    public static final double PUSHER_OUT_TIME = 0.05;
    public static final double PUSHER_FULL_CYCLE_TIME = 0.13;
  }

  public static final class AudioConstants {
    public static final String TABLE_NAME = "audio";
  }

  public static final class DriverConstants {
    public static final double DEADBAND_HIGH = 0.12;
    public static final double DEADBAND_LOW = -DEADBAND_HIGH;
  }
  
  public static final class ArcadeConstants {
    // Max speed to drive in teleop in meters per second
    public static final double MAX_SPEED_ARCADE = 3;

    // Max angular velocity in teleop in radians per second
    public static final double MAX_ANGULAR_VEL_ARCADE = Units.degreesToRadians(120);

    // Max rate of change for speed per second
    public static final double SPEED_RATE_LIMIT_ARCADE = 1.75;

    // Max rate of change for rotation per second
    public static final double ROTATE_RATE_LIMIT_ARCADE = Units.degreesToRadians(230);
  }
}
