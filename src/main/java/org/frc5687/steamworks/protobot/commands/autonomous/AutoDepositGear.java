package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.OpenGearHandler;
import org.frc5687.steamworks.protobot.commands.Shift;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoDepositGear extends SteamworksBaseCommandGroup {

    public static enum Position {
        LEFT,
        CENTER,
        RIGHT;
    }

    public AutoDepositGear(Position position) {
        super();
//        if (position == Position.CENTER) {
        addSequential(new Shift(DoubleSolenoid.Value.kReverse));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_CENTER_DISTANCE, Constants.Auto.Drive.SPEED));
/*        } else {
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED));
            if (position == Position.LEFT) {
                addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_TURN));
            } else if (position == Position.RIGHT) {
                addSequential(new AutoAlign(-Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_TURN));
            }
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FINAL_DISTANCE, Constants.Auto.Drive.SPEED));
        }
        */
        addSequential(new OpenGearHandler());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, -Constants.Auto.Drive.SPEED));
    }

}