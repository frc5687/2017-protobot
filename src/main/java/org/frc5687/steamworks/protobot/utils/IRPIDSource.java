package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import org.frc5687.steamworks.protobot.Constants;

public class IRPIDSource extends AnalogInput {

    public IRPIDSource(int channel) {
        super(channel);
    }

    @Override
    public double pidGet() {
        return Constants.Auto.Drive.IRPID.TRANSFORM_COEFFICIENT * Math.pow(getValue(), Constants.Auto.Drive.IRPID.TRANSFORM_POWER);
    }

}
