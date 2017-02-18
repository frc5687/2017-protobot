package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.subsystems.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;
import org.frc5687.steamworks.protobot.utils.PDP;
import com.kauailabs.navx.frc.AHRS;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class Robot extends IterativeRobot {

    /**
     * Represents the operator interface / controls
     */
    public static OI oi;

    /**
     * Represents the robot's drivetrain
     */
    public static DriveTrain driveTrain;

    /**
     * Represents the robot's gear handler
     */
    public static GearHandler gearHandler;

    /**
     * Represents the pneumatics
     */
    public static Pneumatics pneumatics;

    public static Shifter shifter;

    /**
     * Represents the climbing mechanism
     */
    public static Climber climber;

    public static Robot robot;

    public static PDP pdp;

    public static AHRS imu;

    Command autoCommand;

    public Robot() {
    }

    @Override
    public void startCompetition() {
        super.startCompetition();
    }

    public void robotInit() {
        robot = this;

        driveTrain = new DriveTrain();
        gearHandler = new GearHandler();
        pneumatics = new Pneumatics();
        shifter = new Shifter();
        climber = new Climber();

        pdp = new PDP();

        try {
            // Try to connect to the navX imu.
            imu = new AHRS(SPI.Port.kMXP);
            // Report firmware version to SmartDashboard
            SmartDashboard.putString("FirmwareVersion", imu.getFirmwareVersion());
        } catch (Exception ex) {
            // If there are any errors, null out the imu reference and report the error both to the logs and the dashboard.
            SmartDashboard.putString("FirmwareVersion", "navX not connected");
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
            imu = null;
        }

        oi = new OI(); // must be initialized after subsystems

        UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
        camera0.setResolution(640, 480);
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
        camera1.setResolution(640, 480);
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }

    @Override
    public void autonomousInit() {
        autoCommand = oi.getAutonomous();
        if (autoCommand != null) {
            autoCommand.start();
        }
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        if(autoCommand != null) autoCommand.cancel();
    }

    @Override
    public void testInit() {
        super.testInit();
    }

    @Override
    public void robotPeriodic() {
        super.robotPeriodic();
    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }

    public void updateDashboard(){
        driveTrain.updateDashboard();
        gearHandler.updateDashboard();
        driveTrain.updateDashboard();
        shifter.updateDashboard();
    }

}
