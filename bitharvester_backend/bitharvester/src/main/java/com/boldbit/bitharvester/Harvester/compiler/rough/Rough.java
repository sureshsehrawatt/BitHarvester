package com.boldbit.bitharvester.Harvester.compiler.rough;
import java.lang.reflect.Field;
import java.util.ArrayList;

class Person {
    private String name;
    private int age;
    ArrayList<Integer> arr = new ArrayList<>();
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


public class Rough {
    public static void main(String[] args) {
        int a = 8, b = 8;
        a = a >> 1;
        b = b >>> 1;
        System.out.println(a);
        System.out.println(b);

    }
}
