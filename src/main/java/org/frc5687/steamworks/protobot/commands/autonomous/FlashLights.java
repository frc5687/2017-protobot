package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;

public class FlashLights extends Command {

    private int state = 0;
    private long endTime;

    public FlashLights() {
        requires(ledStrip);
    }

    @Override
    public void initialize() {
        state = 0;
        endTime = System.currentTimeMillis() + 1000;
    }

    @Override
    protected void execute() {
        if (System.currentTimeMillis() > endTime) {
            endTime = System.currentTimeMillis() + 1000;
            state++;
        }
        switch (state) {
            case 1:
                ledStrip.setStripColor(Color.RED);
                break;
            case 2:
                ledStrip.setStripColor(Color.GREEN);
                break;
            case 3:
                ledStrip.setStripColor(Color.BLUE);
                break;
            case 4:
                ledStrip.setStripColor(Color.ORANGE);
                break;
            case 5:
                ledStrip.setStripColor(Color.YELLOW);
                break;
            case 6:
                ledStrip.setStripColor(Color.PURPLE);
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return state > 6;
    }

}
