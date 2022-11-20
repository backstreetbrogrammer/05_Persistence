package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise1 {
    public static void main(String[] args) {
        final var object1 = new OtherClassExercise1();
        try {
            final var oos = new ObjectOutputStream(new FileOutputStream("serFile"));
            oos.writeObject(object1);
            oos.close();
            System.out.print(++object1.numStatic + " ");

            final var ois = new ObjectInputStream(new FileInputStream("serFile"));
            final var fromSerialize = (OtherClassExercise1) ois.readObject();
            ois.close();
            System.out.println(fromSerialize.numTransient + " " + fromSerialize.numStatic);
        } catch (final Exception e) {
            System.out.println("exception");
        }
    }
}

class OtherClassExercise1 implements Serializable {
    static int numStatic = 3;
    transient int numTransient = 5;
}

/*
   Which of the following options are TRUE ? (Choose all that apply)

   A. Compilation fails
   B. Exception thrown at runtime
   C. Output is 4 0 3
   D. Output is 4 0 4
   E. Output is 4 5 3
   F. Output is 4 5 4
   G. To change the standard deserialization process, we should implement the readObject() in OtherClassExercise1
   H. To change the standard deserialization process, we should implement the defaultReadObject() in OtherClassExercise1

 */