package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.frc5687.steamworks.protobot.Robot;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Baxter on 2/12/2017.
 */
public class PDP extends PowerDistributionPanel {

    public PDP() {
        super();
    }

    public double getGearHandlerAmps() {
        return getCurrent(RobotMap.GearHandler.PDP_GEAR_MOTOR);
    }

}
