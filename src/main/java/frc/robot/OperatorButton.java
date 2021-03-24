package frc.robot;

public enum OperatorButton {

  // TODO fill in these buttons
  LEFT_REV(0),
  LEFT_FIRE(1),
  LEFT_RESET_COUNT(2),
  RIGHT_REV(3),
  RIGHT_FIRE(4),
  RIGHT_RESET_COUNT(5),
  TURRET_UP(6),
  TURRET_DOWN(7),
  TURRET_RIGHT(8),
  TURRET_LEFT(9),
  TURRET_GYRO_LOCK(10),
  CANNON_FIRE(11),
  FILL_SOLENOID(12),
  FILL_SAFETY(13),
  REGULATOR_INCREASE(14),
  REGULATOR_DECREASE(15);

  private final int buttonIndex;

  private OperatorButton(int buttonIndex) {
    this.buttonIndex = buttonIndex;
  }

  public int getButtonIndex() {
    return this.buttonIndex;
  }

}
