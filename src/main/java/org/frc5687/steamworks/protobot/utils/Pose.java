package org.frc5687.steamworks.protobot.utils;

public class Pose {

    protected long _millis;

    public Pose() {
        _millis = System.currentTimeMillis();
    }

    public long getMillis() {
        return _millis;
    }

}
