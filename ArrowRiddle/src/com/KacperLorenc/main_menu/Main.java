package com.KacperLorenc.main_menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        primaryStage.setTitle("Arrows");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event->{
            event.consume();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit game");
            alert.setHeaderText("Do you want to exit game?");

            Optional<ButtonType> option = alert.showAndWait();
            if(option.get() == ButtonType.OK) {
                primaryStage.close();
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
