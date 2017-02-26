package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDListener implements PIDOutput {

    private double value;

    public double get() {
        return value;
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            value = output;
        }
    }

}
