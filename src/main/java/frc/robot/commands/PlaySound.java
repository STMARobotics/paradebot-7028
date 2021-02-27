// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class PlaySound extends CommandBase {
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("datatable");
  private NetworkTableEntry entry;
  private final String clip;
  public PlaySound(String clip) {
    entry = table.getEntry(clip);
    this.clip = clip;
  }

  @Override
  public void initialize() {
    entry.setBoolean(true);
    System.out.println("Sound Activated " + clip);
  }

  @Override
  public void execute() {
    
  }
  public String getClip(){
    return clip;
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
