package frc.robot;

import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_PUSHER_FORWARD;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_LEFT_PUSHER_REVERSE;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_PUSHER_FORWARD;
import static frc.robot.Constants.NerdShooterConstants.DEVICE_ID_RIGHT_PUSHER_REVERSE;

public enum NerdPusher {

  LEFT(DEVICE_ID_LEFT_PUSHER_FORWARD, DEVICE_ID_LEFT_PUSHER_REVERSE),
  RIGHT(DEVICE_ID_RIGHT_PUSHER_FORWARD, DEVICE_ID_RIGHT_PUSHER_REVERSE);

  private final int pusherForwardId;
  private final int pusherReverseId;

  private NerdPusher(int pusherForwardId, int pusherReverseId) {
    this.pusherForwardId = pusherForwardId;
    this.pusherReverseId = pusherReverseId;
  }

  public int getForwardId() {
    return pusherForwardId;
  }

  public int getReverseId() {
    return pusherReverseId;
  }

}
