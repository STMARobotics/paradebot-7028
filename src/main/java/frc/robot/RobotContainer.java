// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.CannonConstants.VALVE_OPEN_TIME;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_CANNON_CONTROLLER;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_DRIVER_CONTROLLER;
import static frc.robot.Constants.ControllerConstants.DEVICE_ID_NERD_CONTROLLER;

import java.util.Map;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CompressorCommand;
import frc.robot.commands.NerdRevCommand;
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
import frc.robot.subsystems.NerdFlywheelSubsystem;
import frc.robot.subsystems.NerdPusherSubsystem;
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
  private final XboxController cannonController = new XboxController(DEVICE_ID_CANNON_CONTROLLER);
  private final XboxController nerdController = new XboxController(DEVICE_ID_NERD_CONTROLLER);
  
  private final DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
  private final CannonSubsystem cannonSubsystem = new CannonSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final NerdFlywheelSubsystem leftNerdFlywheelSubsystem = new NerdFlywheelSubsystem(NerdFlywheel.LEFT);
  private final NerdFlywheelSubsystem rightNerdFlywheelSubsystem = new NerdFlywheelSubsystem(NerdFlywheel.RIGHT);
  private final NerdPusherSubsystem leftNerdPusherSubsystem = new NerdPusherSubsystem(NerdPusher.LEFT);
  private final NerdPusherSubsystem rightNerdPusherSubsystem = new NerdPusherSubsystem(NerdPusher.RIGHT);
  private final CompressorSubsystem compressorSubsystem = new CompressorSubsystem();

  private final NerdRevCommand leftNerdRevCommand = new NerdRevCommand(leftNerdFlywheelSubsystem);
  private final NerdRevCommand rightNerdRevCommand = new NerdRevCommand(rightNerdFlywheelSubsystem);
  private final NerdShootCommand leftNerdShootCommand = new NerdShootCommand(leftNerdPusherSubsystem);
  private final NerdShootCommand rightNerdShootCommand = new NerdShootCommand(rightNerdPusherSubsystem);
  private final TeleOpDriveCommand teleOpDriveCommand = new TeleOpDriveCommand(driveTrainSubsystem, driverController);
  private final TeleOpTurretCommand teleOpTurretCommand = new TeleOpTurretCommand(turretSubsystem, cannonController);
  private final TeleopRegulatorCommand teleopRegulatorCommand = 
      new TeleopRegulatorCommand(cannonSubsystem, cannonController);
  private final TurretHoldPositionCommand turretHoldPositionCommand = 
      new TurretHoldPositionCommand(turretSubsystem, cannonController);
  private final ToggleAudioCommand toggleAudioCommand = new ToggleAudioCommand();
  private final PlaySoundOnceCommand promoAudioCommand = new PlaySoundOnceCommand("promotion");
  private final PlaySoundOnceCommand shotAudioCommand = new PlaySoundOnceCommand("shot");
  private final CompressorCommand compressorCommand = new CompressorCommand(compressorSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    ShuffleboardTab consoleTab = Shuffleboard.getTab("Console");

    consoleTab.addNumber("Cannon Pressure", cannonSubsystem::getPressure)
        .withWidget(BuiltInWidgets.kDial).withProperties(Map.of("min", 0, "max", 125))
        .withSize(3, 2).withPosition(0, 0);

    Shuffleboard.getTab("Console").addNumber("System Pressure", compressorSubsystem::getPressure)
        .withWidget(BuiltInWidgets.kDial).withProperties(Map.of("min", 0, "max", 125))
        .withSize(3, 2).withPosition(3, 0);

    driveTrainSubsystem.setDefaultCommand(teleOpDriveCommand);
    turretSubsystem.setDefaultCommand(teleOpTurretCommand);
    cannonSubsystem.setDefaultCommand(teleopRegulatorCommand);
    new Trigger(RobotState::isEnabled).whenActive(toggleAudioCommand).whenActive(compressorCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(nerdController, OperatorButton.LEFT_REV.getButtonIndex())
        .toggleWhenPressed(leftNerdRevCommand);
    new JoystickButton(nerdController, OperatorButton.LEFT_FIRE.getButtonIndex())
        .whileHeld(leftNerdShootCommand);

    new JoystickButton(nerdController, OperatorButton.RIGHT_REV.getButtonIndex())
        .toggleWhenPressed(rightNerdRevCommand);
    new JoystickButton(nerdController, OperatorButton.RIGHT_FIRE.getButtonIndex())
        .whileHeld(rightNerdShootCommand);

    new JoystickButton(cannonController, OperatorButton.CANNON_FIRE.getButtonIndex())
        .whenPressed(shotAudioCommand.alongWith(new ShootCommand(cannonSubsystem).withTimeout(VALVE_OPEN_TIME)));

    new JoystickButton(driverController, XboxController.Button.kY.value).toggleWhenPressed(compressorCommand);

    new JoystickButton(driverController, XboxController.Button.kBack.value).toggleWhenPressed(toggleAudioCommand);
    new JoystickButton(driverController, XboxController.Button.kStart.value).whenPressed(promoAudioCommand);
    new JoystickButton(cannonController, OperatorButton.FILL_SOLENOID.getButtonIndex())
        .whileHeld(cannonSubsystem::openBlastTank, cannonSubsystem);
    new JoystickButton(cannonController, OperatorButton.FILL_SOLENOID.getButtonIndex())
        .whenReleased(cannonSubsystem::closeBlastTank, cannonSubsystem);
    
    new JoystickButton(cannonController, XboxController.Button.kBumperRight.value)
        .whenPressed(turretSubsystem::raiseCannonToMax, turretSubsystem);
    new JoystickButton(cannonController, XboxController.Button.kBumperLeft.value)
        .whenPressed(turretSubsystem::lowerCannonToMin, turretSubsystem);
    new JoystickButton(cannonController, XboxController.Button.kY.value)
        .toggleWhenPressed(turretHoldPositionCommand);
  }

}
