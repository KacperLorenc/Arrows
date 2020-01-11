package com.KacperLorenc.utility;

public class ArrowArrayBuilder implements javafx.util.Builder {

    private ArrowArray up;
    private ArrowArray down;
    private ArrowArray left;
    private ArrowArray right;

    private int length;

    public ArrowArrayBuilder(ArrowArray up, ArrowArray down, ArrowArray left, ArrowArray right, int length) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.length = length;
    }

    @Override
    public NumbersArray build() {
        this.up.initArray();
        this.down.initArray();
        this.left.initArray();
        this.right.initArray();
        NumbersArray array = new NumbersArray(this.length);
        array.populate(up, down, left, right);

        return array;
    }
}
