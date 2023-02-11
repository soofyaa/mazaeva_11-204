//несоответствие
public class Duckbill extends Animal {
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
        super.swim();
    }
}

//соответствие
public class Duckbill extends Animal {
    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public void eat() {
        super.eat();
    }

    public void swim() {}
}