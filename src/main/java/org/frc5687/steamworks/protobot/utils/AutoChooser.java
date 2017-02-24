package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoChooser {
    private AnalogPotentiometer positionSwitch;
    private AnalogPotentiometer gearSwitch;
    private AnalogPotentiometer hopperSwitch;

    public boolean checkWithinTarget(double target,double tolerance, AnalogPotentiometer knob){
        return (knob.get() >= target - tolerance && knob.get()<= target + tolerance);
    }

    public AutoChooser() {
        positionSwitch = new AnalogPotentiometer(RobotMap.AutoChooser.POSITION_SWITCH);
        gearSwitch = new AnalogPotentiometer(RobotMap.AutoChooser.GEAR_SWITCH);
        hopperSwitch = new AnalogPotentiometer(RobotMap.AutoChooser.HOPPER_SWITCH);
    }

    public boolean positionIsAtZero(){
        return checkWithinTarget(Constants.Auto.PositionRotor.ZERO_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);

    }

    public boolean gearIsAtZero(){
        return checkWithinTarget(Constants.Auto.GearRotor.ZERO_TARGET, Constants.Auto.GearRotor.TOLERANCE, gearSwitch);
    }


    public boolean gearIsAtOne(){
        return checkWithinTarget(Constants.Auto.GearRotor.ONE_TARGET, Constants.Auto.GearRotor.TOLERANCE, gearSwitch);
    }

    public boolean gearIsAtTwo() {
        return checkWithinTarget(Constants.Auto.GearRotor.TWO_TARGET, Constants.Auto.GearRotor.TOLERANCE, gearSwitch);
    }

    public boolean gearIsAtThree() {
        return checkWithinTarget(Constants.Auto.GearRotor.THREE_TARGET, Constants.Auto.GearRotor.TOLERANCE, gearSwitch);
    }

    public boolean positionIsAtOne(){
        return checkWithinTarget(Constants.Auto.PositionRotor.ONE_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);
    }

    public boolean positionIsAtTwo(){
        return checkWithinTarget(Constants.Auto.PositionRotor.TWO_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);
    }

    public boolean positionIsAtThree(){
        return checkWithinTarget(Constants.Auto.PositionRotor.THREE_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);
    }

    public boolean positionIsAtFour(){
        return checkWithinTarget(Constants.Auto.PositionRotor.FOUR_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);
    }

    public boolean positionIsAtFive(){
        return checkWithinTarget(Constants.Auto.PositionRotor.FIVE_TARGET, Constants.Auto.PositionRotor.TOLERANCE, positionSwitch);    }

    public boolean autoHopper(){
        return checkWithinTarget(Constants.Auto.HopperRotor.ONE_TARGET, Constants.Auto.HopperRotor.TOLERANCE, gearSwitch);
    }

    public int positionRotorValue(){

        if(positionIsAtZero()){
            return 0;
        }

        if (positionIsAtOne()){
            return 1;
        }

        if (positionIsAtTwo()){
            return 2;
        }

        if (positionIsAtThree()){
            return 3;
        }

        if (positionIsAtFour()){
            return 4;
        }

       if (positionIsAtFive()){
            return 5;
        }

        return -1;
    }

    public int gearRotorValue(){
        if (gearIsAtZero()){
            return 0;
        }

        if(gearIsAtOne()){
            return 1;
        }

        if(gearIsAtTwo()){
            return 2;
        }

        if(gearIsAtThree()){
            return 3;
        }
        return -1;
    }



    public void updateDashboard(){
        SmartDashboard.putNumber("AutoChooser/PositionValue", positionSwitch.get());
        SmartDashboard.putNumber("AutoChooser/GearValue", gearSwitch.get());
        SmartDashboard.putNumber("AutoChooser/HopperValue", hopperSwitch.get());
        SmartDashboard.putNumber("AutoChooser/PositionRotorPosition", positionRotorValue());
        SmartDashboard.putNumber("AutoChooser/GearRotorPosition", gearRotorValue());
        SmartDashboard.putBoolean("AutoChooser/HopperRotorPosition", autoHopper());
    }
}
