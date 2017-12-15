package com.metalbands.mahmoudfaragallah.storage_utility.shared_prefs;

/**
 * Created by Mahmoud on 15-12-2017.
 */

public interface SharedPrefUtility {

    /**
     * @param key
     * @param value
     */
    void saveSetting(String key, Object value);

    /**
     * @param key
     * @return
     */
    String getSettingString(String key);

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    boolean getSettingBoolean(String key, boolean defaultValue);

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    int getSettingInteger(String key, int defaultValue);

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    long getSettingLong(String key, long defaultValue);

    /**
     * @param key
     */
    void removeSetting(String key);

    /**
     *
     */
    void clearAllData();
}
