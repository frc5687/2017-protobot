package org.frc5687.steamworks.protobot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;
import org.frc5687.steamworks.protobot.commands.autonomous.*;
import org.frc5687.steamworks.protobot.subsystems.*;
import org.frc5687.steamworks.protobot.utils.*;

public class Robot extends IterativeRobot implements IPoseTrackable {

    public static DriveTrain driveTrain;
    public static Mandibles mandibles;
    public static Shifter shifter;
    public static Climber climber;
    public static Lights lights;
    public static LEDStrip ledStrip;
    public static Robot robot;
    public static Pincers pincers;
    public static PiTrackerProxy piTrackerProxy;
    public static PoseTracker poseTracker;

    public static AHRS imu;
    public static PDP pdp;
    public static OI oi;

    public static AutoChooser autoRotorChooser;

    private Command autoCommand;

    public Robot() {
    }

    @Override
    public void startCompetition() {
        super.startCompetition();
    }

    @Override
    public void robotInit() {
        robot = this;

        driveTrain = new DriveTrain();
        mandibles = new Mandibles();
        shifter = new Shifter();
        climber = new Climber();
        lights = new Lights();
        ledStrip = new LEDStrip();
        pincers = new Pincers();
        autoRotorChooser = new AutoChooser();


        pdp = new PDP(); // must be initialized after other subsystems
        oi = new OI(); // must be initialized after subsystems

        Constants.isTony = pdp.isTony(); // must be set before subsystems

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

        try {
            UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
            camera0.setResolution(160, 120);
            camera0.setFPS(15);
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }
        try {
            UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
            camera1.setResolution(160, 120);
            camera1.setFPS(15);
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }

        if (Constants.isTony) {
            ledStrip.setStripColor(LEDColors.TONY_BOOTUP);
            DriverStation.reportError("Tony reporting for duty!", false);
        } else {
            ledStrip.setStripColor(LEDColors.RHODY_BOOTUP);
            DriverStation.reportError("Rhody at your service!", false);
        }

        piTrackerProxy = new PiTrackerProxy(20);
        poseTracker = new PoseTracker(this);

    }

    @Override
    public void disabledInit() {
        if (Constants.isTony) {
            ledStrip.setStripColor(LEDColors.TONY_BOOTUP);
        } else {
            ledStrip.setStripColor(LEDColors.RHODY_BOOTUP);
        }
    }

    @Override
    public void autonomousInit() {
        imu.zeroYaw();
        int position = autoRotorChooser.positionRotorValue();
        switch (position) {
            case 0:
                autoCommand = null;
                break;
            case 1:
                autoCommand = new AutoDepositLeftFromFarLeft();
                break;
            case 2:
                autoCommand = new AutoAlign(60, 1);
                break;
            case 3:
                autoCommand = new AutoDepositGear();
                break;
            case 4:
                autoCommand = new AutoAlign(-60, 1);
                break;
            case 5:
                autoCommand = new AutoDepositRightFromFarRight();
                break;
            default:
                autoCommand = null;
                break;
        }
        // autoCommand = new AutoAlign(-60, 0.5);
        // autoCommand = new AutoDepositRightFromFarRight();
        if (autoCommand != null) {
            autoCommand.start();
        }
    }

    @Override
    public void teleopInit() {
        if (autoCommand != null) autoCommand.cancel();
        ledStrip.setStripColor(LEDColors.TELEOP);
    }

    @Override
    public void testInit() {

    }

    @Override
    public void robotPeriodic() {

    }

    @Override
    public void disabledPeriodic() {
        updateDashboard();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        poll();
        updateDashboard();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    @Override
    public void testPeriodic() {
    }

    public void poll() {
        mandibles.poll();
        pincers.poll();
    }

    public void updateDashboard() {
        if (!DriverStation.getInstance().isFMSAttached()) {
            driveTrain.updateDashboard();
            mandibles.updateDashboard();
            shifter.updateDashboard();
            pincers.updateDashboard();
            lights.updateDashboard();
            ledStrip.updateDashboard();
            autoRotorChooser.updateDashboard();
            climber.updateDashboard();

            SmartDashboard.putBoolean("IsTony", Constants.isTony);
            SmartDashboard.putNumber("Yaw", imu.getYaw());
        }
    }

    @Override
    public Pose getPose() {
        return new TonyPose(imu.getYaw(), driveTrain.getLeftDistance(), driveTrain.getRightDistance(), 0);
    }
}
