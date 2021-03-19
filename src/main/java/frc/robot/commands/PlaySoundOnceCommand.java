package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AudioConstants;

public class PlaySoundOnceCommand extends CommandBase {
  private final NetworkTable table = NetworkTableInstance.getDefault().getTable(AudioConstants.TABLE_NAME);
  private final NetworkTableEntry entry;

  public PlaySoundOnceCommand(String clip) {
    entry = table.getEntry(clip);
  }

  @Override
  public void initialize() {
    entry.setBoolean(true);
  }

  @Override
  public void end(boolean interrupted) {
    entry.setBoolean(false);
  }

  @Override
  public boolean isFinished() {
    return !entry.getBoolean(true);
  }
}
