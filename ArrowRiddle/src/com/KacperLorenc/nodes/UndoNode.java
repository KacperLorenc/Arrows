package com.KacperLorenc.nodes;

import com.KacperLorenc.game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//this node undoes users actions

public class UndoNode extends Node {
    Game game;

    public UndoNode(double x, double y, double width, double height, Game game) {
        super(x, y, width, height);
        this.game = game;

        this.rectangle.setFill(Color.LIGHTGREY);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(event -> {
            game.undoMove();
            setRectangleColor();
        });

        this.label.setFont(new Font("Arial", 20));
        this.label.setText("Undo");
        this.label.setOnMouseClicked(event -> {
            game.undoMove();
            setRectangleColor();
        });
    }

    public void setRectangleColor() {
        if (this.game.playersMoves.isEmpty())
            rectangle.setFill(Color.LIGHTGREY);
        else
            rectangle.setFill(Color.LIGHTBLUE);
    }

}
