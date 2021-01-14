/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Intake {
    private static Intake mInstance = new Intake();

    private Conveyor mConveyor;
    public VictorSP intakeMotor;
    public VictorSP centerR;
    public VictorSP centerL;
    public VictorSP pivotMotor;
    

    public static Intake getInstance(){
        return mInstance;
    }

    private Intake(){
        mConveyor = Conveyor.getInstance();
        intakeMotor = new VictorSP(Constants.intakeMotorPort);
        centerL = new VictorSP(Constants.centerLeftMotorPort);
        centerR = new VictorSP(Constants.centerRightMotorPort);
        pivotMotor = new VictorSP(Constants.pivotMotorPort);
    }
    //Pivot Motor Codes

    public void pivotDown(){
        pivotMotor.set(-0.2);
    }

    public void pivotUp(){
        pivotMotor.set(0.3);
    }

    public void pivotStall(){
        pivotMotor.set(0);
    }

    public void intakeOn(){
        intakeMotor.set(-1);
        centerL.set(1);
        centerR.set(-1);
        mConveyor.conveyorStart();
        /* Used to fit 5 balls to the conveyor
        mShooter.acceleratorWheel.set(VictorSPXControlMode.PercentOutput, -0.5);
        mShooter.feederOn();
        */
    }

    public void intakeReverse(){
        intakeMotor.set(1);
        centerL.set(-1);
        centerR.set(1);
        mConveyor.conveyorReverse();
        /*Used when there is 5 balls in the conveyor
        mShooter.feederReverse();
        */
    }

    public void intakeStop(){
        intakeMotor.set(0);
        centerL.set(0);
        centerR.set(0);
        mConveyor.conveyorStop();
        /*Used when there is 5 balls in the conveyor
        Constants.stopAcc = true;
        mShooter.feederStop();
        */
    }

    //Manual Intake Codes

    public void centerRoller(){
        intakeMotor.set(0.2);
    }

    public void leftRoller(){
        centerL.set(1);
    }

    public void rightRoller(){
        centerR.set(-1);
    }

    //Manual Intake Reverse Codes
    public void centerRollerReverse(){
        intakeMotor.set(-1);
    }

    public void leftRollerReverse(){
        centerL.set(-1);
    }

    public void rightRollerReverse(){
        centerR.set(1);
    }

    //Manual Intake Stop
    public void stopCenterRoller(){
        intakeMotor.set(0);
    }

    public void stopLeftRoller(){
        centerL.set(0);
    }

    public void stopRightRoller(){
        centerR.set(0);
    }

}
