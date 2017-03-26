package org.frc5687.steamworks.protobot.commands.actions;

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

    private long waitPeriodEndTime;

    public DriveWith2Joysticks() {
        requires(driveTrain);
        requires(shifter);
    }

    @Override
    protected void initialize() {
        driveTrain.resetDriveEncoders();
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
        runShifterAutomatically();
    }

    private void runShifterAutomatically() {
        if (System.currentTimeMillis() > waitPeriodEndTime) {
            switch (shifter.getGear()) {
                case kForward:
                    if(driveTrain.getRate() > Constants.Shifter.SHIFT_UP_TRHESHOLD) {
                        shift(Shifter.Gear.HIGH);
                    }
                    break;
                case kReverse:
                    if(driveTrain.getRate() > Constants.Shifter.SHIFT_UP_TRHESHOLD) {
                        shift(Shifter.Gear.LOW);
                    }
                    break;
                default:
                    DriverStation.reportWarning("Shifter solenoid neither forwards or reversed!", false);
                    shift(Shifter.Gear.HIGH);
                    break;
            }
        }
    }

    private void shift(Shifter.Gear gear) {
        waitPeriodEndTime = System.currentTimeMillis() + Constants.Shifter.WAIT_PERIOD;
        Command shift = new Shift(gear);
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
