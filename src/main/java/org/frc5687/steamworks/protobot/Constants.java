package org.frc5687.steamworks.protobot;

/**
 * Created by Admin on 1/16/2017.
 */
public class Constants {

    public class GearHandler {
        public static final double openSpeed = 1;
        public static final double closeSpeed = -.05;

        public class PID {
            public static final double MIN_INPUT = 0.055;
            public static final double MAX_INPUT = 0.221;

            public class Move {
                public static final double kP = 1.5;
                public static final double kI = 0;
                public static final double kD = 0;
                public static final double TOLERANCE = 0.01;
            }

            public class Hold {
                public static final double kP = 0;
                public static final double kI = 0;
                public static final double kD = 0;
                public static final double TOLERANCE = 0.01;
                public static final double MAX_SPEED = 0.5;
            }

        }
    }

    public class Drive {
        public static final boolean LEFT_MOTOR_FRONT_INVERTED = true;
        public static final boolean RIGHT_MOTOR_FRONT_INVERTED = true;
        public static final boolean LEFT_MOTOR_REAR_INVERTED = true;
        public static final boolean RIGHT_MOTOR_REAR_INVERTED = true;

        public static final double FULL_FORWARDS_SPEED = -1;
        public static final double FULL_BACKWARDS_SPEED = 1;
    }


    public class Deadbands {
        /**
         * Deadband threshold for drive joysticks
         */
        public static final double DRIVE_STICK = 0.15;
    }

    public class Limits {
        /***
         * Minimum time (in milliseconds) it should take to go from 0 to 1 (stop to full)
         */
        public static final int CYCLES_PER_SECOND = 50;
        public static final double TIME_OF_ACCEL = 250;

        /***
         * Maximum accelerations per cycle
         */
        public static final double ACCELERATION_CAP = TIME_OF_ACCEL / CYCLES_PER_SECOND * 100;
    }

    public class Calibration {
        /***
         * Controls the sensitivity algorithm.
         * 0 results in a linear control-to-speed relationship, while 1 results in cubed.
         *
         * NEVER SET ABOVE 1 OR BELOW 0
         */
        public static final double SENSITIVITY_FACTOR = .2;
    }

    public class Encoders {

        public class Defaults {
            public static final boolean REVERSED = true; //TODO change to new robot specifications
            public static final int SAMPLES_TO_AVERAGE = 20;
            public static final int PULSES_PER_ROTATION = 1440;
            public static final double WHEEL_DIAMETER = 6;
            public static final double INCHES_PER_ROTATION = Math.PI * WHEEL_DIAMETER;
            public static final double SCALAR_RATIO = 8;
            public static final double INCHES_PER_PULSE = INCHES_PER_ROTATION * SCALAR_RATIO / PULSES_PER_ROTATION;
            public static final double MAX_PERIOD = 5;
        }

        public class RightDrive {
            public static final boolean REVERSED = Defaults.REVERSED;
            public static final double INCHES_PER_PULSE = Encoders.Defaults.INCHES_PER_PULSE;
        }

        public class LeftDrive {
            public static final boolean REVERSED = Defaults.REVERSED;
            public static final double INCHES_PER_PULSE = Defaults.INCHES_PER_PULSE;
        }

        public class Potentiometer{
            public static final double potentiometerMaxLimit = 0.5;
        }

    }

    public class Climber {
        public static final double ASCEND_SPEED = .8;
        public static final double DESCEND_SPEED = -0.1;
    }

    public class OI{
        public static final double triggerThreshhold = 0.5; //TODO find actual when pressed value
    }

}
