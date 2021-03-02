// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.CannonConstants.VALVE_OPEN_TIME;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_DRIVER_CONTROLLER;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TeleOpDriveCommand;
import frc.robot.commands.TeleOpTurretCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.NerdShooterSubsystem;
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
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CannonSubsystem cannonSubsystem = new CannonSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final NerdShooterSubsystem leftNerdShooterSubsystem = new NerdShooterSubsystem(NerdShooters.LEFT);
  private final NerdShooterSubsystem rightNerdShooterSubsystem = new NerdShooterSubsystem(NerdShooters.RIGHT);
  private final TeleOpDriveCommand teleOpDriveCommand = new TeleOpDriveCommand(driveTrainSubsystem, driverController);
  private final TeleOpTurretCommand teleOpTurretCommand = new TeleOpTurretCommand(turretSubsystem, driverController);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    driveTrainSubsystem.setDefaultCommand(teleOpDriveCommand);
    turretSubsystem.setDefaultCommand(teleOpTurretCommand);
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
    
    new JoystickButton(driverController, XboxController.Button.kBumperRight.value)
        .whenPressed(() -> turretSubsystem.raiseCannonToMax(), turretSubsystem);
    new JoystickButton(driverController, XboxController.Button.kBumperLeft.value)
        .whenPressed(() -> turretSubsystem.lowerCannonToMin(), turretSubsystem);
  }

}
