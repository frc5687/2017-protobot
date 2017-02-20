package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.commands.DisableRingLight;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoAlign;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoCrossBaseline;
import org.frc5687.steamworks.protobot.commands.autonomous.AutoDepositGear;
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

    public static Lights lights;

    public static LEDStrip ledStrip;

    public static Robot robot;

    public static PDP pdp;

    public static Pincers pincers;

    public static AHRS imu;

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
        pneumatics = new Pneumatics();
        shifter = new Shifter();
        climber = new Climber();
        lights = new Lights();
        ledStrip = new LEDStrip();
        pincers = new Pincers();

        pdp = new PDP(); // must be initialized after other subsystems
        oi = new OI(); // must be initialized after subsystems

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
        autoChooser = new SendableChooser();
        autoChooser.addObject("Do Nothing", new DisableRingLight());
        autoChooser.addObject("Auto Cross Baseline", new AutoCrossBaseline());
        autoChooser.addDefault("Auto Place Gear Center", new AutoDepositGear(AutoDepositGear.Position.CENTER));
        autoChooser.addObject("Auto Align 60", new AutoAlign(60));
        SmartDashboard.putData("Auto Selector", autoChooser);
    }

    @Override
    public void disabledInit() {
        ledStrip.setStripColor(LEDColors.DISABLED);
    }

    @Override
    public void autonomousInit() {
        ledStrip.setStripColor(LEDColors.AUTONOMOUS);
        autoCommand = (Command)autoChooser.getSelected(); // new AutoDepositGear(AutoDepositGear.Position.CENTER);
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
        driveTrain.updateDashboard();
        shifter.updateDashboard();
        pincers.updateDashboard();
        lights.updateDashboard();
        ledStrip.updateDashboard();
        SmartDashboard.putNumber("Indicator", pdp.getIndicator());
        SmartDashboard.putNumber("Yaw", imu.getAngle());
    }

}
