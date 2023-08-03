import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//public class CheckValueAnnotationValidator {
//
//    public static boolean isValid(Class clazz, Object object) throws Exception{
//        Field[] fields = clazz.getDeclaredFields();
//        Field field = fields[0];
//        field.setAccessible(true);
//        CheckValue checkValue = field.getAnnotation(CheckValue.class);
//        int[] range = checkValue.ranges();
//        int value = field.getInt(object);
//        return  (value>= range[0] && value<=range[1]);
//    }
//}

public class CheckValueAnnotationValidator {

    public static boolean isValid(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean isValid = true;
        for (Field field : fields) {
            if (field.isAnnotationPresent(CheckValue.class)) {
                field.setAccessible(true);
                CheckValue checkValue = field.getAnnotation(CheckValue.class);
                String value = field.get(object).toString();
                Pattern pattern = Pattern.compile(checkValue.regex());
                Matcher matcher = pattern.matcher(value);
                isValid = isValid && matcher.matches();
            }
        }
        return isValid;
    }
}