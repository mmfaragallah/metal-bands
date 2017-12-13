package com.metalbands.mahmoudfaragallah.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mahmoud on 07-12-2017.
 */

public class LogUtil {

    private static final boolean enableToast = true;

    /**
     * @param className
     * @param message
     */
    public static void debug(String className, String message) {
        if (ApplicationConstants.DEVELOPMENT_MODE) {
            Log.d(ApplicationConstants.PROJECT_TAG, className + " , " + message);
        }
    }

    /**
     * @param className
     * @param message
     */
    public static void info(String className, String message) {
        if (ApplicationConstants.DEVELOPMENT_MODE) {
            Log.i(ApplicationConstants.PROJECT_TAG, className + " , " + message);
        }
    }

    /**
     * @param className
     * @param message
     */
    public static void warning(String className, String message) {
        if (ApplicationConstants.DEVELOPMENT_MODE) {
            Log.w(ApplicationConstants.PROJECT_TAG, className + " , " + message);
        }
    }

    /**
     * @param className
     * @param message
     */
    public static void error(String className, String message) {
        //if (ApplicationConstants.DEVELOPMENT_MODE) {
        Log.e(ApplicationConstants.PROJECT_TAG, className + " , " + message);
        //}
    }

    /**
     * @param className
     * @param exception
     */
    public static void error(String className, Exception exception) {
        //if (ApplicationConstants.DEVELOPMENT_MODE) {
        error(className, "Exception : " + exception);
        error(className, "StackTrace : " + Log.getStackTraceString(exception));
        error(className, "Cause : " + exception.getCause());
        error(className, "Message : " + exception.getMessage());
        error(className, "exception.toString : " + exception.toString());
        //}
    }

    /**
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (enableToast) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}