package com.KacperLorenc.nodes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class Node extends StackPane {
    Rectangle rectangle;
    Label label;

    public Node(double x, double y, double width, double height){
        rectangle = new Rectangle(width,height);
        label = new Label();
        setTranslateX(x);
        setTranslateY(y);
        getChildren().addAll(rectangle,label);
    }


}
