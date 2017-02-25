package org.frc5687.steamworks.protobot.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Robot;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Baxter on 2/12/2017.
 */
public class PDP extends PowerDistributionPanel {

    private DigitalInput indicator;
    public PDP() {
        super();
        indicator = new DigitalInput(RobotMap.Misc.INDICATOR);
    }

    public double getGearHandlerAmps() {
        return getCurrent(RobotMap.GearHandler.PDP_GEAR_MOTOR);
    }

    public double getClimberAmps() {
        return getCurrent(RobotMap.Climber.PDP_CLIMBER_MOTOR);
    }

    public double getPincersAmps() {
        return getCurrent(4);
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
        return getCurrent(RobotMap.Drive.PDP_LEFT_MOTOR_FRONT);
    }

    public double getLeftTopAmps() {
        return getCurrent(RobotMap.Drive.PDP_LEFT_MOTOR_TOP);
    }

    public double getLeftRearAmps() {
        return getCurrent(RobotMap.Drive.PDP_LEFT_MOTOR_REAR);
    }

    public boolean getIndicator() {
        return indicator.get();
    }

    public boolean isTony() {
        DriverStation.reportError("Indicator reports " + Boolean.toString(indicator.get()), false);
        return indicator.get();
    }

}
