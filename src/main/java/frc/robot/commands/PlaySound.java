// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.networktables.NetworkTable;

//import edu.wpi.first.wpilibj.XboxController;

public class PlaySound extends CommandBase {
  
  /** Creates a new PlaySound. */
  public PlaySound() {
    
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  private final XboxController controller = new XboxController(1);
  NetworkTable table = NetworkTableInstance.getDefault().getTable("datatable");
  NetworkTableEntry entry = table.getEntry("clip");
  NetworkTableEntry entry2 = table.getEntry("clip2");
  //private final XboxController controller = new XboxController(1);


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*
    double state = entry.getDouble(0);
    if (state == 0 || state == 3){
      entry.setDouble(1);
    } else if (state == 1){
      entry.setDouble(0);
    }
    */

    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(controller.getY(Hand.kLeft)) > 0.3){
      entry2.setBoolean(false);
      entry.setBoolean(true);
    }
    else {

      
      entry.setBoolean(false);
      entry2.setBoolean(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    entry2.setBoolean(false);
    entry.setBoolean(false);
    /*
    double state = entry.getDouble(0);

    if (state == 1) {
      if (controller.getYButton() == true) {
        entry.setDouble(2);
      } else if (controller.getBButton() == true) {
        entry.setDouble(3);
      }else {
        entry.setDouble(0);
      }
    }
    */
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
