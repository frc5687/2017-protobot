package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.TimerTask;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Utility class to wrap the interface with the PiTracker
 * Created by Ben Bernard on 6/5/2016.
 */
public class PiTrackerProxy {
    public static final int DEFAULT_PI_PORT = 27002;
    public static final int DEFAULT_ROBORIO_PORT = 27001;
    public static final int DEFAULT_PERIOD = 10;

    DatagramSocket outgoingSocket;
    private Thread listenerThread;



    private int _period = DEFAULT_PERIOD;
    private int _piPort = DEFAULT_PI_PORT;
    private int _roboRioPort = DEFAULT_ROBORIO_PORT;

    private Frame _latestFrame;
    // private OutliersPose trackingPose;

    private long trackingMillis = System.currentTimeMillis();

    private PiListener _piListener;
    private Timer _piTimer;

    public PiTrackerProxy(int period) {
        _period = period;
        try {
            outgoingSocket = new DatagramSocket();
        } catch (IOException ioe) {
            outgoingSocket = null;
        }

        _piListener = new PiListener(this, _roboRioPort);
        listenerThread = new Thread(_piListener);
        listenerThread.start();

        _piTimer = new Timer();

        _piTimer.schedule(new PiTrackerTimerTask(this), _period, _period);
    }


    synchronized protected void collect() {
        long rioMillis = System.currentTimeMillis();

        // Send the heartbeat to the pi
        if (_piListener!=null) {
            InetAddress piAddress = _piListener.getPiAddress();
            if (piAddress!=null) {
                StringBuilder buffer = new StringBuilder();
                buffer.append(Long.toString(rioMillis));
                buffer.append(";");
                buffer.append(Boolean.toString(lights.getRingLight()));
                buffer.append(";");

                SmartDashboard.putString("PiTrackerProxy/PacketSent", buffer.toString());
                byte[] sendData = new byte[1024];
                sendData = buffer.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, buffer.length(), piAddress, _piPort);
                try {
                    outgoingSocket.send(sendPacket);
                } catch (IOException ioe) {
                };
            }
        }

        // latestFrame = _piListener==null?null:_piListener.getLatestFrame();
        // trackingPose = latestFrame==null?null: (OutliersPose)poseTracker.getRaw(latestFrame.adjustedMillis);
    }

    synchronized public void updateDashboard() {
        Frame frame = getLatestFrame();
        SmartDashboard.putNumber("PiTrackerProxy/pulse", System.currentTimeMillis());
        if (frame!=null) {
            SmartDashboard.putNumber("PiTrackerProxy/millis", frame.getMillis());
            SmartDashboard.putBoolean("PiTrackerProxy/sighted", frame.isSighted());
            SmartDashboard.putNumber("PiTrackerProxy/offsetAngle", frame.getOffsetAngle());
            SmartDashboard.putNumber("PiTrackerProxy/distance", frame.getDistance());
        }
    }

    protected synchronized void setLatestFrame(Frame frame) {
        _latestFrame = frame;
    }

    public synchronized Frame getLatestFrame() {
        return _latestFrame;
    }



    public class Frame {
        private long _millis;
        private boolean _isSighted;
        private double _offsetAngle;
        private double _distance;

        public Frame(String packet) {
            String[] a = packet.split(";");
            _millis = Long.parseLong(a[0]);
            _isSighted = Boolean.parseBoolean(a[1]);
            _offsetAngle = Double.parseDouble(a[2]);
            _distance = Double.parseDouble(a[3]);
            // long currentMillis = System.currentTimeMillis();
            // adjustedMillis = millis + ((currentMillis - millis) / 2);
        }

        public long getMillis() { return _millis; }
        public boolean isSighted() { return _isSighted; }
        public double getOffsetAngle() { return _offsetAngle; }
        public double getDistance() { return _distance; }
    }

    protected class PiTrackerTimerTask extends TimerTask {
        private PiTrackerProxy _piTracker;

        protected PiTrackerTimerTask(PiTrackerProxy piTracker) {
            _piTracker = piTracker;
        }

        @Override
        public void run() {
            _piTracker.collect();
        }
    }

    protected class PiListener implements Runnable {
        private PiTrackerProxy _tracker;
        private InetAddress _piAddress = null;
        private int _roboRioPort;

        protected PiListener(PiTrackerProxy tracker, int roboRioPort) {
            _roboRioPort = roboRioPort;
            DriverStation.reportError("Starting piListener", false);
            _tracker = tracker;
        }

        @Override
        public void run() {
            DriverStation.reportError("piListener run " + Integer.toString(_roboRioPort), false);
            DatagramSocket incomingSocket;
            byte[] receiveData = new byte[1024];
            try {
                incomingSocket = new DatagramSocket(_roboRioPort);
                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    // DriverStation.reportError("piListener waiting for packet ", false);
                    incomingSocket.receive(receivePacket);
                    // DriverStation.reportError("piListener got packet ", false);
                    if (receivePacket == null) {
                        // DriverStation.reportError("Pi listener received empty packet from " + _piAddress.toString() + ": ", false);
                    } else {
                        synchronized (this) {
                            _piAddress = receivePacket.getAddress();
                        }
                        String raw = new String(receivePacket.getData(), 0, receivePacket.getLength());

                        // DriverStation.reportError("Pi listener received packet from " + _piAddress.toString() + ": " + raw, false);
                        SmartDashboard.putString("PiTrackerProxy/PacketReceived", raw.toString());
                        Frame frame = new Frame(raw);
                        _tracker.setLatestFrame(frame);
                    }
                }

            } catch (IOException ioe) {
                DriverStation.reportError("piListener died 1" + ioe.getMessage(), true);
            } catch (Exception e) {
                DriverStation.reportError("piListener died 2" + e.toString(), true);
            }

        }

        public synchronized InetAddress getPiAddress() {
            return _piAddress;
        }

    }
}
