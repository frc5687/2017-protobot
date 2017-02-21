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

    public AutoRotorChooser() {
        positionRotor = new AnalogPotentiometer(RobotMap.AutoChooser.POSITION_ROTOR);
        gearRotor = new AnalogPotentiometer(RobotMap.AutoChooser.POSITION_ROTOR);
    }

    public boolean positionIsAtZero(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.zeroTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.zeroTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean gearIsAtZero(){
        return (gearRotor.get() <= Constants.Auto.GearRotor.zeroTarget + Constants.Auto.GearRotor.tolerance && gearRotor.get() >= Constants.Auto.GearRotor.zeroTarget- Constants.Auto.GearRotor.tolerance);
    }


    public boolean gearIsAtOne(){
        return (gearRotor.get() <= Constants.Auto.GearRotor.oneTarget + Constants.Auto.GearRotor.tolerance && gearRotor.get() >= Constants.Auto.GearRotor.oneTarget- Constants.Auto.GearRotor.tolerance);
    }

    public boolean gearIsAtTwo() {
        return (gearRotor.get() <= Constants.Auto.GearRotor.twoTarget + Constants.Auto.GearRotor.tolerance && gearRotor.get() >= Constants.Auto.GearRotor.twoTarget - Constants.Auto.GearRotor.tolerance);
    }

    public boolean gearIsAtThree() {
        return (gearRotor.get() <= Constants.Auto.GearRotor.threeTarget + Constants.Auto.GearRotor.tolerance && gearRotor.get() >= Constants.Auto.GearRotor.threeTarget - Constants.Auto.GearRotor.tolerance);
    }

    public boolean positionIsAtOne(){
        return(positionRotor.get() <= Constants.Auto.PositionRotor.oneTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.oneTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean positionIsAtTwo(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.twoTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.twoTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean positionIsAtThree(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.threeTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.threeTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean positionIsAtFour(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.fourTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.fourTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean positionIsAtFive(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.fiveTarget + Constants.Auto.PositionRotor.tolerance && positionRotor.get() >= Constants.Auto.PositionRotor.fiveTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean autoHopper(){
        return (hopperRotor.get() <= Constants.Auto.HopperRotor.zeroTarget + Constants.Auto.HopperRotor.tolerance && hopperRotor.get() >= Constants.Auto.HopperRotor.zeroTarget - Constants.Auto.HopperRotor.tolerance );
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
