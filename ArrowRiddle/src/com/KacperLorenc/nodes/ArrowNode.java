package com.KacperLorenc.nodes;

import com.KacperLorenc.utility.ArrowArray;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ArrowNode extends Node {
    private char value;
    private ArrowArray.Name name;
    private int index;
    private int length;

    public ArrowNode(char value, double x, double y, double width, double height, ArrowArray.Name name, int index, int length) {
        super(x, y, width, height);

        this.value = value;
        this.name = name;
        this.index = index;
        this.length= length;

        this.rectangle.setFill(Color.CORNFLOWERBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(eventHandler -> changeValue());

        this.label.setFont(new Font("Arial", 30));
        this.label.setOnMouseClicked(eventHandler -> changeValue());

        changeValue(); // to eliminate unwanted arrows from critical points
    }

    public void setValue(char value) { //set value and update label
        this.value = value;
        updateLabel();
    }

    private void changeValue() { // change value of a node on click

        if (index == 0 || index == length - 1) { // in critical places only 2 types of arrows are allowed
            switch (this.name) {
                case UP:
                case LEFT:
                    if (index == 0) { // on the top left corner allowed arrows are '|' and '\'
                        if (this.value == 92)
                            this.value = '|';

                        else
                            this.value = 92;

                    } else { // on the top right corner and bottom left corner allowed arrows are '|' and '/'
                        if (this.value == '|')
                            this.value = '/';

                        else
                            this.value = '|';
                    }
                    break;

                case DOWN:
                case RIGHT:
                    if (index == 0) { // on the bottom left corner and top right corner allowed arrows are '|' and '/'
                        if (this.value == '/')
                            this.value = '|';
                        else
                            this.value = '/';
                    } else { // on the bottom right corner allowed arrows are '|' and '\'
                        if (this.value == '|')
                            this.value = 92;
                        else
                            this.value = '|';
                    }
                    break;
            }

        } else {

            if (this.value == '/')
                this.value = '|';

            else if (this.value == '|')
                this.value = 92;

            else if (this.value == 92)
                this.value = '/';
        }

        updateLabel();
    }

    private void updateLabel() { // convert values to unicode arrows that can be displayed on the screen
        switch (this.name) {
            case UP:
                if (this.value == '/')
                    this.label.setText(String.valueOf('\u2B69'));

                else if (this.value == 92)
                    this.label.setText(String.valueOf('\u2B68'));

                else
                    this.label.setText(String.valueOf('\u2B63'));

                break;

            case DOWN:
                if (this.value == '/')
                    this.label.setText(String.valueOf('\u2B67'));

                else if (this.value == 92)
                    this.label.setText(String.valueOf('\u2B66'));

                else
                    this.label.setText(String.valueOf('\u2B61'));

                break;

            case LEFT:
                if (this.value == '/')
                    this.label.setText(String.valueOf('\u2B67'));
                else if (this.value == 92)
                    this.label.setText(String.valueOf('\u2B68'));
                else
                    this.label.setText(String.valueOf('\u2B62'));
                break;
            case RIGHT:
                if (this.value == '/')
                    this.label.setText(String.valueOf('\u2B69'));
                else if (this.value == 92)
                    this.label.setText(String.valueOf('\u2B66'));
                else
                    this.label.setText(String.valueOf('\u2B60'));
                break;
        }

    }

    public char getValue() { //returns value
        return value;
    }
}
