package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.utils.Color;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;

/**
 * Created by Ben Bernard on 2/21/2017.
 */
public class FlashLights extends Command {
    int state = 0;
    long endTime;

    public FlashLights() {
        requires(ledStrip);
    }

    public void initialize() {
        state = 0;
        endTime =  System.currentTimeMillis() + 1000;
    }


    protected void execute() {
        if (System.currentTimeMillis() > endTime) {
            endTime =  System.currentTimeMillis() + 1000;
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


    protected boolean isFinished() {
        return state>6;
    }
}
