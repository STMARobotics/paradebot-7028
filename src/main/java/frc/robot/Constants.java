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
  }

  public static final class AudioConstants {
    public static final String TABLE_NAME = "audio";
  }
}
