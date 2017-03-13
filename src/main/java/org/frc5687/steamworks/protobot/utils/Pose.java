package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pose {

    protected long _millis;

    public Pose() {
        _millis = System.currentTimeMillis();
    }

    public long getMillis() {
        return _millis;
    }

    public void updateDashboard(String prefix) {
        SmartDashboard.putNumber(prefix + "/millis", _millis);
    }

}
