package com.KacperLorenc.game;

import com.KacperLorenc.mainMenu.Main;
import com.KacperLorenc.nodes.*;
import com.KacperLorenc.utility.ArrowArrayBuilder;
import com.KacperLorenc.utility.ArrowArray;
import com.KacperLorenc.utility.NumbersArray;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Game {
    private Group root;
    private Arrows arrows;
    public int length;


    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;

    private ArrayList<ArrowNode> leftArrows;
    private ArrayList<ArrowNode> rightArrows;
    private ArrayList<ArrowNode> topArrows;
    private ArrayList<ArrowNode> bottomArrows;

    private ArrowArray up;
    private ArrowArray down;
    private ArrowArray left;
    private ArrowArray right;
    private NumbersArray numbersArray;

    private boolean checkWin(ArrowArray up, ArrowArray down, ArrowArray left, ArrowArray right) {

        int i = 0;
        while (i < up.getLength()) {
            if (up.getCharAt(i) != topArrows.get(i).getValue()) {
                return false;
            }
            if (right.getCharAt(i) != rightArrows.get(i).getValue()) {
                return false;
            }
            if (left.getCharAt(i) != leftArrows.get(i).getValue()) {
                return false;
            }
            if (down.getCharAt(i) != bottomArrows.get(i).getValue()) {
                return false;
            }
            i++;
        }
        return true;
    }

    public void handleCheckWin() {
        if (checkWin(up, down, left, right)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You won!");
            alert.setTitle(null);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                exitToMainMenu();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Incorrect answer.");
            alert.setTitle(null);
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    private void buildArrowArrays() {
        ArrowArrayBuilder builder = new ArrowArrayBuilder(up, down, left, right, length);
        numbersArray = builder.build();

    }


    public static Game normalGame(Group root, int length, Arrows arrows) {
        Game game = new Game(root, length, arrows);

        game.initArrowArrays();
        game.buildArrowArrays();
        game.initNodes();
        game.printArraysInConsole();

        return game;
    }

    public static Game customGame(Group root, int length, Arrows arrows){

        Game game = new Game(root,length,arrows);

        return game;
    }

    public static Game loadedGame(Group root, Arrows arrows) { //static fabric method for game loaded from a file
        Game game = new Game(root, arrows);

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(arrows.primaryStage);

        if(selectedFile==null) {
           game=Game.normalGame(root,3,arrows);
           return game;
        }

        loadGame(game, selectedFile);
        game.printArraysInConsole();

        return game;
    }

    private void initArrowArrays() {
        this.up = new ArrowArray(this.length, ArrowArray.Name.UP);
        this.down = new ArrowArray(this.length, ArrowArray.Name.DOWN);
        this.left = new ArrowArray(this.length, ArrowArray.Name.LEFT);
        this.right = new ArrowArray(this.length, ArrowArray.Name.RIGHT);
        this.numbersArray = new NumbersArray(this.length);
    }

    public Game(Group root, int length, Arrows arrows) {
        this.root = root;
        this.length = length;
        this.arrows = arrows;

        this.leftArrows = new ArrayList<>();
        this.rightArrows = new ArrayList<>();
        this.topArrows = new ArrayList<>();
        this.bottomArrows = new ArrayList<>();
    }

    public Game(Group root, Arrows arrows) {
        this(root, 0, arrows);
    }

    private void initNodes() {

        double gridWidth = WIDTH / (length + 2);
        double gridHeight = HEIGHT / (length + 2);

        int arrayIteratorX = -1;

        for (int i = 0; i < length + 2; i++) {

            int arrayIteratorY = 0;  // array iterators are made because i and j values will go over bounds of arrow arrays

            for (int j = 0; j < length + 2; j++) {


                if (i != 0 && i < length + 1 && j != 0 && j < length + 1) { // if node is not on the edges of the grid then it becomes an int node with numerical value

                    IntNode node = new IntNode(numbersArray.getIntAt(arrayIteratorX, arrayIteratorY++), i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);

                } else { // otherwise arrow node is being generated

                    char arrow = generateArrow(); // generate random arrow

                    if ((i == length + 1 && j == length + 1)) { // nothing happens on the corners

                    } else if (i == 0 && j == length + 1) {

                        SaveNode saveNode = new SaveNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(saveNode);

                    } else if (i == length + 1 && j == 0) {
                        ExitNode exitNode = new ExitNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(exitNode);
                    } else if (i == 0 && j == 0) {

                        CheckNode checkNode = new CheckNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(checkNode);

                    } else if (i == 0) { // arrows on the left side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.LEFT, j - 1, this.length);
                        root.getChildren().add(node);
                        leftArrows.add(node);

                    } else if (i == length + 1) { //arrows on the right side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.RIGHT, j - 1, this.length);
                        root.getChildren().add(node);
                        rightArrows.add(node);

                    } else if (j == 0) { //arrows on the upper side of of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.UP, i - 1, this.length);
                        root.getChildren().add(node);
                        topArrows.add(node);

                    } else { //arrows on the bottom side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.DOWN, i - 1, this.length);
                        root.getChildren().add(node);
                        bottomArrows.add(node);

                    }

                }

            }
            arrayIteratorX++;
        }
    }

    private char generateArrow() {
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                return '/';
            case 1:
                return 92;
            default:
                return '|';
        }
    }

    private void printArraysInConsole() {
        System.out.print("  ");
        for (int i = 0; i < length; i++) {
            System.out.print(up.getCharAt(i) + " ");
        }

        for (int i = 0; i < length; i++) {
            System.out.println("");
            System.out.print(left.getCharAt(i) + " ");
            for (int j = 0; j < length; j++) {
                System.out.print(numbersArray.getIntAt(j, i) + " ");
            }
            System.out.print(right.getCharAt(i));
        }
        System.out.println(" ");
        System.out.print("  ");
        for (int i = 0; i < length; i++) {
            System.out.print(down.getCharAt(i) + " ");
        }
    }

    private void exitToMainMenu() {
        root.getScene().getWindow().hide();
        Main main = new Main();
        Stage stage = new Stage();
        try {
            main.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doYouWantToExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to exit to main menu?");
        alert.setTitle(null);

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            exitToMainMenu();
        } else if (option.get() == ButtonType.NO) {

        }
    }

    public void saveGame() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(this.arrows.primaryStage);

        if (file != null) {
            saveTextToFile(file);
        }
    }

    private void saveTextToFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(this.length + "\n");

            //Arrow Arrays
            for (int i = 0; i < this.length ; i++){
                writer.write(this.left.getCharAt(i));
                writer.write("\n");
            }
            for (int i = 0; i < this.length ; i++){
                writer.write(this.up.getCharAt(i));
                writer.write("\n");
            }
            for (int i = 0; i < this.length ; i++){
                writer.write(this.right.getCharAt(i));
                writer.write("\n");
            }
            for (int i = 0; i < this.length ; i++){
                writer.write(this.down.getCharAt(i));
                writer.write("\n");
            }


            //Int Nodes
            for (int i = 0; i < this.length; i++) {
                for (int j = 0; j < this.length; j++) {
                    writer.write(this.numbersArray.getIntAt(i, j) + "\n");
                }
            }

            //Arrow Nodes
            for (ArrowNode n : leftArrows) {
                writer.write(n.getValue() + "\n");
            }
            for (ArrowNode n : topArrows) {
                writer.write(n.getValue() + "\n");
            }
            for (ArrowNode n : rightArrows) {
                writer.write(n.getValue() + "\n");
            }
            for (ArrowNode n : bottomArrows) {
                writer.write(n.getValue() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadGame(Game game, File selectedFile) {
        try (Scanner scanner =new Scanner (new FileReader(selectedFile))) {

            int length = scanner.nextInt();
            game.length = length;
            game.initArrowArrays();

            scanner.nextLine();

            //Arrow Arrays
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.left.setChar(i, arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.up.setChar(i, arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.right.setChar(i, arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.down.setChar(i, arrow.charAt(0));
            }

            //Int Nodes

            for (int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    String number = scanner.nextLine();
                    game.numbersArray.setIntAt(i,j,Integer.parseInt(number.substring(0,1)));
                }
            }

            game.initNodes();

            //Arrow Nodes
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.leftArrows.get(i).setValue(arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.topArrows.get(i).setValue(arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.rightArrows.get(i).setValue(arrow.charAt(0));
            }
            for (int i = 0; i < length; i++) {
                String arrow = scanner.nextLine();
                game.bottomArrows.get(i).setValue(arrow.charAt(0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCustomNodes(){ // generates map to create a custom game
        double gridWidth = WIDTH / (length + 2);
        double gridHeight = HEIGHT / (length + 2);

        int arrayIteratorX = -1;

        for (int i = 0; i < length + 2; i++) {

            int arrayIteratorY = 0;

            for (int j = 0; j < length + 2; j++) {


                if (i != 0 && i < length + 1 && j != 0 && j < length + 1) { // if node is not on the edges of the grid then it becomes an int node with numerical value

                    CustomNode node = new CustomNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
                    root.getChildren().add(node);

                } else { // otherwise arrow node is being generated

                    char arrow = '|';

                    if ((i == length + 1 && j == length + 1) || (i == 0 && j == 0)) { // nothing happens on the corners

                    } else if (i == 0 && j == length + 1) {

                        SaveNode saveNode = new SaveNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(saveNode);

                    } else if (i == length + 1 && j == 0) {
                        ExitNode exitNode = new ExitNode(i * gridWidth, j * gridHeight, gridWidth, gridHeight, this);
                        root.getChildren().add(exitNode);
                    }


                    else if (i == 0) { // arrows on the left side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.LEFT, j - 1, this.length);
                        root.getChildren().add(node);
                        leftArrows.add(node);

                    } else if (i == length + 1) { //arrows on the right side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.RIGHT, j - 1, this.length);
                        root.getChildren().add(node);
                        rightArrows.add(node);

                    } else if (j == 0) { //arrows on the upper side of of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.UP, i - 1, this.length);
                        root.getChildren().add(node);
                        topArrows.add(node);

                    } else { //arrows on the bottom side of the array

                        ArrowNode node = new ArrowNode(arrow, i * gridWidth, j * gridHeight, gridWidth, gridHeight, ArrowArray.Name.DOWN, i - 1, this.length);
                        root.getChildren().add(node);
                        bottomArrows.add(node);
                    }

                }

            }
            arrayIteratorX++;
        }
    }
}
