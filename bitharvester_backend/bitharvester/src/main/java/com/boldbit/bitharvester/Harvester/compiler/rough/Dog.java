package com.boldbit.bitharvester.Harvester.compiler.rough;

import java.util.*;
import java.util.ArrayList;
import static java.lang.Math.*;

public final class Dog extends Animal {

    static int a;
    int b = a;
    String parentClassName = "Animal";
    public static final String className = "Dog";

    Dog() {
        a = 10;
        speak(5);
    }

    public static void speak(int times) {
        do {
            System.out.println("Barking...");
        } while (times > 2);
        if (a < 20) {
            a = 20;
        }
    }

    // TODO: Some Comment
    void work(String task) {
        try {
            int taskSize = task.length();
            int[] subTasks = new int[taskSize];
            for (int i : subTasks) {
                System.out.println("Working");
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Animal {

}