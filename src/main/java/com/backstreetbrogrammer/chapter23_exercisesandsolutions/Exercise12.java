package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise12 implements Serializable {
    private static final long serialVersionUID = -49L;

    private transient String name = "John";
    private static String birthPlace = "London";
    private transient Integer age;

    final List<Exercise12> colleagues = new ArrayList<>();

    private final Object height = new Object();

    {
        age = 15;
    }

    public Exercise12() {
        name = "Peter";
    }

    @Override
    public String toString() {
        return "Exercise12{" +
                "name='" + name + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", age=" + age +
                ", colleagues=" + colleagues +
                ", height=" + height +
                '}';
    }

    static Exercise12 writeAndRead(final Exercise12 exercise12) throws IOException, ClassNotFoundException {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile12"))) {
            oos.writeObject(exercise12);
        }
        final Exercise12 fromSerialize;
        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile12"))) {
            fromSerialize = (Exercise12) ois.readObject();
        }
        return fromSerialize;
    }

    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        final var exercise12 = new Exercise12();
        final var fromSerialize = writeAndRead(exercise12);
        System.out.println(fromSerialize);
    }
}

/*
   Which of the following fields will be null when we print "fromSerialize" object ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. name
   D. height
   E. colleagues
   F. birthPlace
   G. age

*/
