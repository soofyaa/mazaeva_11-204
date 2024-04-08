package ru.itis.homework5.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.itis.homework5.annotations.MyCache;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MyCacheAspect {

    private final Map<String, Cache> cacheMap = new HashMap<>();

    @Around("@annotation(myCache)")
    public Object cacheMethodResult(ProceedingJoinPoint joinPoint, MyCache myCache) throws Throwable {
        String methodName = joinPoint.getSignature().toString();
        String value = Arrays.toString(joinPoint.getArgs());

        String key = methodName + value;

        int duration = myCache.duration();

        System.out.println(key);

        if (cacheMap.containsKey(key) ) {
            Cache cache = cacheMap.get(key);
            LocalDateTime expiryTime = cache.getExpiryTime();

            if (expiryTime.isAfter(LocalDateTime.now())) {
                return cache.getResult() + " (cache)";
            } else {
                cacheMap.remove(key);
            }
        }

        Object result = joinPoint.proceed();
        LocalDateTime expiryTime = LocalDateTime.now().plusSeconds(duration);
        cacheMap.put(key, new Cache(result, expiryTime));

        return result;
    }

    @Getter
    @AllArgsConstructor
    private static class Cache {
        private final Object result;
        private final LocalDateTime expiryTime;
    }
}
