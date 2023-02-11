//несоответствие
public class Cat extends Animal{
    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public void swim() {
        throw new RuntimeException("cat cant swim");
    }

    /*
    Мы создали в родительском классе Animal 3 метода: есть, спать и плавать.
    Для утконоса эти методы не вызывают исключение, всё ок.
    Но коты не имеют плавать, поэтому вызывается исключение.
    Получается, класс-наследник нарушает работу программы
    => нарушается принцип L.

    Чтобы исправить, нужно убрать метод swim из родительского класса
    И далее создать отдельно в классе-наследнике (утконос).
     */
}

//соответствие
public class Cat extends Animal {
    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public void eat() {
        super.eat();
    }
}