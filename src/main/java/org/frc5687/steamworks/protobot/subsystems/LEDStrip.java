package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.utils.Color;
import org.frc5687.steamworks.protobot.utils.LEDController;

/**
 * Subsystem to control lights for vision tracking and shooter aid
 *
 * @author wil
 */
public class LEDStrip extends Subsystem {

    private LEDController redStrip;
    private LEDController greenStrip;
    private LEDController blueStrip;

    private boolean _climberRunning;
    private boolean _climbing;
    private boolean _atTop;
    private boolean _gearInMandibles;
    private boolean _mandibleOpen;
    private boolean _gimmeGear;
    private boolean _gearInDustpan;
    private boolean _dustpanDeployed;

    public LEDStrip() {
        redStrip = new LEDController(RobotMap.Lights.RED_STRIP);
        greenStrip = new LEDController(RobotMap.Lights.GREEN_STRIP);
        blueStrip = new LEDController(RobotMap.Lights.BLUE_STRIP);
    }

    @Override
    protected void initDefaultCommand() {
    }


    public void poll() {
        setStripColor(pickColor());
    }

    public Color pickColor() {
        if (_climberRunning) { return LEDColors.CLIMBER_RUNNING; }
        if (_climbing) { return LEDColors.CLIMBING; }
        if (_atTop) { return LEDColors.REACHED_TOP; }
        if (_gearInMandibles) { return LEDColors.GEAR_IN_MANDIBLES; }
        if (_mandibleOpen) { return LEDColors.MANDIBLES_OPEN; }
        if (_gimmeGear) { return LEDColors.GIMME_GEAR; }
        if (_gearInDustpan) { return LEDColors.GEAR_IN_DUSTPAN; }
        if (_dustpanDeployed) {return LEDColors.DUSTPAN_DEPLOYED; }
        if (DriverStation.getInstance().isAutonomous()) { return LEDColors.AUTONOMOUS; }
        if (DriverStation.getInstance().isDisabled()) { return LEDColors.DISABLED; }

        return LEDColors.TELEOP;
    }

    public void setStripColor(int red, int green, int blue) {
        redStrip.setRaw(red);
        greenStrip.setRaw(green);
        blueStrip.setRaw(blue);
        updateDashboard();
    }

    public void setStripColor(Color color) {
        redStrip.setRaw(color.getRed());
        greenStrip.setRaw(color.getGreen());
        blueStrip.setRaw(color.getBlue());
        updateDashboard();
    }

    public Color getStripColor() {
        return new Color(redStrip.getRaw(), greenStrip.getRaw(), blueStrip.getRaw());
    }

    public void updateDashboard() {
        try {
            SmartDashboard.putNumber("ledstrip/red", redStrip.getRaw());
            SmartDashboard.putNumber("ledstrip/green", greenStrip.getRaw());
            SmartDashboard.putNumber("ledstrip/blue", blueStrip.getRaw());
            SmartDashboard.putBoolean("ledstrip/dustpanDeployed", _dustpanDeployed);
            SmartDashboard.putBoolean("ledstrip/gearInDustpan", _gearInDustpan);
            SmartDashboard.putBoolean("ledstrip/gearInMandibles", _gearInMandibles);
            SmartDashboard.putBoolean("ledstrip/climberRunning", _climberRunning);
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }
    }

    public void setClimberRunning(boolean climberRunning) {
        _climberRunning = climberRunning;
    }

    public void setClimbing(boolean climbing) {
        _climbing = climbing;;
    }

    public void setAtTop(boolean atTop) {
        _atTop = atTop;
    }

    public void setGearInMandibles(boolean gearInMandibles) {
        _gearInMandibles = gearInMandibles;
    }

    public void setMandiblesOpen(boolean mandiblesOpen) {
        _mandibleOpen = mandiblesOpen;
    }

    public void setGimmeGear(boolean gimmeGear) {
        _gimmeGear = gimmeGear;
    }

    public void setGearInDustpan(boolean gearInDustpan) {
        _gearInDustpan = gearInDustpan;
    }

    public void setDustpanDeployed(boolean dustpanDeployed) {
        _dustpanDeployed = dustpanDeployed;
    }



}