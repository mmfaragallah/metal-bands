package com.metalbands.mahmoudfaragallah.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mahmoud on 12-12-2017.
 */
public class SharedPrefUtility {

    //region constants
    private static final String PREFS_NAME = "MetalBandsPrefs";
    //endregion

    //region private members
    private static SharedPrefUtility instance;
    //endregion

    //region public methods
    /**
     * @return
     */
    public static SharedPrefUtility getInstance() {
        if (instance == null) {
            instance = new SharedPrefUtility();
        }
        return instance;
    }

    /**
     * @param context
     * @param key
     * @param value
     */
    public void saveSetting(Context context, String key, Object value) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
    }

    /**
     * @param context
     * @param key
     * @return
     */
    public String getSettingString(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getSettingBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public int getSettingInteger(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public long getSettingLong(Context context, String key, long defaultValue) {

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * @param context
     * @param key
     */
    public void removeSetting(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * @param context
     */
    public void clearAllData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }
    //endregion
}