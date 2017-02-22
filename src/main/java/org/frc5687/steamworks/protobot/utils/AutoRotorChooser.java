package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoRotorChooser {
    private AnalogPotentiometer positionRotor;
    private AnalogPotentiometer gearRotor;
    private AnalogPotentiometer hopperRotor;

    public boolean checkWithinTarget(double target,double tolerance, AnalogPotentiometer knob){
        return (knob.get() >= target - tolerance && knob.get()<= target + tolerance);
    }

    public AutoRotorChooser() {
        positionRotor = new AnalogPotentiometer(RobotMap.AutoChooser.POSITION_ROTOR);
        gearRotor = new AnalogPotentiometer(RobotMap.AutoChooser.GEAR_ROTOR);
        hopperRotor = new AnalogPotentiometer(RobotMap.AutoChooser.HOPPER_ROTOR);
    }

    public boolean positionIsAtZero(){
        return checkWithinTarget(Constants.Auto.PositionRotor.zeroTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);

    }

    public boolean gearIsAtZero(){
        return checkWithinTarget(Constants.Auto.GearRotor.zeroTarget, Constants.Auto.GearRotor.tolerance, gearRotor);
    }


    public boolean gearIsAtOne(){
        return checkWithinTarget(Constants.Auto.GearRotor.oneTarget, Constants.Auto.GearRotor.tolerance, gearRotor);
    }

    public boolean gearIsAtTwo() {
        return checkWithinTarget(Constants.Auto.GearRotor.twoTarget, Constants.Auto.GearRotor.tolerance, gearRotor);
    }

    public boolean gearIsAtThree() {
        return checkWithinTarget(Constants.Auto.GearRotor.threeTarget, Constants.Auto.GearRotor.tolerance, gearRotor);
    }

    public boolean positionIsAtOne(){
        return checkWithinTarget(Constants.Auto.PositionRotor.oneTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);
    }

    public boolean positionIsAtTwo(){
        return checkWithinTarget(Constants.Auto.PositionRotor.twoTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);
    }

    public boolean positionIsAtThree(){
        return checkWithinTarget(Constants.Auto.PositionRotor.threeTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);
    }

    public boolean positionIsAtFour(){
        return checkWithinTarget(Constants.Auto.PositionRotor.fourTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);
    }

    public boolean positionIsAtFive(){
        return checkWithinTarget(Constants.Auto.PositionRotor.fiveTarget, Constants.Auto.PositionRotor.tolerance, positionRotor);    }

    public boolean autoHopper(){
        return checkWithinTarget(Constants.Auto.HopperRotor.oneTarget, Constants.Auto.HopperRotor.tolerance, gearRotor);
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
        SmartDashboard.putNumber("PositionValue", positionRotor.get());
        SmartDashboard.putNumber("GearValue", gearRotor.get());
        SmartDashboard.putNumber("HopperValue", hopperRotor.get());
        SmartDashboard.putNumber("PositionRotorPosition", positionRotorValue());
        SmartDashboard.putNumber("GearRotorPosition", gearRotorValue());
        SmartDashboard.putBoolean("HopperRotorPosition", autoHopper());


    }
}
