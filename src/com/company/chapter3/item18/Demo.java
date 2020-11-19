package com.company.chapter3.item18;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Demo {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s =  new InstrumentedHashSet<>();
        s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s.getAddCount()); //prints 6 however expected to print 3

        InstrumentedSet<String> s1 = new InstrumentedSet<>(new HashSet<>());

        s1.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
        System.out.println(s1.getAddCount()); //prints 3!
    }
}
