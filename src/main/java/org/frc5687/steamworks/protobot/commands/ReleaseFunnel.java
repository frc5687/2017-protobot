package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.Climber;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Caleb on 1/28/2017.
 */
public class ReleaseFunnel extends Command {
    private long endTime;
    public ReleaseFunnel() {requires(climber);}


    protected void initialize(){
        endTime = System.currentTimeMillis() + Constants.GearHandler.funnelReleaseTime;
    }

    protected void execute(){
        climber.setSpeed(Constants.GearHandler.funnelReleaseSpeed);
    }

    protected boolean isFinished(){
        return System.currentTimeMillis() > endTime;
    }

}
