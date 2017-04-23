package org.frc5687.steamworks.protobot.utils;

/**
 * Created by Baxter on 4/22/2017.
 */
public class OffsetIRPIDSource extends IRPIDSource {

    double offset;

    public OffsetIRPIDSource(int channel, double offset) {
        super(channel);
        this.offset = offset;
    }

    @Override
    public double pidGet() {
        return super.pidGet() + offset;
    }
}
