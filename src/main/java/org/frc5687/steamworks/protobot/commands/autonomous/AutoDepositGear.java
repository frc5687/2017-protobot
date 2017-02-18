package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.OpenGearHandler;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoDepositGear extends CommandGroup {

    public static enum Position {
        LEFT(1),
        CENTER(0),
        RIGHT(-1);

        int turn;

        Position(int turn) {
            this.turn = turn;
        }

        public int getTurn() {
            return turn;
        }
    }

    public AutoDepositGear() {
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_INITIAL_DISTANCE));
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_TURN));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FINAL_DISTANCE));
        addSequential(new OpenGearHandler());
    }

}
