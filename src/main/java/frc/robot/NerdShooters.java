package frc.robot;

import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_FLYWHEEL;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_PUSHER_FORWARD;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_PUSHER_REVERSE;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_FLYWHEEL;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_PUSHER_FORWARD;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_PUSHER_REVERSE;

public enum NerdShooters {

  LEFT(DEVICE_ID_LEFT_FLYWHEEL, DEVICE_ID_LEFT_PUSHER_FORWARD, DEVICE_ID_LEFT_PUSHER_REVERSE),
  RIGHT(DEVICE_ID_RIGHT_FLYWHEEL, DEVICE_ID_RIGHT_PUSHER_FORWARD, DEVICE_ID_RIGHT_PUSHER_REVERSE);

  private final int flywheelId;
  private final int pusherForwardId;
  private final int pusherReverseId;

  private NerdShooters(int flywheelId, int pusherForwardId, int pusherReverseId) {
    this.flywheelId = flywheelId;
    this.pusherForwardId = pusherForwardId;
    this.pusherReverseId = pusherReverseId;
  }

  public int getflywheelId() {
    return flywheelId;
  }

  public int getPusherForwardId() {
    return pusherForwardId;
  }

  public int getPusherReverseId() {
    return pusherReverseId;
  }

}
