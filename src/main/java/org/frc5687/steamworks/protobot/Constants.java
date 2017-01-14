package org.frc5687.steamworks.protobot;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class Constants {

    public class Deadbands {
        /**
         * Deadband threshold for drive joysticks
         */
        public static final double DRIVE_STICK = 0.1;
    }

    public class Calibration {
        /***
         * Controls the sensitivity algorithm.
         * 0 results in a linear control-to-speed relationship, while 1 results in cubed.
         *
         * NEVER SET ABOVE 1 OR BELOW 0
         */
        public static final double SENSITIVITY_FACTOR = 0.5;
    }


}
