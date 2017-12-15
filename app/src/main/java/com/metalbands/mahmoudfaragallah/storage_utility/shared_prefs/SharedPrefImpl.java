package com.metalbands.mahmoudfaragallah.storage_utility.shared_prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mahmoud on 12-12-2017.
 */
public class SharedPrefImpl implements SharedPrefUtility {

    //region constants
    private static final String PREFS_NAME = "MetalBandsPrefs";
    //endregion

    //region private members
    private SharedPreferences settings;
    private static SharedPrefImpl instance;
    //endregion

    //region constructors
    private SharedPrefImpl(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    //endregion

    //region public methods

    /**
     * @return
     */
    public static SharedPrefImpl getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefImpl(context);
        }
        return instance;
    }

    /**
     * @param key
     * @param value
     */
    @Override
    public void saveSetting(String key, Object value) {

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
     * @param key
     * @return
     */
    @Override
    public String getSettingString(String key) {
        return settings.getString(key, null);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public boolean getSettingBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public int getSettingInteger(String key, int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    @Override
    public long getSettingLong(String key, long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    /**
     * @param key
     */
    @Override
    public void removeSetting(String key) {
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     *
     */
    @Override
    public void clearAllData() {
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }
    //endregion
}