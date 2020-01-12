package com.KacperLorenc.nodes;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

//this node holds a numeric value and can be customized

public class CustomIntNode extends IntNode{

    public CustomIntNode(double x, double y, double width, double height) {
        super(0, x, y, width, height);
        this.rectangle.setOnMouseClicked(this::changeValue);
        this.label.setOnMouseClicked(this::changeValue);
    }
    private void changeValue(MouseEvent event){
        if (event.getButton() == MouseButton.PRIMARY) {
            this.value++;

        } else if (event.getButton() == MouseButton.SECONDARY) {
            if(this.value>0)
                this.value --;
        }
        updateLabel();
    }
    private void updateLabel(){
        this.label.setText(String.valueOf(this.value));
    }

}
