package com.KacperLorenc.nodes;



import com.KacperLorenc.game.Game;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//this node lets one check the answer

public class CheckNode extends Node {


    public CheckNode(double x, double y, double width, double height, Game game) {
        super(x, y, width, height);

        this.rectangle.setFill(Color.LIGHTBLUE);
        this.rectangle.setStroke(Color.BLACK);
        this.rectangle.setOnMouseClicked(eventHandler-> game.handleCheckWin());

        this.label.setFont(new Font("Arial",20));
        this.label.setText("Check");
        this.label.setOnMouseClicked(eventHandler-> game.handleCheckWin());

    }

}
