package com.KacperLorenc.game;

import com.KacperLorenc.nodes.CustomArrowNode;
import com.KacperLorenc.nodes.CustomIntNode;
import com.KacperLorenc.nodes.ExitNode;
import com.KacperLorenc.nodes.SaveNode;
import com.KacperLorenc.utility.ArrowArray;
import javafx.scene.Group;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CustomGame extends Game {
    private List<CustomIntNode> customIntNodes;
    private ArrayList<CustomArrowNode> leftArrows;
    private ArrayList<CustomArrowNode> rightArrows;
    private ArrayList<CustomArrowNode> topArrows;
    private ArrayList<CustomArrowNode> bottomArrows;

    private CustomGame(Group root, Arrows arrows, int length) {
        super(root,length,arrows);
        customIntNodes = new ArrayList<>();
    }

    public static CustomGame customGame(Group root, int length, Arrows arrows){

        CustomGame game = new CustomGame(root,arrows,length);
        game.leftArrows = new ArrayList<>();
        game.rightArrows = new ArrayList<>();
        game.topArrows = new ArrayList<>();
        game.bottomArrows = new ArrayList<>();

        game.initNodes();

        return game;
    }

    @Override
    protected void initNodes() {
        double gridWidth = WIDTH / (length + 2);
        double gridHeight = HEIGHT / (length + 2);

        int arrayIteratorX = -1;

        for (int i = 0; i < length + 2; i++) {

            int arrayIteratorY = 0;

            for (int j = 0; j < length + 2; j++) {


                if (i != 0 && i < length + 1 && j != 0 && j < length + 1) { // if node is not on the edges of the grid then it becomes an int node with numerical value

                    CustomIntNode node = new CustomIntNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);
                    customIntNodes.add(node);

                } else { // otherwise arrow node is being generated

                    char arrow = '|';

                    if ((i == length + 1 && j == length + 1) || (i == 0 && j == length + 1)) { // nothing happens on the corners

                    } else if (i == 0 && j == 0) {

                      SaveNode saveNode = new SaveNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(saveNode);

                    } else if (i == length + 1 && j == 0) {
                        ExitNode exitNode = new ExitNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(exitNode);
                    }

                    else if (i == 0) { // arrows on the left side of the array

                        CustomArrowNode node = new CustomArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.LEFT, j - 1, this.length,this);
                        root.getChildren().add(node);
                        leftArrows.add(node);

                    } else if (i == length + 1) { //arrows on the right side of the array

                        CustomArrowNode node = new CustomArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.RIGHT, j - 1, this.length,this);
                        root.getChildren().add(node);
                        rightArrows.add(node);

                    } else if (j == 0) { //arrows on the upper side of of the array

                        CustomArrowNode node = new CustomArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.UP, i - 1, this.length,this);
                        root.getChildren().add(node);
                        topArrows.add(node);

                    } else { //arrows on the bottom side of the array

                        CustomArrowNode node = new CustomArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.DOWN, i - 1, this.length,this);
                        root.getChildren().add(node);
                        bottomArrows.add(node);
                    }

                }

            }
            arrayIteratorX++;
        }
    }

    @Override
    protected void saveTextToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(this.length + "\n");

            //Arrow Arrays - the user saves chosen values of Arrow Nodes as a solution to the riddle,
            // so those values will be read to arrow arrays

            for (CustomArrowNode arrow : leftArrows){
                writer.write(arrow.getValue());
                writer.write("\n");
            }
            for (CustomArrowNode arrow : topArrows){
                writer.write(arrow.getValue());
                writer.write("\n");
            }
            for (CustomArrowNode arrow : rightArrows){
                writer.write(arrow.getValue());
                writer.write("\n");
            }
            for (CustomArrowNode arrow : bottomArrows){
                writer.write(arrow.getValue());
                writer.write("\n");
            }


            //Int Nodes
            for (CustomIntNode node : customIntNodes){
                writer.write(node.getValue() + "\n");
            }

            //Arrow Nodes
            for (int i = 0; i < this.length; i++) {
                writer.write(generateArrow() + "\n");
            }
            for (int i = 0; i < this.length; i++) {
                writer.write(generateArrow() + "\n");
            }
            for (int i = 0; i < this.length; i++) {
                writer.write(generateArrow() + "\n");
            }
            for (int i = 0; i < this.length; i++) {
                writer.write(generateArrow() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
