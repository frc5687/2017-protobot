package org.frc5687.steamworks.protobot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.subsystems.DriveTrain;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;
import org.frc5687.steamworks.protobot.subsystems.Pneumatics;
import org.frc5687.steamworks.protobot.subsystems.Climber;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class Robot extends IterativeRobot {

    public static AHRS imu;
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

    /**
     * Represents the climbing mechanism
     */
    public static Climber climber;

    public static Robot robot;

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
        climber = new Climber();


        try {
            // Try to connect to the navX imu.
            imu = new AHRS(SPI.Port.kMXP);

            // Report to both the logs and the dashboard.  We may not want to keep this permanently, but it's helpful for our initial testing.
            DriverStation.reportError(String.format("Connected to navX MXP with FirmwareVersion %1$s", imu.getFirmwareVersion()), false);
            SmartDashboard.putString("FirmwareVersion", imu.getFirmwareVersion());
        } catch (Exception ex) {
            // If there are any errors, null out the imu reference and report the error both to the logs and the dashboard.
            SmartDashboard.putString("FirmwareVersion", "navX not connected");
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
            imu = null;
        }

        oi = new OI(); // must be initialized after subsystems

        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        imu.reset();

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
        sendIMUData();
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
    }

    protected void sendIMUData() {
        if (imu==null) {
            // If we can't find the imu, report that to the dashboard and return.
            SmartDashboard.putString("FirmwareVersion",      "navX not connected");
            return;
        }

        // Display 6-axis Processed Angle Data
        SmartDashboard.putBoolean(  "IMU/Connected",        imu.isConnected());
        SmartDashboard.putBoolean(  "IMU/IsCalibrating",    imu.isCalibrating());
        SmartDashboard.putNumber(   "IMU/Yaw",              imu.getYaw());
        SmartDashboard.putNumber(   "IMU/Pitch",            imu.getPitch());
        SmartDashboard.putNumber(   "IMU/Roll",             imu.getRoll());

        // Display tilt-corrected, Magnetometer-based heading (requires magnetometer calibration to be useful)
        SmartDashboard.putNumber(   "IMU/CompassHeading",   imu.getCompassHeading());

        // Display 9-axis Heading (requires magnetometer calibration to be useful)
        SmartDashboard.putNumber(   "IMU/FusedHeading",     imu.getFusedHeading());


        // These functions are compatible w/the WPI Gyro Class, providing a simple
        // path for upgrading from the Kit-of-Parts gyro to the navx MXP
        SmartDashboard.putNumber(   "IMU/TotalYaw",         imu.getAngle());
        SmartDashboard.putNumber(   "IMU/YawRateDPS",       imu.getRate());

        // Display Processed Acceleration Data (Linear Acceleration, Motion Detect)
        SmartDashboard.putNumber(   "IMU/Accel_X",          imu.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU/Accel_Y",          imu.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU/IsMoving",         imu.isMoving());
        SmartDashboard.putBoolean(  "IMU/IsRotating",       imu.isRotating());

        // Display estimates of velocity/displacement.  Note that these values are not expected to be accurate enough
        // for estimating robot position on a FIRST FRC Robotics Field, due to accelerometer noise and the compounding
        // of these errors due to single (velocity) integration and especially double (displacement) integration.
        SmartDashboard.putNumber(   "IMU/Velocity_X",           imu.getVelocityX());
        SmartDashboard.putNumber(   "IMU/Velocity_Y",           imu.getVelocityY());
        SmartDashboard.putNumber(   "IMU/Displacement_X",       imu.getDisplacementX());
        SmartDashboard.putNumber(   "IMU/Displacement_Y",       imu.getDisplacementY());

        // Connectivity Debugging Support
        SmartDashboard.putNumber(   "IMU/Byte_Count",       imu.getByteCount());
        SmartDashboard.putNumber(   "IMU/Byte_Count",       imu.getByteCount());
        SmartDashboard.putNumber(   "IMU/Update_Count",     imu.getUpdateCount());
    }


}

