package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.DriverStation;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;

/**
 * Created by Baxter on 3/25/2017.
 */
public class AutoAlignAwayFromBoiler extends AutoAlign {

    public AutoAlignAwayFromBoiler(double speed) {
        super(0, speed);
    }

    @Override
    protected void initialize() {
        switch (DriverStation.getInstance().getAlliance()) {
            case Blue:
                setAngle(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_RIGHT);
                break;
            case Red:
                setAngle(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_LEFT);
                break;
            default:
                setAngle(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_LEFT);
                break;
        }
        super.initialize();
    }
}
