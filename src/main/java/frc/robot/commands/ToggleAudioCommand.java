// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AudioConstants;

public class ToggleAudioCommand extends CommandBase {
  private final NetworkTable table = NetworkTableInstance.getDefault().getTable(AudioConstants.TABLE_NAME);
  private final NetworkTableEntry entry = table.getEntry("audio");

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
    return false;
  }
}
