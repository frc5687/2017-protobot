package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

/**
 * Created by Caleb on 1/28/2017.
 */
public class ReleaseFunnel extends Command {
    public ReleaseFunnel() {requires(Climber);}
    int counter = 0;


    protected void initialize(){
    }

    protected void execute(){
        Climber.setSpeed(Constants.GearHandler.funnelReleaseSpeed);
        counter ++;
    }

    protected boolean isFinished(){
        return counter > (Constants.GearHandler.funnelReleaseTime*Constants.Limits.CYCLES_PER_SECOND);
    }

}
