package frc.robot;

import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_FLYWHEEL;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_PUSHER;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_FLYWHEEL;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_PUSHER;

public enum NerdShooters {

  LEFT(DEVICE_ID_LEFT_FLYWHEEL, DEVICE_ID_LEFT_PUSHER),
  RIGHT(DEVICE_ID_RIGHT_FLYWHEEL, DEVICE_ID_RIGHT_PUSHER);

  private final int flywheelId;
  private final int pusherId;

  private NerdShooters(int flywheelId, int pusherId) {
    this.flywheelId = flywheelId;
    this.pusherId = pusherId;
  }

  public int getflywheelId() {
    return flywheelId;
  }

  public int getPuhserId() {
    return pusherId;
  }

}
