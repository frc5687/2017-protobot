package org.frc5687.steamworks.protobot.commands.autonomous;

/**
 * Created by Ben Bernard on 3/25/2017.
 */
public class TestGroup extends SteamworksBaseCommandGroup {

    public TestGroup() {
        super();
        addSequential(new AutoDepositCenterDeadReckoning());
        addSequential(new AutoTraverseNeutralZoneLeftFromCenter());
    }
}
