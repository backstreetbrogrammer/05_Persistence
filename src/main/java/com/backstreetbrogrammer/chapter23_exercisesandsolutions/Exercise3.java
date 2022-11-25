package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Exercise3 implements Serializable {
    private static final long serialVersionUID = 63L;

    private final OtherClass3 otherObject = new OtherClass3();

    public static void main(final String[] args) {
        final var exercise3 = new Exercise3();
        exercise3.storeIt(exercise3);
    }

    private void storeIt(final Exercise3 exercise3) {
        try {
            final var oos = new ObjectOutputStream(new FileOutputStream("serFile3"));
            oos.writeObject(exercise3);
            oos.close();
            System.out.println("stored");
        } catch (final Exception e) {
            System.out.println("exception");
        }
    }

}

class OtherClass3 {
}

/*
   What is the result ? (Choose all that apply)

   A. exception
   B. stored
   C. Compilation fails
   D. Exactly one object is serialized
   E. Exactly two objects are serialized

*/
