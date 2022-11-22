package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise5 extends OtherClass5 implements Serializable {
    private static final long serialVersionUID = -43L;

    Exercise5() {
        System.out.print("exercise5 ");
    }

    public static void main(final String[] args) {
        final var exercise5Object = new Exercise5();
        try {
            final var fos = new FileOutputStream("serFile5");
            final var oos = new ObjectOutputStream(fos);
            oos.writeObject(exercise5Object);
            oos.close();

            final var fis = new FileInputStream("serFile5");
            final var ois = new ObjectInputStream(fis);
            final var fromSerialize = (Exercise5) ois.readObject();
            ois.close();
        } catch (final Exception ignored) {
        }
    }
}

class OtherClass5 {
    OtherClass5() {
        System.out.print("otherClass5 ");
    }
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. otherClass5 exercise5
   D. otherClass5 exercise5 exercise5
   E. otherClass5 exercise5 otherClass5
   F. otherClass5 exercise5 otherClass5 exercise5

*/
