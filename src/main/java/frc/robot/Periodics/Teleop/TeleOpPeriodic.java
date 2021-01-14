/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Periodics.Teleop;

import frc.robot.Subsystems.*;

/**
 * Teleop Periodic Code
 */
public class TeleOpPeriodic {
    private static TeleOpPeriodic mInstance = new TeleOpPeriodic();

    private Shooter mShooter;
    private Intake mIntake;
    private Gamepad mGamepad;
    private DrivePanel mDrivePanel;
    boolean isUsingGamepad = false;

    public static TeleOpPeriodic getInstance(){
        return mInstance;
    }

    private TeleOpPeriodic(){
        mShooter = Shooter.getInstance();
        mIntake = Intake.getInstance();
        mGamepad = Gamepad.getInstance();
        mDrivePanel = DrivePanel.getInstance();
    }

    public void teleOpPeriodic(){
        //pivot codes
        if(mDrivePanel.pivotDown()){
            mIntake.pivotDown();
        }
        else if(mDrivePanel.pivotUp()){
            mIntake.pivotUp();
        }
        else{
            mIntake.pivotStall();
        }

        //Intake Codes
        if(mGamepad.getIntakeGamepad()){
            mIntake.intakeOn();
            isUsingGamepad = true;
        }
        else if(mGamepad.getReverseIntakeGamepad()){
            mIntake.intakeReverse();
            isUsingGamepad = true;
        }
        else{
            //isControllingConveyor = false;
            mIntake.intakeStop();
            isUsingGamepad = false;
        }

        //Manual Intake Codes
        if(mDrivePanel.leftRoller()){
            mIntake.leftRoller();
        }
        else if(mDrivePanel.leftRollerReverse()){
            mIntake.leftRollerReverse();
        }
        else if(!isUsingGamepad){
            mIntake.stopLeftRoller();
        }

        //Center Roller
        if(mDrivePanel.centerRoller()){
            mIntake.centerRoller();
        }
        else if(mDrivePanel.centerRollerReverse()){
            mIntake.centerRollerReverse();
        }
        else if(!isUsingGamepad){
            mIntake.stopCenterRoller();
        }

        //Right Roller
        if(mDrivePanel.rightRoller()){
            mIntake.rightRollerReverse();
        }
        else if(mDrivePanel.rightRollerReverse()){
            mIntake.rightRoller();
        }
        else if(!isUsingGamepad){
            mIntake.stopRightRoller();
        }

        //Conveyor
        /*not used
        if(mDrivePanel.conveyor()){
            mConveyor.conveyorStart();
        }
        else if(mDrivePanel.conveyorReverse()){
            mConveyor.conveyorReverse();
        }
        else
        if(!isUsingGamepad && !mGamepad.startFeeder()){
            mConveyor.conveyorStop();
        }*/

        //feeder codes
        if(mGamepad.startFeeder()){
            mShooter.shoot();
        }
        else if(mDrivePanel.feederIn()){
            mShooter.feederOn();
        }
        else{
            mShooter.feederOff();
        }

        //acc wheel
        if(mDrivePanel.shooterSpeedUp()){
            mShooter.shooterSpeedUp(3000);
        }
        else{
            mShooter.shooterStop();
        }

        /*if(!isControllingConveyor && mGamepad.startFeeder()){
            mConveyor.conveyorStop();
        }*/
    }
}