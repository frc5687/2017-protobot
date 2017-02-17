package org.frc5687.steamworks.protobot.utils;

/**
 * Created by Ben Bernard on 6/18/2016.
 */
public class Pose {
    protected long _millis;

    public Pose() {
        _millis = System.currentTimeMillis();
    }

    public long getMillis() {
        return _millis;
    }
}
