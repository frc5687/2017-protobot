package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Created by Caleb on 2/13/2017.
 */
public class TestEncoders extends Command {
    public TestEncoders() {
        requires(driveTrain);

    }

    private boolean IsEncoderWorking = false;
    private double startRightDistance = driveTrain.getRightDistance();
    private double startLeftDistance = driveTrain.getLeftDistance();
    private long startTime = System.currentTimeMillis();
    private long endTime = startTime + Constants.Drive.ENCODDER_TEST_TIME;

    @Override
    protected void initialize() {
    }

    protected void execute() {
        driveTrain.tankDrive(Constants.Drive.ENCODER_TEST_SPEED, Constants.Drive.ENCODER_TEST_SPEED);
        while(System.currentTimeMillis()<endTime) {
        }
        driveTrain.tankDrive(0,0);
        if (driveTrain.getLeftDistance()-startLeftDistance>Constants.Encoders.Threshholds.encodeThreshold ){
            if (driveTrain.getRightDistance()-startRightDistance> Constants.Encoders.Threshholds.encodeThreshold){
                IsEncoderWorking = true;
            }
        }
        //todo add an if statement that turns lights green if isEncoderWorkingand false otherwise

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
