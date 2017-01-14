package org.frc5687.steamworks.protobot;

import org.frc5687.steamworks.protobot.Utils.Gamepad;

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
    public class Encoders {
        public class Defaults {
            public static final boolean REVERSED = false;
            public static final int SAMPLES_TO_AVERAGE = 20;
            public static final int PULSES_PER_ROTATION = 1440;
            public static final double WHEEL_DIAMETER = 6;
            public static final double INCHES_PER_ROTATION = Math.PI * WHEEL_DIAMETER;
            public static final double SCALAR_RATIO = 8;
            public static final double INCHES_PER_PULSE = INCHES_PER_ROTATION*SCALAR_RATIO/ PULSES_PER_ROTATION ;
            public static final double MAX_PERIOD = 5;
        }

        public class RightDrive {
            public static final boolean REVERSED = Defaults.REVERSED;
            public static final double INCHES_PER_PULSE = Defaults.INCHES_PER_PULSE;
        }

        public class LeftDrive {
            public static final boolean REVERSED = true;
            public static final double INCHES_PER_PULSE = Defaults.INCHES_PER_PULSE;
        }
    }

}
