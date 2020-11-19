package com.company.chapter3.item19;

import java.time.Instant;

public class Demo {
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}

class Super {
    //Broken - constructor invokes an overridable method
    public Super(){
        overrideMe();
    }
    public void overrideMe(){ }
}

class Sub extends Super {
    private final Instant instant;
    Sub(){ instant = Instant.now(); }
    @Override
    public void overrideMe() { System.out.println(instant.toString()); }
}


