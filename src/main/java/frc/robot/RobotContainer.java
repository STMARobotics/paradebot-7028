// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.CannonConstants.VALVE_OPEN_TIME;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_DRIVER_CONTROLLER;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_OPERATOR_CONTROLLER;

import java.util.Map;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.NerdShootCommand;
import frc.robot.commands.PlaySoundOnceCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.TeleOpDriveCommand;
import frc.robot.commands.TeleOpTurretCommand;
import frc.robot.commands.TeleopRegulatorCommand;
import frc.robot.commands.ToggleAudioCommand;
import frc.robot.commands.TurretHoldPositionCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.CompressorSubsystem;
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
  private final XboxController driverController = new XboxController(DEVICE_ID_DRIVER_CONTROLLER);
  private final XboxController operatorController = new XboxController(DEVICE_ID_OPERATOR_CONTROLLER);
  
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  
  private final CannonSubsystem cannonSubsystem = new CannonSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final NerdShooterSubsystem leftNerdShooterSubsystem = new NerdShooterSubsystem(NerdShooter.LEFT);
  private final NerdShooterSubsystem rightNerdShooterSubsystem = new NerdShooterSubsystem(NerdShooter.RIGHT);
  private final NerdShootCommand nerdShootCommand = new NerdShootCommand(leftNerdShooterSubsystem);
  private final TeleOpDriveCommand teleOpDriveCommand = new TeleOpDriveCommand(driveTrainSubsystem, driverController);
  private final TeleOpTurretCommand teleOpTurretCommand = new TeleOpTurretCommand(turretSubsystem, operatorController);
  private final TeleopRegulatorCommand teleopRegulatorCommand = 
      new TeleopRegulatorCommand(cannonSubsystem, operatorController);
  private final TurretHoldPositionCommand turretHoldPositionCommand = 
      new TurretHoldPositionCommand(turretSubsystem, operatorController);
  private final CompressorSubsystem compressorSubsystem = new CompressorSubsystem();
  private final ToggleAudioCommand toggleAudioCommand = new ToggleAudioCommand();
  private final PlaySoundOnceCommand promoAudioCommand = new PlaySoundOnceCommand("promotion");
  private final PlaySoundOnceCommand shotAudioCommand = new PlaySoundOnceCommand("shot");
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
    new Trigger(RobotState::isEnabled).whenActive(toggleAudioCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // whileHeld for Full Auto
    // whenPressed for Semi Auto
    new JoystickButton(driverController, XboxController.Button.kB.value)
      .whenPressed(() -> nerdShootCommand.schedule());

    new JoystickButton(driverController, XboxController.Button.kA.value)
        .whenPressed(shotAudioCommand.alongWith(new ShootCommand(cannonSubsystem).withTimeout(VALVE_OPEN_TIME)));

    new JoystickButton(driverController, XboxController.Button.kY.value)
        .whileHeld(compressorSubsystem::startCompressor, compressorSubsystem);
    new JoystickButton(driverController, XboxController.Button.kY.value)
        .whenReleased(compressorSubsystem::stopCompressor, compressorSubsystem);

    new JoystickButton(driverController, XboxController.Button.kBack.value).toggleWhenPressed(toggleAudioCommand);
    new JoystickButton(driverController, XboxController.Button.kStart.value).whenPressed(promoAudioCommand);
    new JoystickButton(driverController, XboxController.Button.kX.value)
        .whenPressed(cannonSubsystem::openBlastTank, cannonSubsystem);
    new JoystickButton(driverController, XboxController.Button.kB.value)
        .whenPressed(cannonSubsystem::closeBlastTank, cannonSubsystem);
    
    new JoystickButton(operatorController, XboxController.Button.kBumperRight.value)
        .whenPressed(turretSubsystem::raiseCannonToMax, turretSubsystem);
    new JoystickButton(operatorController, XboxController.Button.kBumperLeft.value)
        .whenPressed(turretSubsystem::lowerCannonToMin, turretSubsystem);
    new JoystickButton(operatorController, XboxController.Button.kY.value)
        .toggleWhenPressed(turretHoldPositionCommand);
  }

}
