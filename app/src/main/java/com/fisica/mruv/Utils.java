package com.fisica.mruv;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Utils {
    /**
     * Vibra constantemente durante un periodo de tiempo especifico
     *
     * @param context     contexto de la activity desde la cual se utiliza el vibrador
     * @param miliseconds Cantidad en milisegundos que el dispositivo va a vibrar
     */
    public static void vibrate(Context context, long miliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot(miliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
            else
                vibrator.vibrate(miliseconds);
        }
    }
}
