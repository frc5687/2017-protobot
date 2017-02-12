package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.frc5687.steamworks.protobot.Robot;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Baxter on 2/12/2017.
 */
public class PDP extends PowerDistributionPanel {

    public PDP() {
        super();
    }

    public double getGearHandlerAmps() {
        return getCurrent(RobotMap.GearHandler.PDP_GEAR_MOTOR);
    }



    public double getMeanDrivetrainAmps() {
        return (getRightFrontAmps() + getRightTopAmps() + getRightRearAmps() + getLeftFrontAmps() + getLeftTopAmps() + getLeftRearAmps()) / 6;
    }

    /**
     * Methods for getting current from individual drivetrain cims
     */

    public double getRightFrontAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_FRONT);
    }

    public double getRightTopAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_TOP);
    }

    public double getRightRearAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_REAR);
    }

    public double getLeftFrontAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_FRONT);
    }

    public double getLeftTopAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_TOP);
    }

    public double getLeftRearAmps() {
        return getCurrent(RobotMap.Drive.PDP_RIGHT_MOTOR_REAR);
    }

}
