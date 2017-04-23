package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * PID source that returns the minimum of multiple ir sensor distances
 */
public class CompoundIRPIDSource implements PIDSource {

    IRPIDSource[] irSensors;


    public CompoundIRPIDSource(int... channels) {
        irSensors = new IRPIDSource[channels.length];
        for(int i = 0; i < channels.length; i ++) {
            irSensors[i] = new IRPIDSource(channels[i]);
        }
    }

    public CompoundIRPIDSource(IRPIDSource... irSensors) {
        this.irSensors = irSensors;
    }

    @Override
    public double pidGet() {
        double lowest = irSensors[0].pidGet();
        for (int i = 1; i < irSensors.length; i ++) {
            double value = irSensors[i].pidGet();
            lowest = Math.min(value, lowest);
        }
        return lowest;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }
}
