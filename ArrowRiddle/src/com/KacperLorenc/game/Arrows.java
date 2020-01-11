package com.KacperLorenc.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Arrows extends Application {

    private Group root;
    public Stage primaryStage;
    private int length;
    private boolean loaded;


    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        Game game;

        if (loaded) {
            game = Game.loadedGame(root, this);
        } else {
            game = Game.normalGame(root, this.length, this);
        }

        primaryStage.setTitle("Arrows");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            game.doYouWantToExit();
        });
        this.primaryStage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Arrows(int length, boolean loaded) {
        this.length = length;
        this.loaded = loaded;
    }

}
