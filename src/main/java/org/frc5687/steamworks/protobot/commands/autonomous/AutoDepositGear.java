package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.OpenMandibles;
import org.frc5687.steamworks.protobot.commands.Shift;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoDepositGear extends SteamworksBaseCommandGroup {

    public enum Position {
        LEFT,
        CENTER,
        RIGHT;
    }

    public AutoDepositGear(Position position) {
        super();
        addSequential(new Shift(DoubleSolenoid.Value.kReverse));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_CENTER_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new OpenMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, -Constants.Auto.Drive.SPEED));
    }

}