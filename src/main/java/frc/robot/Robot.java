/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Drivebase;
import frc.robot.Subsystems.Gamepad;
import frc.robot.Subsystems.*;
import frc.robot.Periodics.Teleop.*;
import frc.Math.PID;
import frc.Math.ShooterPID;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public DrivebaseTP mDrivebaseTP;
  public Drivebase mDrivebase;
  public Gamepad mGamepad;
  public DrivePanel mDrivePanel;
  public Intake mIntake;
  public Shooter mShooter;
  public TeleOpPeriodic mTeleOpPeriodic;
  public Conveyor mConveyor;
  public ShooterPID mShooterPID;
  public PID mPID;
  public double timer;
  public boolean developerMode;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putBoolean("Developer Mode", false);

    mDrivebase = Drivebase.getInstance();
    mGamepad = Gamepad.getInstance();
    mDrivePanel = DrivePanel.getInstance();
    mIntake = Intake.getInstance();
    mShooter = Shooter.getInstance();
    mDrivebaseTP = DrivebaseTP.getInstance();
    mTeleOpPeriodic = TeleOpPeriodic.getInstance();
    mPID = PID.getInstance();
    mConveyor = Conveyor.getInstance();
    mShooterPID = ShooterPID.getInstance();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Shooter RPM ", mShooter.getShooterRPM());
    SmartDashboard.putNumber("Accelerator RPM ", mShooter.getAccRPM());
    SmartDashboard.putBoolean("Is Ready For Shoot", mShooter.isReadyForShoot);
    SmartDashboard.putNumber("Gyro Angle", mDrivebase.getGyroAngle());
    developerMode = SmartDashboard.getBoolean("Developer Mode", false);
    SmartDashboard.putNumber("Shooter Encoder", mShooter.shooterEnc.getRaw());
    SmartDashboard.putNumber("Accelerator Encoder", mShooter.accEnc.getRaw());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    timer = Timer.getFPGATimestamp();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    if(Timer.getFPGATimestamp() - timer < 3){
      mShooter.shooterSpeedUp(3200);
      mShooter.shoot();
    }
    else if(Timer.getFPGATimestamp() - timer < 5){
      mShooter.shooterStop();
      mShooter.feederStop();
      mShooter.acceleratorWheel.set(VictorSPXControlMode.PercentOutput, 0);
      mDrivebase.robotDrive(-0.5, 0);
    }
    else{
      mDrivebase.robotDrive(0, 0);
    }
  }

  @Override
  public void teleopInit() {
    //super.teleopInit(); Don't know what this code does.
    mShooter.resetSensors();
    mShooterPID.accPIDReset();
    mShooterPID.shooterPIDReset();  
    mPID.turnPidReset();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    mDrivebaseTP.drivebaseTP();
    mTeleOpPeriodic.teleOpPeriodic();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
