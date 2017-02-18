package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoCrossField extends AutoDrive {

    public AutoCrossField() {
        super(Constants.Auto.AnglesAndDistances.CROSS_FIELD_DISTANCE, Constants.Auto.Drive.SPEED);
    }

}
