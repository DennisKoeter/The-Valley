package com.groeps33.gui;/**
 * Created by Dennis on 25/05/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ValleyFX extends Application {
    private static Parent root;
    private static Stage stage;
    private static MediaPlayer player;

    // settings variables
    private static boolean audioFX;
    private static boolean audioMusic;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        String musicString = new File("MainMusic.mp3").toURI().toString();
        Media musicMedia = new Media(musicString);
        player = new MediaPlayer(musicMedia);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();
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
        if(!audioMusic && player != null && player.getStatus() == MediaPlayer.Status.PLAYING){
            player.stop();
        }
        else if(audioMusic && player != null && player.getStatus() == MediaPlayer.Status.STOPPED){
            player.play();
        }
    }

    public static HashMap getSettings() {
        HashMap settings = new HashMap();
        settings.put("audioFX", audioFX);
        settings.put("audioMusic", audioMusic);
        return settings;
    }
}
