package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise10 extends OtherClass10 implements Serializable {
    private static final long serialVersionUID = 37L;

    {
        name = "Python";
    }

    public Exercise10() {
        name = "JavaScript";
    }

    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        try (final var oos = new ObjectOutputStream(
                new FileOutputStream("serFile10"))) {
            final var exercise10 = new Exercise10();
            exercise10.name = "Golang";
            oos.writeObject(exercise10);
        }

        try (final var ois = new ObjectInputStream(
                new FileInputStream("serFile10"))) {
            final var fromSerialize = (Exercise10) ois.readObject();
            System.out.printf("%s%n", fromSerialize.name);
        }
    }
}

class OtherClass10 {
    protected transient String name;

    public OtherClass10() {
        this.name = "Java";
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

/*
   What is the result ? (Choose all that apply)

   A. Compilation fails
   B. An exception is thrown at runtime
   C. Golang
   D. Java
   E. JavaScript
   F. Python
   G. null

*/
