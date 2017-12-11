package com.gooker.gradlenexus.stat;

import android.util.Log;

/**
 * @author mz
 * @date 2017/12/11 15:47
 */

public class AppStatAgent {

    private static final String TAG = AppStatAgent.class.getSimpleName();

    public static void onClick(boolean loged, String content) {
        if (loged) {
            Log.i(TAG, content);
        }
    }

    public static void onClick2(boolean loged, String content) {
        if (loged) {
            Log.i(TAG, content);
        }
    }

    public static void onClick3(boolean loged, String content) {
        if (loged) {
            Log.i(TAG, content);
        }
    }

    public static void onClick4(boolean loged, String content) {
        if (loged) {
            Log.i(TAG, content);
        }
    }
}
