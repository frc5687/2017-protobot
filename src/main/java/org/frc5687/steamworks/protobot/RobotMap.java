package org.frc5687.steamworks.protobot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static class Drive {
        //  REAR - FRONT - TOP
        // PWM
        public static final int RIGHT_MOTOR_FRONT = 3;
        public static final int RIGHT_MOTOR_REAR = 4;
        public static final int RIGHT_MOTOR_TOP = 5;

        public static final int LEFT_MOTOR_FRONT = 0;
        public static final int LEFT_MOTOR_REAR = 1;
        public static final int LEFT_MOTOR_TOP = 2;

        // PDP ports
        public static final int PDP_RIGHT_MOTOR_FRONT = 15;
        public static final int PDP_RIGHT_MOTOR_TOP = 14;
        public static final int PDP_RIGHT_MOTOR_REAR = 13;

        public static final int PDP_LEFT_MOTOR_FRONT = 3;
        public static final int PDP_LEFT_MOTOR_TOP = 1;
        public static final int PDP_LEFT_MOTOR_REAR = 0;

        // DIO
        public static final int LEFT_ENCODER_CHANNEL_A = 6;
        public static final int LEFT_ENCODER_CHANNEL_B = 7;
        public static final int RIGHT_ENCODER_CHANNEL_A = 8;
        public static final int RIGHT_ENCODER_CHANNEL_B = 9;

        // Analog In
        public static final int RIGHT_IR_SENSOR = 0;
        public static final int CENTER_IR_SENSOR = 3;
        public static final int LEFT_IR_SENSOR = 1;

    }

    public static class Mandibles {

        public static final int MANDIBLES_MOTOR = 6;
        public static final int POTENTIOMETER = 1;
        public static final int PDP_MANDIBLES_MOTOR = 10;
        public static final int MANDIBLES_IR = 2;

    }

    public static class Lights {

        public static final int RED_STRIP = 10; // Note that this is an MXP port (0)
        public static final int GREEN_STRIP = 11;
        public static final int BLUE_STRIP = 13;

        public static final int RINGLIGHT = 18; // These are DIO ports, not PWM ports. DIO ports are not consecutive!

    }

    public static class Shifter {

        public static final int PISTON_EXTENDER = 1;
        public static final int PISTON_RETRACTOR = 0;

    }

    public static class Climber {

        public static final int CLIMBER_MOTORS = 7;
        public static final int PDP_CLIMBER_MOTOR_A = 2;
        public static final int PDP_CLIMBER_MOTOR_B = 12;

    }

    public static class Misc {

        public static final int INDICATOR = 0;

    }

    public static class Dustpan {

        public static final int LIFTER_MOTOR = 8;
        public static final int ROLLER_MOTOR = 9;
        public static final int POTENTIOMETER = 0;
        public static final int PDP_DUSTPAN_LIFTER_MOTOR = 4;
        public static final int PDP_DUSTPAN_ROLLER_MOTOR = 5;
        public static final int IR = 7;

    }

    public static class AutoChooser {

        public final static int POSITION_SWITCH = 4; //4 on rio is 1 on NAVX
        public final static int GEAR_SWITCH = 5;
        public final static int HOPPER_SWITCH = 6;

    }

}
