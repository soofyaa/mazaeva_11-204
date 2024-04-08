package ru.itis;

public class Main {

    private static Dog dog1;
    private static Dog dog2;

    public static void main(String[] args) {

        App.dependencyInjectionContainerStart();
        System.out.println("Equality check: " + dog1.equals(dog2));

    }
}

