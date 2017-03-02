package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoChooser {
    private RotarySwitch positionSwitch;
    private RotarySwitch gearSwitch;
    private RotarySwitch hopperSwitch;

    public boolean checkWithinTarget(double target,double tolerance, AnalogPotentiometer knob){
        return (knob.get() >= target - tolerance && knob.get()<= target + tolerance);
    }

    public AutoChooser() {
        positionSwitch = new RotarySwitch(RobotMap.AutoChooser.POSITION_SWITCH, .01, .092, .235, .505, .680, .823, .958);
        gearSwitch = new RotarySwitch(RobotMap.AutoChooser.GEAR_SWITCH,  .01, .092, .235, .505, .680, .823, .958);
        hopperSwitch = new RotarySwitch(RobotMap.AutoChooser.HOPPER_SWITCH,  .043, .09, .17, .23, .31, .5, .59, .68, .75, .82, .91, .96);
    }


    public int positionRotorValue(){
        return positionSwitch.get();
    }

    public int gearRotorValue(){
        return gearSwitch.get();
    }

    public int hopperRotorValue(){
        return hopperSwitch.get();
    }


    public void updateDashboard(){
        SmartDashboard.putNumber("AutoChooser/PositionValue", positionSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/GearValue", gearSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/HopperValue", hopperSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/PositionRotorPosition", positionSwitch.get());
        SmartDashboard.putNumber("AutoChooser/GearRotorPosition", gearSwitch.get());
        SmartDashboard.putNumber("AutoChooser/HopperRotorPosition", hopperSwitch.get());
    }
}
