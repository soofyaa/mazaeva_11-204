package ru.itis.animerec.utils.score;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ScoreUtil {
    public static double calculateScore(int all, Map<String, Integer> subTypes, String type) {
        int score = 0;
        int count = 0;

        if (subTypes.containsKey(type)) {
            score += subTypes.get(type);
            count++;
        }

        return count > 0 ? (double) score / all : 0;
    }

    public static <T> double calculateScore(int all, Map<String, Integer> subTypes, List<T> objects, Function<T, String> extractor) {
        int score = 0;
        int count = 0;

        for (T object : objects) {
            String type = extractor.apply(object);
            if (subTypes.containsKey(type)) {
                score += subTypes.get(type);
                count++;
            }
        }

        return count > 0 ? (double) score / (all * count) : 0;
    }
}
