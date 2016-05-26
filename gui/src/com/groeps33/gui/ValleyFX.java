package com.groeps33.gui;/**
 * Created by Dennis on 25/05/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ValleyFX extends Application {
    private static Parent root;
    private static Stage stage;

    // settings variables
    private static boolean audioFX;
    private static boolean audioMusic;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        audioFX = true;
        audioMusic = true;
        Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        Scene scene = new Scene(root);
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void changeScene(URL location) throws IOException {
        System.out.println(location.toString());
        root = FXMLLoader.load(location);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void saveSettings(boolean audioFxSetting, boolean audioMusicSetting) {
        audioFX = audioFxSetting;
        audioMusic = audioMusicSetting;
    }
}
