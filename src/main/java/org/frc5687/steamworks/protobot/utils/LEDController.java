package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * Specialized DigitalOutput class to provide broader-range PWM output for LEDs.
 * This class maps the standard 0-255 value range across a duty-cycle range of 0% to 100%
 * Note that standard roboRIO PWM has a duty-cycle range of roughly 0% to 20%
 */
public class LEDController extends DigitalOutput {
    /**
     * The last value set.
     */
    private int _value = 0;

    /**
     * Create an instance of an LED controller given a specific channel.
     *
     * @param channel the DIO channel to use for the digital output. 0-9 are
     *                on-board, 10-25 are on the MXP
     */
    public LEDController(int channel) {
        super(channel);
        super.setPWMRate(12000);
        super.enablePWM((double) _value / 255);
    }

    /**
     * Sets the value of the LED controller
     *
     * @param value The value (0-255) to set.
     */
    public void setRaw(int value) {
        set(value);
    }

    /**
     * Sets the value of the LED controller
     *
     * @param value The value (0-255) to set.
     */
    public void set(int value) {
        _value = value;
        super.set(_value > 0);
        double target = ((double) _value) / 255.0;
        super.updateDutyCycle(target);
    }

    /**
     * Gets the most recent value sent to the LED controller.
     *
     * @return The most recent value sent to the LED controller.
     */
    public int getRaw() {
        return _value;
    }
}