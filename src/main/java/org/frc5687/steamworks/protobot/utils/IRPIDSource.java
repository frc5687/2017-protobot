package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import org.frc5687.steamworks.protobot.Constants;

public class IRPIDSource extends AnalogInput {

    public IRPIDSource(int channel) {
        super(channel);
    }


    public double getRaw() {
        return super.getVoltage();
    }

    public double getDistance() {
        return Constants.Auto.Drive.IRPID.TRANSFORM_COEFFICIENT * Math.pow(getRaw(), Constants.Auto.Drive.IRPID.TRANSFORM_POWER) / 2.54;

    }
    @Override
    public double pidGet() {
        return getDistance();
    }

}
