package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.*;

public class Exercise2 implements Serializable {
    private static final long serialVersionUID = 47L;

    private String favoriteLanguage;
    private int yearsOfExperience;

    public void setFavoriteLanguage(final String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public void setYearsOfExperience(final int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("favoriteLanguage", String.class)
    };

    private void writeObject(final ObjectOutputStream oos) throws IOException {
        final ObjectOutputStream.PutField fields = oos.putFields();
        fields.put("favoriteLanguage", favoriteLanguage);
        fields.put("yearsOfExperience", yearsOfExperience);
        oos.writeFields();
    }

    private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField fields = is.readFields();
        favoriteLanguage = (String) fields.get("favoriteLanguage", null);
        yearsOfExperience = fields.get("yearsOfExperience", 0);
    }

    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        final var object1 = new Exercise2();
        object1.setFavoriteLanguage("Java");
        object1.setYearsOfExperience(10);

        try (final var oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("serFile2")))) {
            oos.writeObject(object1);
        }

        try (final var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("serFile2")))) {
            final var fromSerialize = (Exercise2) ois.readObject();
            System.out.printf("%s %d%n", fromSerialize.favoriteLanguage, fromSerialize.yearsOfExperience);
        }
    }
}

/*
   Which of the following options are TRUE ? (Choose all that apply)

   A. Compilation fails
   B. Exception thrown at runtime
   C. Output is = Java 0
   D. Output is = Java 10
   E. Output is = null 0
   F. Output is = null 10

*/
