package org.frc5687.steamworks.protobot.utils;

/**
 * Created by Ben Bernard on 5/29/2016.
 */
public class Color {
    private int _red;
    private int _green;
    private int _blue;

    public Color (int red, int green, int blue) {
        _red = red;
        _green = green;
        _blue = blue;
    }

    public int getRed() {
        return  _red;
    }

    public int getGreen() {
        return  _green;
    }

    public int getBlue() {
        return  _blue;
    }

    public static final Color RED = new Color(255,0,0);
    public static final Color CRIMSON = new Color(255,20,60);
    public static final Color WHITE = new Color(255,255,255);
    public static final Color DIM_WHITE = new Color(127,127,127);
    public static final Color BLACK = new Color(0,0,0);

    public static final Color MAGENTA = new Color(255,0,255);
    public static final Color VIOLET = new Color(238,130,238);
    public static final Color PURPLE = new Color(128,0,128);

    public static final Color GOLD = new Color(255,215,0);
    public static final Color ORANGE = new Color(255,165,0);

    public static final Color LIME = new Color(0,255,0);
    public static final Color GREEN = new Color(0,128,0);
    public static final Color FOREST_GREEN = new Color(34,139,34);

    public static final Color SKY_BLUE = new Color(135,206,235);
    public static final Color DEEP_SKY_BLUE = new Color(0,191,255);
    public static final Color BLUE = new Color(0,0,255);


    public static final Color YELLOW = new Color(255,255,0);
    //public static Color RED = new Color(255,0,0);

}