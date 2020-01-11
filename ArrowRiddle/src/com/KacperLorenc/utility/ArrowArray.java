package com.KacperLorenc.utility;

import java.util.Random;

public class ArrowArray {
    private char[] tab;
    private int length;
    private Name name;

    public enum Name {
        UP, DOWN, LEFT, RIGHT;
    }

    public int getLength() {
        return length;
    }

    public ArrowArray(int length, Name name) {
        this.tab = new char[length];
        this.length = length;
        this.name = name;
    }

    public void initArray() {
        Random random = new Random();
        int r;
        for (int i = 0; i < this.length; i++) {

            //The if statements are here to guarantee
            //that every arrow has an equal chance to get drawn.

            if (i == 0 || i == this.length - 1) {
                r = random.nextInt(2);
            } else {
                r = random.nextInt(3);
            }
            this.putChar(r,i);
        }
    }

    public char getCharAt(int i) {
        return tab[i];
    }
    public void setChar(int i, char c){
        if(i>=0 && i <this.length)
        tab[i] = c;
    }

    private void putChar(int r, int i) {
        //Check if we are putting char on the edge of an array
        //and which array is it

        // '|' - means to add +1 into every cell in the same column if it's in UP or DOWN
        // or add +1 into every cell in the same row if it's in LEFT or RIGHT

        // '/' and '\' - mean to add +1 in every cell that lays at an angle
        if(i == 0 || i==this.length-1) {

            if (this.name == Name.UP) {
                if (i == 0) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = 92; // "\"
                            break;
                    }
                } else if (i == this.length - 1) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = '/';
                            break;
                    }
                }
            } else if (this.name == Name.DOWN) {
                if (i == 0) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = '/';
                            break;
                    }
                } else if (i == this.length - 1) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = 92; // "\"
                            break;
                    }
                }
            } else if (this.name == Name.LEFT) {
                //The indexes of left array will be going from up to down, meaning
                //the highest cell will have index 0 and the lowest - last index.
                if (i == 0) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = 92; // "\"
                            break;
                    }
                } else if (i == this.length - 1) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = '/'; // "\"
                            break;
                    }
                }
            } else if (this.name == Name.RIGHT) {
                //The indexes of right array will have the same
                //indexing pattern as left array.
                if (i == 0) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = '/';
                            break;
                    }
                } else if (i == this.length - 1) {
                    switch (r) {
                        case 0:
                            this.tab[i] = '|';
                            break;
                        case 1:
                            this.tab[i] = 92; // "\"
                            break;
                    }
                }
            }
        }
        else{
            //In any other case arrows are put into the array without any conditions
            switch(r){
                case 0:
                    this.tab[i] = '|';
                    break;
                case 1:
                    this.tab[i] = 92; // "\"
                    break;
                case 2:
                    this.tab[i] = '/';
                    break;
            }
        }
    }
}

