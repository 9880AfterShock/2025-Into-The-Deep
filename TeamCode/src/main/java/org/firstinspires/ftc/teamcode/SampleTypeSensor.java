package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import android.graphics.Color;
import android.os.Build;

import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class SampleTypeSensor {
    static Color TeamColor;
    static Color NeutralColor;
    static Color EnemyColor;
    static float Tolerance;
    private static float colorDist(NormalizedRGBA a, Color b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return (float) Math.sqrt(Math.pow(b.red() - a.red,2) + Math.pow(b.green() - a.green,2) + Math.pow(b.blue() - a.blue,2));
        }
        return -3f;
    }
    public static void initTypeSensor(Color teamColor, Color enemyColor, Color neutralColor, float tolerance) {
        TeamColor = teamColor;
        EnemyColor = enemyColor;
        NeutralColor = neutralColor;
        Tolerance = tolerance;
    }
    public static SampleType getSampleType() {
        NormalizedRGBA color = SampleColorSensor.getColor();
        if (abs(colorDist(color,NeutralColor)) <= Tolerance) {
            return SampleType.NEUTRAL;
        } else if (abs(colorDist(color,TeamColor)) <= Tolerance) {
            return SampleType.FRIENDLY;
        } else if (abs(colorDist(color,EnemyColor)) <= Tolerance) {
            return SampleType.OPPONENT;
        } else {
            return SampleType.NONE;
        }
    }
}
