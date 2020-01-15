package com.KacperLorenc.main_menu;

import com.KacperLorenc.game.Arrows;

import com.KacperLorenc.game.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MainMenuController {

    @FXML
    ComboBox<String> arraySize;
    @FXML
    Button exitButton;

    //init

    public void initialize() {
        ObservableList<String> sizes = FXCollections.observableArrayList("2x2", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8");
        arraySize.setItems(sizes);
        arraySize.setValue("3x3");
    }

    //type of game

    public void startGame() {
        Stage primaryStage = new Stage();
        Arrows arrows = new Arrows(arrayLength(),Game.gameType.NEW_GAME);
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.hide();

        try {
            arrows.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGame(){
        Stage primaryStage = new Stage();
        Arrows arrows = new Arrows(arrayLength(),Game.gameType.LOADED_GAME);
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.hide();

            Platform.runLater(() -> {
                try {
                    arrows.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

    public void customGame() {
        Stage primaryStage = new Stage();
        Arrows arrows = new Arrows(arrayLength(), Game.gameType.CUSTOM_GAME);
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.hide();

        try {
            arrows.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //utility

    private int arrayLength() {
        String s = arraySize.getSelectionModel().getSelectedItem();
        s = s.substring(0, 1);
        return Integer.parseInt(s);
    }

    public void exitGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("Do you want to exit game?");

        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
    }

    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to Arrows!\n\nPick array size from a dropdown menu and click New Game." +
                " If you previously started a game and saved it, choose Load Game to start where you finished!" +
                " If you feel creative, You can choose a Custom Game option to create your own riddle." +
                "\n\nHow to play:\n\n" +
                "Every cell holds a numerical value. To win, point as many arrows on each cell, as its number says." +
                "\n\nControls:\n" +
                "\nGame:\n" +
                "-left mouse click on cells with arrows to point them in wanted direction\n" +
                "-left mouse click on Check button to check your answer\n" +
                "-left mouse click on Save button to save game\n" +
                "-left mouse click on Exit button to exit to main menu\n" +
                "-left mouse click on Undo if its color is blue to undo your move\n\n" +
                "Custom Game:\n" +
                "-left mouse click on cells with arrows to point them in wanted direction - they will become a valid answer when loaded as a new game\n" +
                "-left mouse click on cells with numbers to increase their values\n" +
                "-right mouse click to decrease their values\n\n");
        alert.showAndWait();
    }
}
