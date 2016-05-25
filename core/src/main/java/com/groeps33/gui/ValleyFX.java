package com.groeps33.gui;/**
 * Created by Dennis on 25/05/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ValleyFX extends Application {
    private static Parent root;
    private static Stage stage;
    private static ValleyFX application;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        application = this;
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage = primaryStage;
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(String sceneName) throws IOException {
        root = FXMLLoader.load(application.getClass().getResource(sceneName + ".fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
