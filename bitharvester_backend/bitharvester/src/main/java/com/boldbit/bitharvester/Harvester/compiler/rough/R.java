package com.boldbit.bitharvester.Harvester.compiler.rough;
import java.lang.reflect.Field;

class Person {
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


public class R {
    public static void main(String[] args) {
        // Get the Class object for Person
        Class<Person> personClass = Person.class;
        
        // Get all the declared fields of the Person class
        Field[] fields = personClass.getDeclaredFields();
        
        // Print the names of the fields
        System.out.println("Fields of Person class:");
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
