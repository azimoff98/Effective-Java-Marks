package com.company.chapter2.item10;

import java.util.Collections;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        float f = -0.0f;
        float f1 = 0.0f;

        System.out.println(f == f1);  //true
        System.out.println(Float.compare(f, f1)); //false

        PhoneNumber phoneNumber = new PhoneNumber(994, 50, 452);
        PhoneNumber phoneNumber1 = new PhoneNumber(994, 50, 452);
        System.out.println(phoneNumber.hashCode());
        System.out.println(phoneNumber1.hashCode());

        Map<PhoneNumber, String> map = Collections.singletonMap(phoneNumber, "first");

        System.out.println(
                map.get(new PhoneNumber(994,50,452))
        );

    }
}
