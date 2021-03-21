// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int DEVICE_ID_JOYSTICK = 1;
  }

  public static final class CompressorConstants {
    public static final int DEVICE_ID_COMPRESSOR = 3;
  }

  public static final class CannonConstants {
    public static final int DEVICE_ID_CANNON_VALVE = 0;
    public static final int DEVICE_ID_PRESSURE_REGULATOR = 4;
    public static final int DEVICE_ID_PRESSURE_SENSOR = 0;
    public static final int DEVICE_ID_BLAST_FORWARD = 0;
    public static final int DEVICE_ID_BLAST_REVERSE = 1;
    public static final double VALVE_OPEN_TIME = 0.2;

    // regulator max and min aren't tuned
    public static final double PRESSURE_REGULATOR_KP = 0.001;
  }

  public static final class DriveTrainConstants {
    public static final int DEVICE_ID_LEFT_LEADER = 0;
    public static final int DEVICE_ID_LEFT_FOLLOWER = 1;
    public static final int DEVICE_ID_RIGHT_LEADER = 2;
    public static final int DEVICE_ID_RIGHT_FOLLOWER = 3;

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

    public static final double ACT_BOUND_MAX = 2.0;
    public static final double ACT_BOUND_DEAD_MAX = 1.8;
    public static final double ACT_BOUND_CENTER = 1.525;
    public static final double ACT_BOUND_DEAD_MIN = 1.25;
    public static final double ACT_BOUND_MIN = 1.05;
  }

  public static final class NerdShooterConstants {
    public static final int DEVICE_ID_LEFT_FLYWHEEL = 6;
    public static final int DEVICE_ID_LEFT_PUSHER_FORWARD = 2;
    public static final int DEVICE_ID_LEFT_PUSHER_REVERSE = 3;

    public static final int DEVICE_ID_RIGHT_FLYWHEEL = 7;
    public static final int DEVICE_ID_RIGHT_PUSHER_FORWARD = 4;
    public static final int DEVICE_ID_RIGHT_PUSHER_REVERSE = 5;

    /**
     * time it takes pusher to go out, then in
     */
    public static final double PUSHER_CYCLE_TIME = 0.2;
  }

  public static final class AudioConstants {
    public static final String TABLE_NAME = "audio";
  }
}
