package com.dobrihlopez.securedcommunicationexample.presentation;

public class Test {
    class A {
        String str = "ab";

        A() {
            this.printSmth();
        }

        void printSmth() {
            System.out.println(str.length());
        }
    }

    class B extends A {
        String str = "abc";

        @Override
        void printSmth() {
            System.out.println(str.length());
        }
    }
}
