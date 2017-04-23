package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.shifter;

/**
 * Command for controlling the drivetrain manually
 */
public class DriveWith2Joysticks extends Command {

    public DriveWith2Joysticks() {
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
        driveTrain.resetDriveEncoders();
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed(), true);
        runShifterAutomatically();
    }

    private void runShifterAutomatically() {
        if (shifter.waitPeriodElapsed() && driveTrain.isDrivingStraight() && shifter.isAutShiftEnabled()) {
            switch (shifter.getGear()) {
                case HIGH:
                    if(Math.abs(driveTrain.getRate()) < Constants.Shifter.SHIFT_DOWN_THRESHOLD) {
                        shift(Shifter.Gear.LOW);
                    }
                    break;
                case LOW:
                    if(Math.abs(driveTrain.getRate()) > Constants.Shifter.SHIFT_UP_THRESHOLD) {
                        shift(Shifter.Gear.HIGH);
                    }
                    break;
                default:
                    DriverStation.reportWarning("Shifter solenoid neither forwards or reversed!", false);
                    shift(Shifter.Gear.LOW);
                    break;
            }
        }
    }

    private void shift(Shifter.Gear gear) {
        DriverStation.reportError("Auto-shifting into " + gear.toString() + "(" + driveTrain.getRate() + ")", false);
        Command shift = new Shift(gear, true);
        shift.start();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
