package com.backstreetbrogrammer.chapter23_exercisesandsolutions;

import java.io.Serializable;

public class Exercise6 {
}

class Machine {
}

class Keyboard {
}

class Computer extends Machine implements Serializable {
}

class Dell extends Computer {
}

class Lenovo extends Computer {
    final Keyboard keyboard = new Keyboard();
}

/*
   Which instances of class(es) can be serialized ? (Choose all that apply)

   A. Computer
   B. Dell
   C. Lenovo
   D. Keyboard
   E. Machine

*/
