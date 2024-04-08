package ru.itis.homework5.services;

import org.springframework.stereotype.Service;
import ru.itis.homework5.annotations.MyCache;

@Service
public class MyService {

    @MyCache(duration = 10)
    public String reverse(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
}
