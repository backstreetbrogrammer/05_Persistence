package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise7 implements Serializable {
    private static final long serialVersionUID = -42L;

    private transient String name = "John";
    private Integer age = 20;

    {
        name = "Peter";
        age = 15;
    }

    public Exercise7() {
        this.name = "David";
        this.age = 31;
    }

    public static void main(final String[] args) throws Throwable {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile7"))) {
            final var exercise7 = new Exercise7();
            exercise7.age = 40;
            oos.writeObject(exercise7);
        }

        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile7"))) {
            final var fromSerialize = (Exercise7) ois.readObject();
            System.out.printf("%s,%d%n", fromSerialize.name, fromSerialize.age);
        }
    }
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. John,20
   D. David,31
   E. null,31
   F. Peter,15
   G. null,40
   H. null,null

*/
