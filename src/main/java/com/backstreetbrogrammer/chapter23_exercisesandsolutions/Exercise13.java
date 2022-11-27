package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;
import java.util.List;

public class Exercise13 implements Serializable {
    private static final long serialVersionUID = 71L;

    transient int age = 13;
    String name;

    @Override
    public String toString() {
        return String.format("%s:%d,", name, age);
    }

    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        final var obj1 = new Exercise13();
        obj1.name = "John";
        obj1.age = 10;

        final var obj2 = new Exercise13();
        obj2.name = "Peter";
        obj2.age = 20;

        final var objects = List.of(obj1, obj2);
        writeAndRead(objects);
    }

    private static void writeAndRead(final List<Exercise13> objects) throws IOException, ClassNotFoundException {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile13"))) {
            oos.writeObject(objects);
        }
        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile13"))) {
            final var fromSerializeObjects = (List<Exercise13>) ois.readObject();
            fromSerializeObjects.forEach(System.out::print);
        }
    }
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. John:0,Peter:0,
   D. John:10,Peter:0,
   E. John:10,Peter:20,
   F. John:0,Peter:20,
   G. Code compiles and runs correctly but no output

*/
