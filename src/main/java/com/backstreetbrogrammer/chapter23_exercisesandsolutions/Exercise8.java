package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise8 implements Serializable {
    private static final long serialVersionUID = -8242844986472977959L;

    private String name = "CocaCola";
    private transient int sugar = 5;
    private OtherClass8 otherClass8;

    public Exercise8() {
        super();
        this.name = "Pepsi";
        this.otherClass8 = new OtherClass8();
        sugar = 8;
    }

    {
        name = "Fanta";
    }

    public static void main(final String[] args) throws Throwable {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile8"))) {
            final var exercise8 = new Exercise8();
            exercise8.name = "Sprite";
            exercise8.sugar = 4;
            oos.writeObject(exercise8);
        }

        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile8"))) {
            final var fromSerialize = (Exercise8) ois.readObject();
            System.out.printf("%s,%d%n", fromSerialize.name, fromSerialize.sugar);
        }
    }

}

class OtherClass8 {
    boolean isSweet = true;
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. Pepsi,5
   D. Sprite,0
   E. Fanta,5
   F. Fanta,8
   G. Sprite,-1

*/
