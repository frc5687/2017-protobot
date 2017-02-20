package org.frc5687.steamworks.protobot;

/**
 * Created by Ben Bernard on 1/12/2017.
 */

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    /**
     * Drive Train ports  8=pincers pdp 4
     */
    public static class Drive {

        public static final int RIGHT_MOTOR_FRONT = 3;
        public static final int RIGHT_MOTOR_REAR = 4;
        public static final int RIGHT_MOTOR_TOP = 5;

        public static final int LEFT_MOTOR_FRONT = 0;
        public static final int LEFT_MOTOR_REAR = 1;
        public static final int LEFT_MOTOR_TOP = 2;

        public static final int PDP_RIGHT_MOTOR_FRONT = 15;
        public static final int PDP_RIGHT_MOTOR_TOP = 14;
        public static final int PDP_RIGHT_MOTOR_REAR = 13;

        public static final int PDP_LEFT_MOTOR_FRONT = 3;
        public static final int PDP_LEFT_MOTOR_TOP = 1;
        public static final int PDP_LEFT_MOTOR_REAR = 0;

        // Encoder channel ports as of 03/02, left reversed with right
        public static final int LEFT_ENCODER_CHANNEL_A = 6;
        public static final int LEFT_ENCODER_CHANNEL_B = 7;
        public static final int RIGHT_ENCODER_CHANNEL_A = 8;
        public static final int RIGHT_ENCODER_CHANNEL_B = 9;
        public static final int IR_DRIVE_SENSOR = 1;
    }

    /**
     * Gear Handler ports
     */
    public static class GearHandler {
        public static final int GEAR_MOTOR = 6;
        public static final int MAX_EXTENSION_HALL = 2; //TODO Change to ports on robot
        public static final int MIN_EXTENSION_HALL = 1;
        public static final int GEAR_POTENTIOMETER = 3;
        public static final int PDP_GEAR_MOTOR = 2;
     }

    /**
     * Pneumatics ports
     */
    public static class Pneumatics {
        public static final int PISTON_EXTENDER = 1;
        public static final int PISTON_RETRACTOR = 0;
    }
    public static class Lights {
        public static final int RED_STRIP = 10; // Note that this is an MXP port (0)
        public static final int GREEN_STRIP = 11;
        public static final int BLUE_STRIP = 13;

        public static final int RINGLIGHT = 18; // These are DIO ports, not PWM ports. DIO ports are not consecutive!


    }

    public static class Shifter {
        public static final int PISTON_EXTENDER = 5;
        public static final int PISTON_RETRACTOR = 4;
    }

    /**
     * Climber ports
     */
    public static class Climber {
        public static final int CLIMBER_MOTOR = 7;
        public static final int PDP_CLIMBER_MOTOR = 12;
    }

    public static class Misc {
        public static final int INDICATOR = 2;
    }

    public static class Pincers {
        public static final int PINCER_MOTOR = 8;
        public static final int POTENTIOMETER = 0;
        public static final int PISTON_RETRACTOR = 2;
        public static final int PISTON_EXTENDER = 3;
    }

    public static class AutoChooser {
        public final static int ROTOR = 4; //4 on rio is 1 on NAVX
    }

}
