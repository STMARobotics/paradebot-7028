package frc.robot;

import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_FLYWHEEL;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_FLYWHEEL;

public enum NerdFlywheel {

  LEFT(DEVICE_ID_LEFT_FLYWHEEL),
  RIGHT(DEVICE_ID_RIGHT_FLYWHEEL);

  private final int flywheelId;

  private NerdFlywheel(int flywheelId) {
    this.flywheelId = flywheelId;
  }

  public int getflywheelId() {
    return this.flywheelId;
  }

}
