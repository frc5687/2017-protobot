package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class SafeJoystickButton extends Trigger {

    private JoystickButton a, b;

    public SafeJoystickButton(JoystickButton buttonA, JoystickButton buttonB) {
        a = buttonA;
        b = buttonB;
    }

    @Override
    public boolean get() {
        return a.get() && b.get();
    }
}
