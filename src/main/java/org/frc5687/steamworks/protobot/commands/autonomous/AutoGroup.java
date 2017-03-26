package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by Ben Bernard on 3/26/2017.
 */
public class AutoGroup extends SteamworksBaseCommandGroup {
    public AutoGroup(int position, int gear, int hopper) {
        super();
        // Add the initial operations based on position switch...
        switch (position) {
            case 0:
                break;
            case 1:
                DriverStation.reportError("Adding AutoDepositLeftFromFarLeft", false);
                addSequential(new AutoDepositLeftFromFarLeft());
                break;
            case 2:
                DriverStation.reportError("Adding AutoDepositLeftVision", false);
                addSequential(new AutoDepositLeftVision());
                break;
            case 3:
                DriverStation.reportError("Adding AutoDepositGear", false);
                addSequential(new AutoDepositGear());
                break;
            case 4:
                DriverStation.reportError("Adding AutoDepositRightVision", false);
                addSequential(new AutoDepositRightVision());
                break;
            case 5:
                DriverStation.reportError("Adding AutoDepositRightFromFarRight", false);
                addSequential(new AutoDepositRightFromFarRight());
                break;
            default:
                break;
        }

        // Now add follow-on operation based on the hopper switch
        switch (hopper) {
            case 0:
                // Don't do anything!
                break;
            case 1:
                // Traverse the neutral zone as expediently as possible!
                switch (position) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        // For left side, traverse left
                        DriverStation.reportError("Adding AutoTraverseNeutralZoneFromSide", false);
                        addSequential(new AutoTraverseNeutralZoneFromSide());
                        break;
                    case 3:
                        if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
                            DriverStation.reportError("Adding AutoTraverseNeutralZoneLeftFromCenter", false);
                            addSequential(new AutoTraverseNeutralZoneLeftFromCenter());
                        } else if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
                            DriverStation.reportError("Adding AutoTraverseNeutralZoneRightFromCenter", false);
                            addSequential(new AutoTraverseNeutralZoneRightFromCenter());
                        }
                }
                break;
            case 2:
                // Traverse the neutral zone on the left side
                switch (position) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        // For left side, traverse left
                        DriverStation.reportError("Adding AutoTraverseNeutralZoneFromSide", false);
                        addSequential(new AutoTraverseNeutralZoneFromSide());
                        break;
                    case 3:
                        DriverStation.reportError("Adding AutoTraverseNeutralZoneLeftFromCenter", false);
                        addSequential(new AutoTraverseNeutralZoneLeftFromCenter());
                }
                break;
            case 3:
                // Traverse the neutral zone on the right side
                switch (position) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        // For left side, traverse left
                        DriverStation.reportError("Adding AutoTraverseNeutralZoneFromSide", false);
                        addSequential(new AutoTraverseNeutralZoneFromSide());
                        break;
                    case 3:
                        DriverStation.reportError("Adding AutoTraverseNeutralZoneRightFromCenter", false);
                        addSequential(new AutoTraverseNeutralZoneRightFromCenter());
                }
                break;
        }

    }
}
