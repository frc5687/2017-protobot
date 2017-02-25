package org.frc5687.steamworks.protobot;

/**
 * This class includes all of the constant values used by the code
 */
public class Constants {

    public static boolean isTony = true;

    public static double pickConstant(double tonyValue, double protobotValue) {
        return isTony ? tonyValue : protobotValue;
    }

    public static int pickConstant(int tonyValue, int protobotValue) {
        return isTony ? tonyValue : protobotValue;
    }

    public static boolean pickConstant(boolean tonyValue, boolean protobotValue) {
        return isTony ? tonyValue : protobotValue;
    }

    public static long pickConstant(long tonyValue, long protobotValue) {
        return isTony ? tonyValue : protobotValue;
    }

    public class GearHandler {

        public static final double OPEN_SPEED = -.5;
        public static final double CLOSE_SPEED = .1;
        public static final double CLAMP_SPEED = .1;
        public static final long OPEN_TIME = 250;
        public static final long CLOSE_TIME = 250;
        public static final double TONY_MAX_POT_LIMIT = 0.5;
        public static final double PROTOBOT_MAX_POT_LIMIT = 0.5;
        public static final double WIGGLE_SPEED = 0.2;
        public static final long WIGGLE_OUT_TIME = 30;
        public static final long WIGGLE_IN_TIME = 70;

    }

    public class Drive {

        public static final boolean LEFT_MOTORS_INVERTED = true;
        public static final boolean RIGHT_MOTORS_INVERTED = false;

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
//            public static final double INCHES_PER_PULSE = INCHES_PER_ROTATION * SCALAR_RATIO / PULSES_PER_ROTATION;
            public static final double INCHES_PER_PULSE = .0973;
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

    }

    public class Climber {

        public static final boolean MOTOR_INVERTED = true;
        public static final double ASCEND_SPEED = 1;
        public static final double FUNNEL_RELEASE_SPEED = 1;
        public static final long FUNNEL_RELEASE_TIME = 167;

    }

    public class OI {

        public static final double TRIGGER_THRESHHOLD = 0.5; //TODO find actual when pressed value

    }

    public class Shifter {

        public static final long STOP_MOTOR_TIME = 120; //TODO find correct values
        public static final long SHIFT_TIME = 120; //TODO find correct values

    }

    public class Pincers {

        public static final double POTENTIOMETER_LIFTED_TONY = .006;
        public static final double POTENTIOMETER_LIFTED_RHODY = 0.386;
        public static final double POTENTIOMETER_LOWERED_TONY = .270;
        public static final double POTENTIOMETER_LOWERED_RHODY = 0.64;
        public static final double MAX_SPEED = 0.5;
        public static final int IR_THRESHOLD = 2000;

        public class PID {

            public static final double MIN_INPUT = 0;
            public static final double MAX_INPUT = 1;
            public static final double kP = 1.5;
            public static final double kI = 0.15;
            public static final double kD = 0;
            public static final double TOLERANCE = 0.02;

        }

    }

    public class Auto {

        public static final double MIN_IMU_ANGLE = -180;
        public static final double MAX_IMU_ANGLE = 180;

        public class AnglesAndDistances {

            public static final double CROSS_BASELINE_DISTANCE = 100;
            public static final double CROSS_FIELD_DISTANCE = 0;
            public static final double RETREAT_DISTANCE = 18;
            public static final double DEPOSIT_GEAR_IR_DISTANCE = 950;
            public static final double DEPOSIT_GEAR_NEAR_INITIAL_DISTANCE = 24;
            public static final double DEPOSIT_GEAR_NEAR_ANGLE = 45;
            public static final double DEPOSIT_GEAR_NEAR_DIAGONAL_DISTANCE = 59;

            public static final double DEPOSIT_GEAR_FAR_INITIAL_DISTANCE = 93;
            public static final double DEPOSIT_GEAR_FAR_ANGLE = 60;

        }

        public class Align {

            public static final double SPEED = 0.5;

            public static final double kP = 0.05;
            public static final double kI = 0.01;
            public static final double kD = 0.00;
            public static final double TOLERANCE = 1;
            public static final double MAX_OUTPUT = 0;
            /*
             *time the angle must be on target for to be considered steady
             */
            public static final double STEADY_TIME = 100;

        }

        public class Drive {

            public static final double SPEED = 0.7;

            public static final long STEADY_TIME = 200;

            public class IRPID {
                public static final double kP = -0.01;
                public static final double kI = 0;
                public static final double kD = 0;
                public static final double TOLERANCE = 50;
            }

            public class DistancePID {
                public static final double kP = -0.05;
                public static final double kI = 0;
                public static final double kD = 0;
                public static final double TOLERANCE = 1;
            }

            public class AnglePID {
                public static final double kP = 0.05;
                public static final double kI = 0.01;
                public static final double kD = 0.0;

                public static final double MAX_DIFFERENCE = 0.1;
            }

        }

        public class PositionRotor {

            public static final double ZERO_TARGET = 0;
            public static final double ONE_TARGET = 0;
            public static final double TWO_TARGET = 0;
            public static final double THREE_TARGET = 0;
            public static final double FOUR_TARGET = 0;
            public static final double FIVE_TARGET = 0;
            public static final double TOLERANCE = 0;

        }

        public class GearRotor {

            public static final double ZERO_TARGET = 0;
            public static final double ONE_TARGET = 0;
            public static final double TWO_TARGET = 0;
            public static final double THREE_TARGET = 0;
            public static final double TOLERANCE = 0.1;

        }

        public class HopperRotor {

            public static final double ZERO_TARGET = 0;
            public static final double ONE_TARGET = 0;
            public static final double TOLERANCE = 0;

        }

    }

}
