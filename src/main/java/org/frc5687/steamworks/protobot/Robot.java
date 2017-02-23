package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.commands.DisableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoCrossBaseline;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoDepositGear;
import org.frc5687.steamworks.protobot.commands.autonomous.FlashLights;
import org.frc5687.steamworks.protobot.subsystems.*;
import org.frc5687.steamworks.protobot.utils.AutoChooser;
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


    public static Shifter shifter;

    /**
     * Represents the climbing mechanism
     */
    public static Climber climber;

    public static Lights lights;

    public static LEDStrip ledStrip;

    public static Robot robot;

    public static PDP pdp;

    public static Pincers pincers;

    public static AHRS imu;

    public static AutoChooser autoRotorChooser;

    private Command autoCommand;
    private SendableChooser autoChooser;

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

        /*
        try {
            UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
            camera0.setResolution(640, 480);
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }
        try {
            UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
            camera1.setResolution(640, 480);
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }
*/
        Constants.isTony = pdp.isTony();
    }

    @Override
    public void disabledInit() {
        ledStrip.setStripColor(LEDColors.DISABLED);
    }

    @Override
    public void autonomousInit() {
        autoCommand = new AutoDrive(36, -0.2);
        if (autoCommand!=null) {
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

    public void updateDashboard(){
        driveTrain.updateDashboard();
        gearHandler.updateDashboard();
        shifter.updateDashboard();
        pincers.updateDashboard();
        lights.updateDashboard();
        ledStrip.updateDashboard();
        SmartDashboard.putBoolean("IsTony", Constants.isTony);
        autoRotorChooser.updateDashboard();

        SmartDashboard.putNumber("Yaw", imu.getAngle());

    }

}
