package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to autonomously turn the robot in place to a specified angle relative to its initial position
 */
public class AutoTurn extends Command implements PIDOutput {

    private PIDController controller;
    private double target;

    public AutoTurn(double angle) {
        target = angle;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void pidWrite(double output) {

    }
}
