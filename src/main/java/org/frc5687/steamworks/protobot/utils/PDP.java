package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    public double getClimberAAmps() {
        return getCurrent(RobotMap.Climber.PDP_CLIMBER_MOTOR_A);
    }

    public double getClimberBAmps() {
        return getCurrent(RobotMap.Climber.PDP_CLIMBER_MOTOR_B);
    }
    public double getMeanClimberAmps() {
        return (getClimberAAmps() + getClimberBAmps()) / 2 ;
    }

    public double getDustpanLifterAmps() {
        return getCurrent(RobotMap.Dustpan.PDP_DUSTPAN_LIFTER_MOTOR);
    }

    public double getDustpanRollerAmps() {
        return getCurrent(RobotMap.Dustpan.PDP_DUSTPAN_ROLLER_MOTOR);
    }

    public double getMeanDrivetrainAmps() {
        return getTotalDriveTrainAmps() / 6;
    }

    /*
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

    public double getTotalLeftDriveTrainAmps() {
        return getLeftFrontAmps() + getLeftTopAmps() + getLeftRearAmps();
    }

    public double getTotalRightDriveTrainAmps() {
        return getRightFrontAmps() + getRightTopAmps() + getRightRearAmps();
    }

    public double getTotalDriveTrainAmps() {
        return getTotalLeftDriveTrainAmps() + getTotalRightDriveTrainAmps();
    }

    public boolean getIndicator() {
        return indicator.get();
    }

    public boolean isTony() {
        return indicator.get();
    }

    public double getMandiblesAmps() {
        return getCurrent(RobotMap.Mandibles.PDP_MANDIBLES_MOTOR);
    }

    public double getTotalClimberAmps() {
        return getClimberAAmps() + getClimberBAmps();
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("PDP/Current/Total", getTotalCurrent());
        SmartDashboard.putNumber("PDP/Current/DriveTrain", getTotalDriveTrainAmps());
        SmartDashboard.putNumber("PDP/Current/Dustpan Lifter", getDustpanLifterAmps());
        SmartDashboard.putNumber("PDP/Current/Dustpan Roller", getDustpanRollerAmps());
        SmartDashboard.putNumber("PDP/Current/Mandibles", getMandiblesAmps());
        SmartDashboard.putNumber("PDP/Current/Climber", getTotalClimberAmps());
    }

}
