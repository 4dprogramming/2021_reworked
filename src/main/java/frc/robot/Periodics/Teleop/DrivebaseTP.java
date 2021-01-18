/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Periodics.Teleop;

import frc.robot.Subsystems.DrivePanel;
import frc.robot.Subsystems.Drivebase;
import frc.robot.Subsystems.Gamepad;

/**
 * Drivebase Teleop Periodic.
 */
public class DrivebaseTP{
    private static DrivebaseTP mInstance = new DrivebaseTP();

    public static DrivebaseTP getInstance(){
        return mInstance;
    }
    
    public Drivebase mDrivebase;
    public Gamepad mGamepad;
    
    public double sensitivity = 0.75;
    
    public DrivebaseTP(){
        mDrivebase = Drivebase.getInstance();
        mGamepad = Gamepad.getInstance();
    }

    public void drivebaseTP(){
        mDrivebase.robotDrive((mGamepad.getForward()-mGamepad.getReverse()), mGamepad.getSteering()*sensitivity);
    }
}

