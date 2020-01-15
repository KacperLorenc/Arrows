package com.KacperLorenc.nodes;

import com.KacperLorenc.game.Game;
import com.KacperLorenc.utility.ArrowArray;
import com.KacperLorenc.utility.Move;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//this node hold a char value and displays it as an arrow

public class ArrowNode extends Node {
    private char value;
    private ArrowArray.Name name;
    private int index;
    private int length;
    private Game game;

    //constructor

    public ArrowNode(char value, double x, double y, double width, double height, ArrowArray.Name name, int index, int length, Game game) {
        super(x, y, width, height);

        this.value = value;
        this.name = name;
        this.index = index;
        this.length = length;
        this.game = game;

        this.rectangle.setFill(Color.CORNFLOWERBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(eventHandler -> {
            updateStack();
            updateValue();
            updateUndoNode();
        });

        this.label.setFont(new Font("Arial", 30));
        this.label.setOnMouseClicked(eventHandler -> {
            updateStack();
            updateValue();
            updateUndoNode();
        });

        updateValue(); // to eliminate unwanted arrows from critical points
    }

    //update

    private void updateValue() { // change value of a node on click

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

    public void updateValue(char arrow) {
        this.value = arrow;
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

    private void updateStack() {
        Move move = new Move(this.index, this.value, this.name);
        game.playersMoves.push(move);
    }

    public void updateUndoNode() {
            this.game.undoNode.setRectangleColor();
    }

    //setters and getters

    public char getValue() { //returns value
        return value;
    }

    public void setValue(char value) { //set value and update label
        this.value = value;
        updateLabel();
    }

    public int getIndex() {
        return index;
    }
}
