package com.KacperLorenc.nodes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class IntNode extends Node {
    int value;

    public IntNode(int value, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.value = value;

        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setFill(Color.LIGHTBLUE);

        this.label.setFont(new Font("Arial",30));
        this.label.setText(String.valueOf(this.value));
    }

    public int getValue() {
        return value;
    }
}
