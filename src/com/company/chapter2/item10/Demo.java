package com.company.chapter2.item10;

public class Demo {

    public static void main(String[] args) {
        float f = -0.0f;
        float f1 = 0.0f;

        System.out.println(f == f1);  //true
        System.out.println(Float.compare(f, f1)); //false
    }

}
