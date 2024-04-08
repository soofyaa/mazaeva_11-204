package ru.itis;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.util.*;

public class App {
    private static Map<Class<?>, Object> singletons = new HashMap<>();

    @SneakyThrows
    public static void dependencyInjectionContainerStart() {
        Reflections reflections = new Reflections("ru.itis", new SubTypesScanner(false));
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);

        for (Class<?> c : allClasses) {
            Scope scopeAnnotation = c.getAnnotation(Scope.class);
            if (scopeAnnotation != null) {
                String scope = scopeAnnotation.value();
                if ("Singleton".equals(scope)) {
                    Object singleton = createSingletonInstance(c);
                    Class<?>[] clazz = c.getInterfaces();
                    for (Class<?> aClass : clazz) {
                        singletons.put(aClass, singleton);
                    }
                    singletons.put(c, singleton);
                }
            }
        }

        for (Class<?> c : allClasses) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(c) == null) {
                    if (field.getType().isPrimitive()) {
                        field.set(c, 0);
                    } else if (!field.getType().isInterface()) {
                        field.set(c, singletons.containsKey(field.getType())
                                ? singletons.get(field.getType())
                                : createSingletonInstance(field.getType()));
                    }
                    else {
                        field.set(c, singletons.containsKey(field.getType())
                                ? singletons.get(field.getType())
                                : createSingletonInstance(reflections.getSubTypesOf(field.getType())
                                    .stream().findFirst().get()));

                    }
                }
            }
        }
    }

    @SneakyThrows
    private static Object createSingletonInstance(Class<?> c){
        return c.getDeclaredConstructor().newInstance();
    }
}