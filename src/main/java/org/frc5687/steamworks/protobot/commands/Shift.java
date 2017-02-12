package org.frc5687.steamworks.protobot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;


import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.shifter;

/**
 * Command for expanding piston of double solenoid
 */
public class Shift extends CommandGroup {

    private DoubleSolenoid.Value gear = DoubleSolenoid.Value.kOff;
    private double leftSpeed, rightSpeed;

    public Shift(DoubleSolenoid.Value gear) {
        this.gear = gear;
        addSequential(new StopMotor());
        addSequential(new ShiftGear(gear));
    }

    @Override
    protected void initialize() {
        leftSpeed = driveTrain.getLeftSpeed();
        rightSpeed = driveTrain.getRightSpeed();
    }

    @Override
    protected void end() {
        driveTrain.tankDrive(leftSpeed, rightSpeed);
    }

    public class StopMotor extends Command {

        private long endTime;

        public StopMotor() {
            requires(driveTrain);
        }

        @Override
        protected void initialize() {
            endTime = System.currentTimeMillis() + Constants.Shifter.STOP_MOTOR_TIME;
        }

        @Override
        protected void execute() {
            driveTrain.tankDrive(0, 0);
        }

        @Override
        protected void end() {
        }

        @Override
        protected boolean isFinished() {
            return System.currentTimeMillis() >= endTime;
        }

    }

    public class ShiftGear extends Command {

        private DoubleSolenoid.Value gear;

        public ShiftGear(DoubleSolenoid.Value gear) {
            this.gear = gear;
            requires(shifter);
        }

        @Override
        protected void execute() {
            shifter.shift(gear);
        }

        @Override
        protected boolean isFinished() {
            return shifter.getGear() == gear;
        }

    }

}
