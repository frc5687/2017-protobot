package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoChooser {
    private AnalogPotentiometer positionRotor;
    private AnalogPotentiometer gearRotor;

    public AutoChooser() {
        positionRotor = new AnalogPotentiometer(RobotMap.AutoChooser.POSITIONROTOR);
        gearRotor = new AnalogPotentiometer(RobotMap.AutoChooser.POSITIONROTOR);
    }

    public boolean positionIsAtZero(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.zeroTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.zeroTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean gearIsAtZero(){
        return (gearRotor.get() <= Constants.Auto.GearRotor.zeroTarget + Constants.Auto.GearRotor.tolerance || gearRotor.get() >= Constants.Auto.GearRotor.zeroTarget- Constants.Auto.GearRotor.tolerance);
    }


    public boolean gearIsAtOne(){
        return (gearRotor.get() <= Constants.Auto.GearRotor.oneTarget + Constants.Auto.GearRotor.tolerance || gearRotor.get() >= Constants.Auto.GearRotor.oneTarget- Constants.Auto.GearRotor.tolerance);
    }

    public boolean gearIsAtTwo() {
        return (gearRotor.get() <= Constants.Auto.GearRotor.twoTarget + Constants.Auto.GearRotor.tolerance || gearRotor.get() >= Constants.Auto.GearRotor.twoTarget - Constants.Auto.GearRotor.tolerance);
    }

    public boolean gearIsAtThree() {
        return (gearRotor.get() <= Constants.Auto.GearRotor.threeTarget + Constants.Auto.GearRotor.tolerance || gearRotor.get() >= Constants.Auto.GearRotor.threeTarget - Constants.Auto.GearRotor.tolerance);
    }

    public boolean isAtOne(){
        return(positionRotor.get() <= Constants.Auto.PositionRotor.oneTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.oneTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean isAtTwo(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.twoTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.twoTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean isAtThree(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.threeTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.threeTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean isAtFour(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.fourTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.fourTarget- Constants.Auto.PositionRotor.tolerance);
    }

    public boolean isAtFive(){
        return (positionRotor.get() <= Constants.Auto.PositionRotor.fiveTarget + Constants.Auto.PositionRotor.tolerance || positionRotor.get() >= Constants.Auto.PositionRotor.fiveTarget- Constants.Auto.PositionRotor.tolerance);
    }


    public void updateDashboard(){
        SmartDashboard.putNumber("SwitchValue", positionRotor.get());
    }
}
