package com.KacperLorenc.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Arrows extends Application {

    private Group root;
    public Stage primaryStage;
    private int length;
    Game.gameType type;


    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new Group();
        Game game;

        switch (type){
            case NEW_GAME:
                game = Game.normalGame(root, this.length, this);
                break;
            case LOADED_GAME:
                game = Game.loadedGame(root, this);
                break;
            default:
                game = CustomGame.customGame(root,length,this);
            break;
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

    public Arrows(int length, Game.gameType type) {
        this.length = length;
        this.type = type;
    }

}
