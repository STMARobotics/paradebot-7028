// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.CannonConstants.VALVE_OPEN_TIME;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_DRIVER_CONTROLLER;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_JOYSTICK;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ManageRegulatorCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TeleOpDriveCommand;
import frc.robot.commands.TeleopRegulatorCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final XboxController driverController = new XboxController(DEVICE_ID_DRIVER_CONTROLLER);
  private final Joystick joystick = new Joystick(DEVICE_ID_JOYSTICK);
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CannonSubsystem cannonSubsystem = new CannonSubsystem();
  private final TeleOpDriveCommand teleOpDriveCommand = new TeleOpDriveCommand(driveTrainSubsystem, driverController);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    var regulatorPositionModeEntry = Shuffleboard.getTab("SmartDashboard")
        .addPersistent("Regulator Position Mode", true).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    scheduleRegulatorModeCommand(regulatorPositionModeEntry);
    regulatorPositionModeEntry.addListener(this::handleRegulatorPositionModeEntry,
        EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    driveTrainSubsystem.setDefaultCommand(teleOpDriveCommand);
  }

  private void handleRegulatorPositionModeEntry(EntryNotification notification) {
    scheduleRegulatorModeCommand(notification.getEntry());
  }

  private void scheduleRegulatorModeCommand(NetworkTableEntry entry) {
    if (entry.getBoolean(true)) {
      new ManageRegulatorCommand(cannonSubsystem, joystick).schedule();
    } else {
      new TeleopRegulatorCommand(cannonSubsystem, driverController).schedule();
    }
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(driverController, XboxController.Button.kA.value)
        .whenPressed(new ShootCommand(cannonSubsystem).withTimeout(VALVE_OPEN_TIME));
  }

}
