package org.frc5687.steamworks.protobot.utils;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class SafeButton extends Button {

    private Trigger a, b;

    public SafeButton(Trigger buttonA, Trigger buttonB) {
        a = buttonA;
        b = buttonB;
    }

    @Override
    public boolean get() {
        return a.get() && b.get();
    }
}
