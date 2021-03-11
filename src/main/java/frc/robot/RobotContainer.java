// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.CannonConstants.VALVE_OPEN_TIME;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_DRIVER_CONTROLLER;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_JOYSTICK;

import java.util.Map;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TeleOpDriveCommand;
import frc.robot.commands.ToggleAudio;
import frc.robot.commands.TeleOpTurretCommand;
import frc.robot.commands.TeleopRegulatorCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.TurretSubsystem;

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
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final TeleOpDriveCommand teleOpDriveCommand = new TeleOpDriveCommand(driveTrainSubsystem, driverController);
  private final TeleOpTurretCommand teleOpTurretCommand = new TeleOpTurretCommand(turretSubsystem, driverController);
  private final TeleopRegulatorCommand teleopRegulatorCommand = new TeleopRegulatorCommand(cannonSubsystem, joystick);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    Shuffleboard.getTab("Console").addNumber("Pressure", cannonSubsystem::getPressure)
        .withWidget(BuiltInWidgets.kDial).withProperties(Map.of("min", 0, "max", 100))
        .withSize(3, 2).withPosition(0, 0);

    driveTrainSubsystem.setDefaultCommand(teleOpDriveCommand);
    turretSubsystem.setDefaultCommand(teleOpTurretCommand);
    cannonSubsystem.setDefaultCommand(teleopRegulatorCommand);
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
    
    new JoystickButton(driverController, XboxController.Button.kX.value).toggleWhenPressed(new ToggleAudio(), true);
    
    new JoystickButton(driverController, XboxController.Button.kBumperRight.value)
        .whenPressed(() -> turretSubsystem.raiseCannonToMax(), turretSubsystem);
    new JoystickButton(driverController, XboxController.Button.kBumperLeft.value)
        .whenPressed(() -> turretSubsystem.lowerCannonToMin(), turretSubsystem);

    new JoystickButton(driverController, XboxController.Button.kX.value)
        .whenPressed(cannonSubsystem::openBlastTank);
    new JoystickButton(driverController, XboxController.Button.kB.value)
        .whenPressed(cannonSubsystem::closeBlastTank);
  }

}
