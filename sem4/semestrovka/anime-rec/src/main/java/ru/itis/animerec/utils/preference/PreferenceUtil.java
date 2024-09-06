package ru.itis.animerec.utils.preference;

import java.util.HashMap;
import java.util.Map;

public class PreferenceUtil {

    public static UserPreferences.Preferences incrementPreference(UserPreferences.Preferences preference, String... subTypes) {
        if (preference == null) {
            preference = new UserPreferences.Preferences();
        }

        preference.setAll(preference.getAll() + subTypes.length);
        Map<String, Integer> subTypeMap = preference.getSubTypes();
        if (subTypeMap == null) {
            subTypeMap = new HashMap<>();
            preference.setSubTypes(subTypeMap);
        }

        for (String subType : subTypes) {
            subTypeMap.put(subType, subTypeMap.getOrDefault(subType, 0) + 1);
        }

        return preference;
    }

    public static UserPreferences.Preferences decrementPreference(UserPreferences.Preferences preference, String... subTypes) {
        if (preference == null) {
            preference = new UserPreferences.Preferences();
        }

        preference.setAll(preference.getAll() - subTypes.length);
        Map<String, Integer> subTypeMap = preference.getSubTypes();
        if (subTypeMap == null) {
            subTypeMap = new HashMap<>();
            preference.setSubTypes(subTypeMap);
        }

        for (String subType : subTypes) {
            subTypeMap.put(subType, subTypeMap.getOrDefault(subType, 0) - 1);
        }

        return preference;
    }
}
