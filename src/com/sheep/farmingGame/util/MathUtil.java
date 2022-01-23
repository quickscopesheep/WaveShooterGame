package com.sheep.farmingGame.util;

public class MathUtil {
    public static float NormalizeX(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return x/mag;
    }

    public static float NormalizeY(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return y/mag;
    }

    public static float Distance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }
}
