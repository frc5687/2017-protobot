package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by Ben Bernard on 2/16/2017.
 */
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.RobotMap;

import org.frc5687.steamworks.protobot.utils.*;


/**
 * Subsystem to control lights for vision tracking and shooter aid
 * @author wil
 */
public class LEDStrip extends Subsystem {
    private LEDController redStrip;
    private LEDController greenStrip;
    private LEDController blueStrip;


    public LEDStrip() {
        redStrip = new LEDController(RobotMap.Lights.RED_STRIP);
        greenStrip = new LEDController(RobotMap.Lights.GREEN_STRIP);
        blueStrip = new LEDController(RobotMap.Lights.BLUE_STRIP);
    }

    @Override
    protected void initDefaultCommand() {
        // setDefaultCommand(new PulseLEDStrip(Color.BLACK, Color.ORANGE, 700));
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
        } catch (Exception e) {
            DriverStation.reportError(e.getMessage(), true);
        }
    }
}