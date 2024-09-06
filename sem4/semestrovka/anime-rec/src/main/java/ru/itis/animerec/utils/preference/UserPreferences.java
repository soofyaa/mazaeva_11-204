package ru.itis.animerec.utils.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPreferences {
    private Preferences type;
    private Preferences animeStatus;
    private Preferences studio;
    private Preferences creator;
    private Preferences year;
    private Preferences genres;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Preferences {
        private int all;
        private Map<String, Integer> subTypes;
    }
}