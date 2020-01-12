package com.KacperLorenc.nodes;

import com.KacperLorenc.game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//this node will be used to save game

public class SaveNode extends Node {
    public SaveNode(double x, double y, double width, double height, Game game) {

        super(x, y, width, height);
        this.rectangle.setFill(Color.LIGHTBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(eventHandler-> game.saveGame());

        this.label.setFont(new Font("Arial",20));
        this.label.setText("Save");
        this.label.setOnMouseClicked(eventHandler-> game.saveGame());

    }
}
