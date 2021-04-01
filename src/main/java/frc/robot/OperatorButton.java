package frc.robot;

public enum OperatorButton {

  // Joystick 2
  LEFT_REV(9),
  LEFT_FIRE(5),
  LEFT_RESET_COUNT(10),
  RIGHT_REV(3),
  RIGHT_FIRE(2),
  RIGHT_RESET_COUNT(4),
  // Joystick 1
  TURRET_UP(4),
  TURRET_DOWN(3),
  TURRET_RIGHT(2),
  TURRET_LEFT(1),
  TURRET_GYRO_LOCK(8),
  CANNON_FIRE(9), // Switch
  FILL_SOLENOID(10),
  REGULATOR_INCREASE(6),
  REGULATOR_DECREASE(5),
  COMPRESSOR(7); // Switch

  private final int buttonIndex;

  private OperatorButton(int buttonIndex) {
    this.buttonIndex = buttonIndex;
  }

  public int getButtonIndex() {
    return this.buttonIndex;
  }

}
