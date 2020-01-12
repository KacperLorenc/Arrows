package com.KacperLorenc.mainMenu;

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

}
