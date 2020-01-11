package com.KacperLorenc.nodes;

public class CustomNode extends IntNode{

    public CustomNode(double x, double y, double width, double height) {
        super(0, x, y, width, height);
        this.rectangle.setOnMouseClicked(eventHandler -> changeValue());
        this.label.setOnMouseClicked(eventHandler -> changeValue());
    }
    private void changeValue(){
        if(this.value == 12)
            this.value = 0;
        else
            this.value++;

        updateLabel();
    }
    private void updateLabel(){
        this.label.setText(String.valueOf(this.value));
    }

}
