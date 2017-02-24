package org.frc5687.steamworks.protobot.utils;

/**
 * Created by Caleb Marston on 1/13/2017.
 * Based on last years helpers
 */

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.DriverStation;
import org.frc5687.steamworks.protobot.Constants;

    /**
     * A collection of helper/ math methods used throughout the robot code
     */
    public class Helpers{
        /**
         * Logs an action to Drive Station
         * @param message the message to send to Drive Station
         */


        /**
         * Applies a deadband threshold to a given value
         * @param input raw value from joystick
         * @param deadband the deadband threshold
         * @return double the adjusted value
         */
        public static double applyDeadband(double input, double deadband){
            return (Math.abs(input) >= Math.abs(deadband)) ? input : 0;
        }

        /**
         * Applies a transform to the input to provide better sensitivity at low speeds.
         * @param input the raw input value from a joystick
         * @return the adjusted control value
         */
        public static double applySensitivityTransform(double input) {
            // See http://www.chiefdelphi.com/forums/showthread.php?p=921992

            // The transform can only work on values between -1 and 1.
            if (input>1) { return 1; }
            if (input <-1) { return -1; }

            // The sensitivity factor MUST be between 0 and 1!
            double factor = Math.max(Math.min(Constants.Calibration.SENSITIVITY_FACTOR, 1),0);

            return factor*input*input*input + (1-factor)*input;
        }

        /**
         * Checks if a value is within TOLERANCE of another value
         * @param value the value to check
         * @param target the target or desired value
         * @param TOLERANCE the acceptable TOLERANCE around the target
         * @return true if the value is within TOLERANCE of the target value
         */
    }