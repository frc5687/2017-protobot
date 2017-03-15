package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;
import java.util.TimerTask;

public class PoseTracker {

    private static final int DEFAULT_PERIOD = 10;
    private static final int DEFAULT_HISTORY = 5;
    int _nextwrite = 0;
    private boolean _async = false;
    private int _period = 10;
    private int _history = 5;
    private int _bufferSize = (_history * 1000) / _period;
    private Timer _timer;
    private IPoseTrackable _trackable;
    private Pose[] _poses;


    public PoseTracker(IPoseTrackable trackable, boolean async, int period, int history) {
        SmartDashboard.putBoolean("PoseTracker/Mode/Async", async);
        SmartDashboard.putNumber("PoseTracker/Mode/Period", period);
        SmartDashboard.putNumber("PoseTracker/Mode/History", history);
        _trackable = trackable;
        _async = async;
        _period = period;
        _history = history;
        _bufferSize = (_history * 1000) / _period;
        _poses = new Pose[_bufferSize];

        if (async) {
            _timer = new Timer();
            _timer.schedule(new PoseTrackerTask(this), _period, _period);
        }
    }

    public PoseTracker(IPoseTrackable trackable, int period, int history) {
        this(trackable, true, period, history);
    }

    public PoseTracker(IPoseTrackable trackable) {
        this(trackable, true, DEFAULT_PERIOD, DEFAULT_HISTORY);
    }

    public PoseTracker(int period, int history) {
        this(null, false, period, history);
    }

    public PoseTracker() {
        this(null, false, DEFAULT_PERIOD, DEFAULT_HISTORY);
    }

    synchronized public void reset() {
        _nextwrite = 0;
        _poses = new Pose[_bufferSize];
    }

    public void collect(Pose pose) {
        if (_async) {
            throw new RuntimeException("Client code must not call collect on an asynchronous PoseTracker.");
        }
        add(pose);
    }

    synchronized private void collect() {
        if (_async && _trackable == null) {
            throw new RuntimeException("Asynchronous collect called on a PoseTracker instance with no trackable.");
        }
        Pose pose = _trackable.getPose();
        SmartDashboard.putNumber("PoseTracker/PoseCollected", pose.getMillis());

        add(pose);
    }


    synchronized public void add(Pose pose) {
        _poses[_nextwrite] = pose;
        _nextwrite++;
        if (_nextwrite >= _bufferSize) {
            _nextwrite = 0;
        }
    }

    synchronized public Pose get(long millis) {
        Pose afterPose = null;
        int read = _nextwrite - 1;
        while (true) {
            if (read < 0) {
                read = _bufferSize - 1;
            }
            // If there's nothing there, we're out of buffer
            if (_poses[read] == null) {
                SmartDashboard.putNumber("PoseTracker/PoseFound", afterPose.getMillis());
                return afterPose;
            }

            // If the pose time is before the requested time, we'er out of buffer
            if (_poses[read].getMillis() < millis) {
                SmartDashboard.putNumber("PoseTracker/PoseFound", afterPose.getMillis());
                return afterPose;
            }

            // Otherwise grab the pose
            afterPose = _poses[read];

            read--;
        }

    }

    synchronized public Pose getLatestPose() {
        Pose afterPose = null;
        int read = _nextwrite - 1;
        if (read < 0) {
            read = _bufferSize - 1;
        }
        // If there's nothing there, we're out of buffer
        if (_poses[read] == null) {
            return null;
        } else {
            return _poses[read];
        }
    }


    synchronized public void updateDashboard() {
        Pose pose = getLatestPose();
        if (pose!=null) {
            pose.updateDashboard("PoseTracker");
        }
    }

    protected class PoseTrackerTask extends TimerTask {

        private PoseTracker _poseTracker;

        public PoseTrackerTask(PoseTracker tracker) {
            _poseTracker = tracker;
        }

        @Override
        public void run() {
            _poseTracker.collect();
        }
    }


}
