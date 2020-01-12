package com.KacperLorenc.nodes;

import com.KacperLorenc.game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//this node lets the user exit the game

public class ExitNode extends Node {
    public ExitNode(double x, double y, double width, double height, Game game) {
        super(x, y, width, height);

        this.rectangle.setFill(Color.LIGHTBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(eventHandler-> game.doYouWantToExit());

        this.label.setFont(new Font("Arial",20));
        this.label.setText("Exit");
        this.label.setOnMouseClicked(eventHandler-> game.doYouWantToExit());

    }

}
