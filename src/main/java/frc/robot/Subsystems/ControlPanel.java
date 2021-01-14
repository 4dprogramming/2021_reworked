/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*/package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;

/**
 * Control Panel Subsystem.
 */
/*/public class ControlPanel {
    private static ControlPanel mInstance = new ControlPanel();

    public static ControlPanel getInstance(){
        return mInstance;
    }

    public Solenoid controlPanelPiston;
    public VictorSP controlPanelMotor;

    private ControlPanel(){
        controlPanelMotor = new VictorSP(Constants.controlPanelMotorPort);
        controlPanelPiston = new Solenoid(Constants.controlPanelSolenoidPort);
    }

    //TO-DO create the control panel methods
}/*/
