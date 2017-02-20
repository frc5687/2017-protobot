package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoChooser {
    private AnalogPotentiometer rotor;

    public AutoChooser() {
        rotor = new AnalogPotentiometer(RobotMap.AutoChooser.ROTOR);
    }

    public boolean isAtZero(){
        return (rotor.get() <= Constants.Auto.Rotor.zeroTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.zeroTarget-Constants.Auto.Rotor.threshhold);
    }

    public boolean isAtOne(){
        return (rotor.get() <= Constants.Auto.Rotor.oneTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.oneTarget-Constants.Auto.Rotor.threshhold);
    }

    public boolean isAtTwo(){
        return (rotor.get() <= Constants.Auto.Rotor.twoTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.twoTarget-Constants.Auto.Rotor.threshhold);
    }

    public boolean isAtThree(){
        return (rotor.get() <= Constants.Auto.Rotor.threeTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.threeTarget-Constants.Auto.Rotor.threshhold);
    }

    public boolean isAtFour(){
        return (rotor.get() <= Constants.Auto.Rotor.fourTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.fourTarget-Constants.Auto.Rotor.threshhold);
    }

    public boolean isAtFive(){
        return (rotor.get() <= Constants.Auto.Rotor.fiveTarget + Constants.Auto.Rotor.threshhold || rotor.get() >= Constants.Auto.Rotor.fiveTarget-Constants.Auto.Rotor.threshhold);
    }


    public void updateDashboard(){
        SmartDashboard.putNumber("SwitchValue", rotor.get());
    }
}
