import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Main {
/*
Переписать аннотацию @CheckValue так, чтобы она принимала одну строчку.

Переписать CheckValueAnnotationValidator так,
чтобы он проверял соответствие поля, помеченного аннотацией, регулярке, которая передана как значение поля.

Если в классе несколько полей помеченных аннотацией, валидатор должен проверить все.
 */

    public static void main(String[] args) throws Exception {

        Animal animal = new Animal(12,"Boris","White");
        System.out.println(CheckValueAnnotationValidator
                .isValid(animal));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Animal {

        private int age;

        @CheckValue(range = "Boris")
        private String name;

        @CheckValue(range = "White")
        private String color;
    }
}