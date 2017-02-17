package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.RaisePincers;

/**
 * Created by Caleb on 2/14/2017.
 */

public class Pincers extends Subsystem {

    private VictorSP pincerMotor;
    private DoubleSolenoid piston;
    private AnalogPotentiometer potentiometer;
    public Pincers(){
        pincerMotor = new VictorSP(RobotMap.Pincers.PINCER_MOTOR);
        potentiometer = new AnalogPotentiometer(RobotMap.Pincers.POTENTIOMETER);
        piston = new DoubleSolenoid(RobotMap.Pincers.PISTON_EXTENDER, RobotMap.Pincers.PISTON_RETRACTOR);
    }
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RaisePincers());
    }


    public void setPincerSpeed(double speed){
        pincerMotor.set(speed);
    }

    public void extendPiston(){
        piston.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPiston(){
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    public double getAngle(){
        return potentiometer.get();
    }

    public boolean isLifted(){
        return potentiometer.get() == Constants.Pincers.potentiometerLifted;
    }

    public boolean isOpen() {
        return piston.get() == DoubleSolenoid.Value.kReverse;
    }

    public boolean isClosed() {return piston.get() == DoubleSolenoid.Value.kForward;}


    public boolean isLowered(){return potentiometer.get() == Constants.Pincers.potentiometerLowered;}

    public AnalogPotentiometer getPotentiometer(){
        return potentiometer;
    }

    public void updateDashboard(){
        SmartDashboard.putNumber("Pincer/PotentiometerValue",potentiometer.get());
    }


}
