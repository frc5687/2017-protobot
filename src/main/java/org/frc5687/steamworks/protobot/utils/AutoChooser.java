package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoGroup;

/**
 * Created by Caleb on 2/20/2017.
 */
public class AutoChooser {
    private RotarySwitch springSwitch;
    private RotarySwitch gearSwitch;
    private RotarySwitch hopperSwitch;

    public AutoChooser() {
        springSwitch = new RotarySwitch(RobotMap.AutoChooser.POSITION_SWITCH, Constants.RotarySwitch.TOLERANCE, .092, .235, .505, .680, .823, .958);
        gearSwitch = new RotarySwitch(RobotMap.AutoChooser.GEAR_SWITCH,  Constants.RotarySwitch.TOLERANCE, .092, .235, .505, .680, .823, .958);
        hopperSwitch = new RotarySwitch(RobotMap.AutoChooser.HOPPER_SWITCH,  .02, .09, .17, .23, .31, .5, .59, .68, .75, .82, .91, .96);
    }


    public int springSwitchValue(){
        return springSwitch.get();
    }

    public int gearSwitchValue(){
        return gearSwitch.get();
    }

    public int hopperSwitchValue(){
        return hopperSwitch.get();
    }


    public void updateDashboard(){
        SmartDashboard.putNumber("AutoChooser/Raw/Spring", springSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/Raw/Gear", gearSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/Raw/Hopper", hopperSwitch.getRaw());
        SmartDashboard.putNumber("AutoChooser/Numeric/Spring", springSwitch.get());
        SmartDashboard.putNumber("AutoChooser/Numeric/Gear", gearSwitch.get());
        SmartDashboard.putNumber("AutoChooser/Numeric/Hopper", hopperSwitch.get());
        SmartDashboard.putString("AutoChooser/Selection", AutoGroup.getDescription(springSwitch.get(), gearSwitch.get(), hopperSwitch.get()));


    }
}
