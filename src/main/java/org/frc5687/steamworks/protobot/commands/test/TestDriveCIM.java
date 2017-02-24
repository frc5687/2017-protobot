package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class TestDriveCIM extends Command {
    private static double kTOLERANCE = 0.1;

    private VictorSP _victor;
    private Encoder _encoder;
    private int _pdpPort;

    private double _runSpeed;
    private int _runMillis;
    private double _targetAmps;
    private int _targetTicks;

    private State _state = State.RUN;
    private long _endMillis;

    private String _description;

    private double _maxAmps = 0;

    public TestDriveCIM(String description, VictorSP victor, Encoder encoder,  int pdpPort, double runSpeed, int runMillis, double targetAmps, int targetTicks) {
        _description = description;

        _victor = victor;
        _encoder = encoder;
        _pdpPort = pdpPort;

        _runSpeed = runSpeed;
        _runMillis = runMillis;
        _targetAmps = targetAmps;
        _targetTicks = targetTicks;
    }

    @Override
    protected void initialize() {
        _encoder.reset();
        _state = State.RUN;
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
    }

    @Override
    protected void execute() {

        switch (_state) {
            case RUN:
                _victor.set(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getCurrent(_pdpPort));
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.DONE;
                }
                break;
        }
    }

    @Override
    protected void end() {
        boolean pass=true;
        if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
            pass = false;
            DriverStation.reportError("Target amperage not reached on " + _description + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
        }
        if (Math.abs((_targetTicks - _encoder.get()) / _targetTicks) > kTOLERANCE) {
            pass = false;
            DriverStation.reportError("Target ticks not reached on " + _description + ".  Expected " + _targetTicks + " but measured " + _encoder.get() + ".", false);
        }
    }

    @Override
    protected boolean isFinished() {
        return _state == State.DONE;
    }


    private enum State {
        RUN,
        DONE;
    }

}
