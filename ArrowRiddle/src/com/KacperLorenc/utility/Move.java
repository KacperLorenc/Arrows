package com.KacperLorenc.utility;

public class Move {
   private int position;
   private char arrow;
   private ArrowArray.Name name;

    public Move(int position, char arrow, ArrowArray.Name name) {
        this.position = position;
        this.arrow = arrow;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public char getArrow() {
        return arrow;
    }

    public ArrowArray.Name getName() {
        return name;
    }
}
