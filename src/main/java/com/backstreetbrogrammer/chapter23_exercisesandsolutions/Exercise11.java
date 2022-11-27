package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.ObjectStreamField;
import java.io.Serializable;

public class Exercise11 implements Serializable {
    private static final long serialVersionUID = 87L;

    private Double price;
    private Integer numOfItems;
    private Float ratings;

    private final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("price", Double.class)
    };
}

/*
   I want to serialize Exercise11 class objects BUT only want "price" field to be saved.
   What changes, if any, are required to the Exercise11 class for this to occur ? (Choose all that apply)

   A. Mark "numOfItems" and "ratings" fields as transient
   B. Remove the final modifier from "serialPersistentFields" variable
   C. Remove the "serialPersistentFields" variable
   D. Add a missing modifier to the "serialPersistentFields" variable
   E. No changes are required
   F. None of the above

*/
