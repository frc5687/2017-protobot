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
     * Drive Train ports
     */
    public static class Drive {
        public static final int LEFT_MOTOR_FRONT = 0;
        public static final int LEFT_MOTOR_REAR = 1;
        public static final int RIGHT_MOTOR_FRONT = 2;
        public static final int RIGHT_MOTOR_REAR = 3;

        public static final int PDP_LEFT_MOTOR_FRONT = 14;
        public static final int PDP_LEFT_MOTOR_REAR = 15;
        public static final int PDP_RIGHT_MOTOR_FRONT = 12;
        public static final int PDP_RIGHT_MOTOR_REAR = 13;

        // Encoder channel ports as of 03/02, left reversed with right
        public static final int LEFT_ENCODER_CHANNEL_A = 6;
        public static final int LEFT_ENCODER_CHANNEL_B = 7;
        public static final int RIGHT_ENCODER_CHANNEL_A = 8;
        public static final int RIGHT_ENCODER_CHANNEL_B = 9;
    }

}
