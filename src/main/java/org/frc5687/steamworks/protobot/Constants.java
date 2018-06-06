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

    public class Mandibles {

        public static final double OPEN_SPEED_TONY = .85;
        public static final double CLOSE_SPEED_TONY = -.3;
        public static final double CLAMP_SPEED_TONY = -.27;
        public static final double HOLD_OPEN_SPEED_TONY = .15;

        public static final double OPEN_SPEED_RHODY = .85;
        public static final double CLOSE_SPEED_RHODY = -.3;
        public static final double CLAMP_SPEED_RHODY = -.2;
        public static final double HOLD_OPEN_SPEED_RHODY = .15;

        public static final long MIN_OPEN_TIME = 500;
        public static final long MAX_OPEN_TIME = 1000;
        public static final long MIN_CLOSE_TIME = 500;
        public static final long MAX_CLOSE_TIME = 1000;
        public static final double TONY_MAX_POT_LIMIT = 0.5;
        public static final double PROTOBOT_MAX_POT_LIMIT = 0.5;
        public static final double WIGGLE_SPEED_TONY = -0.2;
        public static final double WIGGLE_SPEED_RHODY = -0.3;
        public static final long WIGGLE_OUT_TIME = 30;
        public static final long WIGGLE_IN_TIME = 70;
        public static final long IR_GEAR_DETECTED = 1500;
        public static final double THRESHOLD_OPEN_AMPS = 10.0;
        public static final double THRESHOLD_CLOSE_AMPS = 10.0;
        public static final double RETAIN_SPEED_TONY = -.2;
        public static final double RETAIN_SPEED_RHODY = -.2;

        public static final boolean RHODY_MOTOR_INVERTED = true;
        public static final boolean TONY_MOTOR_INVERTED = true;

    }

    public class DriveTrain {

        public static final boolean LEFT_MOTORS_INVERTED = false;
        public static final boolean RIGHT_MOTORS_INVERTED = true;

        public static final double FULL_FORWARDS_SPEED = 1;
        public static final double FULL_BACKWARDS_SPEED = -1;

        public static final double STRAIGHT_TOLERANCE = 0.2;

        public static final double RIGHT_IR_SENSOR_OFFSET = -2.0;
        public static final double CENTER_IR_SENSOR_OFFSET = 0;
        public static final double LEFT_IR_SENSOR_OFFSET = -2.0;

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
        public static final double ACCELERATION_CAP = 1000 / TIME_OF_ACCEL / CYCLES_PER_SECOND;

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
//            public static final double INCHES_PER_PULSE_TONY = INCHES_PER_ROTATION * SCALAR_RATIO / PULSES_PER_ROTATION;
            public static final double INCHES_PER_PULSE = 0.1076;
            // public static final double INCHES_PER_PULSE_TONY = 0.12371134;
            public static final double MAX_PERIOD = 5;

        }

        public class RightDrive {

            public static final boolean REVERSED = Defaults.REVERSED;
            public static final double INCHES_PER_PULSE_TONY =  0.03609; //Encoders.Defaults.INCHES_PER_PULSE;
            public static final double INCHES_PER_PULSE_RHODY = 0.0406;

        }

        public class LeftDrive {

            public static final boolean REVERSED = Defaults.REVERSED;
            public static final double INCHES_PER_PULSE_TONY = Defaults.INCHES_PER_PULSE;
            public static final double INCHES_PER_PULSE_RHODY = 0.1145;

        }

    }

    public class Climber {
        public static final boolean MOTOR_INVERTED = true;
        public static final double ASCEND_SPEED = 1.0;
        public static final double FUNNEL_RELEASE_SPEED = 1;
        public static final long FUNNEL_RELEASE_TIME = 167;
        public static final double PICKUP_SPEED = 0.5;
        public static final double HAVE_ROPE_AMPS = 10.0;
        public static final double REACHED_TOP_AMPS = 18.0;
        public static final long STARTUP_MILLIS = 500;
    }

    public class OI {

        public static final double AXIS_BUTTON_THRESHHOLD = 0.2;

        public static final long RUMBLE_MILLIS = 250;
        public static final double RUMBLE_INTENSITY = 1.0;
    }

    public class Shifter {
        public static final long STOP_MOTOR_TIME = 60;
        public static final long SHIFT_TIME = 60;

        public static final double SHIFT_UP_THRESHOLD = 50; // in inches per second TODO tune
        public static final double SHIFT_DOWN_THRESHOLD = 40; // in inches per second TODO tune

        public static final long AUTO_WAIT_PERIOD = 500;
        public static final long MANUAL_WAIT_PERIOD = 3000;
    }

    public class Dustpan {
        public static final boolean RHODY_LIFTER_INVERTED = true;
        public static final boolean TONY_LIFTER_INVERTED = true;

        public static final double LOWER_SPEED = 0.7;
        public static final double RAISE_SPEED = -0.7;
        public static final double HOLD_DOWN_SPEED = 0.2;
        public static final double HOLD_UP_SPEED = -0.2;
        public static final double FORWARDS_SPEED = 0.3;
        public static final double LIFTER_OVERRIDE_UP_SPEED = -1.0;
        public static final double LIFTER_OVERRIDE_DOWN_SPEED = 1.0;

        public static final int IR_THRESHOLD = 1500;
        public static final long RAISE_TIME = 1000;
        public static final long LOWER_TIME = 500;
        public static final double HARDSTOP_AMPS = 11.0;
        public static final long OVERRIDE_TIME = 200;
        public static final long EJECT_ROLLERS_DELAY = 100;
        public static final double COLLECT_SPEED = .75;
        public static final double ROLLER_HOLD_SPEED = 0.1;
        public static final double EJECT_SPEED = -0.3;

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

            public static final double STRAIGHT_ANGLE = 0.001;

            public static final double TRAVERSE_NEUTRAL_ZONE_FROM_SIDE_DISTANCE = 240; //julian updated
            public static final double TRAVERSE_NEUTRAL_ZONE_FROM_WALL_DISTANCE = 200;
            public static final double TRAVERSE_NEUTRAL_ZONE_FROM_CENTER_DISTANCE = 200;

            public static final double CROSS_BASELINE_DISTANCE = 150;
            public static final double RETREAT_DISTANCE = 48;
            public static final double DEPOSIT_GEAR_IR_VOLTAGE = 1.155;
            public static final double DEPOSIT_GEAR_IR_DISTANCE = 9.0; // inches
            public static final double DEPOSIT_GEAR_NEAR_INITIAL_DISTANCE = 24;
            public static final double DEPOSIT_GEAR_NEAR_ANGLE = 45;
            public static final double DEPOSIT_GEAR_NEAR_DIAGONAL_DISTANCE = 59;
            public static final double ESCAPE_CENTER_ANGLE_RIGHT = 80;
            public static final double ESCAPE_CENTER_ANGLE_LEFT = -80;
            public static final double ESCAPE_CENTER_DISTANCE = 84; // TODO correct value

            public static final double DEPOSIT_GEAR_SIDE_BEFORE_ARC = 54;
            public static final double DEPOSIT_GEAR_FAR_INITIAL_DISTANCE = 89;
            public static final double DEPOSIT_GEAR_VISION_INITIAL_DISTANCE = 73;
            public static final double DEPOSIT_GEAR_FAR_ANGLE = 60;

            public static final long MANDIBLE_HOLD_TIME = 2000;
            public static final long PAUSE_AT_SPRING_TIME = 250;

            public static final double ABORT_APPROACH_THRESHOLD = 12;
            public static final long ABORT_APPROACH_TIMOUT = 5000;
        }

        public class Align {

            public static final double SPEED = 0.6;

            public static final double kP = 0.04;
            public static final double kI = 0.006;
            public static final double kD = 0.09;
            public static final double TOLERANCE = .5;
            public static final double MAX_OUTPUT = 0;
            /*
             *time the angle must be on target for to be considered steady
             */
            public static final double STEADY_TIME = 100;

        }

        public class Drive {

            public static final double SPEED = 1.0;

            public static final long STEADY_TIME = 100;
            public static final long ALIGN_STEADY_TIME = 100;

            public class IRPID {
                public static final double kP = 0.05;
                public static final double kI = 0.00;
                public static final double kD = 0.03;
                public static final double TOLERANCE = .5;

                /**
                 * a in the voltage-to-distance equation distance = a * voltage ^ b
                 */
                public static final double TRANSFORM_COEFFICIENT = 27.385;
                /**
                 * b in the voltage-to-distance equation distance = a * voltage ^ b
                 */
                public static final double TRANSFORM_POWER = -1.203;
            }

            public class EncoderPID {
                public static final double kP = 0.05;
                public static final double kI = 0;
                public static final double kD = .02;
                public static final double TOLERANCE = 1;
            }

            public class AnglePID {
                public static final double kP = 0.04;
                public static final double kI = 0.006;
                public static final double kD = 0.09;

                public static final double MAX_DIFFERENCE = 0.2;
                public static final double TOLERANCE = .5;
            }

        }
    }

    public class RotarySwitch {
        public static final double TOLERANCE = 0.06;
    }
}
