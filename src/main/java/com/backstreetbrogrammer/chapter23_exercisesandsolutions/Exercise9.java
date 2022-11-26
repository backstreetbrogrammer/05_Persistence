package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise9 implements Serializable {
    private static final long serialVersionUID = -9142875628329476639L;

    private int quantity = -1;
    private transient Double price = null;
    private static String color;

    public Exercise9() {
        quantity = 5;
        color = "GREEN";
    }

    public static void main(final String[] args) throws Throwable {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile9"))) {
            final var exercise9 = new Exercise9();
            exercise9.quantity = 3;
            exercise9.price = 125.5D;
            exercise9.color = "BLUE";
            oos.writeObject(exercise9);
        }
        new Exercise9();
        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile9"))) {
            final var fromSerialize = (Exercise9) ois.readObject();
            System.out.printf("%d,%f,%s%n", fromSerialize.quantity, fromSerialize.price, fromSerialize.color);
        }
    }

    {
        quantity = 4;
    }
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. 3,null,BLUE
   D. 3,null,GREEN
   E. 5,125.5,BLUE
   F. 5,125.5,GREEN
   G. 0,null,null

*/
