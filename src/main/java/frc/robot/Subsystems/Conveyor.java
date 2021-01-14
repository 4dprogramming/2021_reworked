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
public class Conveyor {

    //Hatip Edilmesi lazım

    private static Conveyor mInstance = new Conveyor();

    public static Conveyor getInstance(){
        return mInstance;
    }

    public VictorSP conveyorMotor;

    private Conveyor(){
        conveyorMotor = new VictorSP(Constants.conveyorMotorPort);
    }

    public void conveyorStart(){
        conveyorMotor.set(1);
    }

    public void conveyorStop(){
        conveyorMotor.set(0);
    }
    
    public void conveyorReverse(){
        conveyorMotor.set(-0.5);
    }
}
