package com.groeps33.gui.application;/**
 * Created by Dennis on 25/05/16.
 */

import com.groeps33.gui.screens.lobby.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import com.groeps33.server.shared.lobby.ILobby;
import com.groeps33.server.shared.lobby.ILobbyAdministration;
import com.groeps33.server.shared.UserAccount;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class ValleyFX extends Application {
    private static Parent root;
    private static Stage stage;
    private static MediaPlayer player;
    private static UserAccount userAccount;

    // settings variables
    private static boolean audioFX;
    private static boolean audioMusic;

    private static ILobbyAdministration lobbyAdministration;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            lookupServer();
        } catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "It appears that the server is not online.", "Couldn't connect to server", JOptionPane.ERROR_MESSAGE);
        }

        launch(args);
    }

    private static void lookupServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Constants.RMI_IP, com.groeps33.server.application.Constants.PORT_NUMBER);
        lobbyAdministration = (ILobbyAdministration) registry.lookup(com.groeps33.server.application.Constants.LOBBY_ADMIN_NAME);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = getClass().getResource(Constants.LOGIN_PATH);
        String musicString = new File(Constants.MAINMUSIC_NAME).toURI().toString();
        Media musicMedia = new Media(musicString);
        player = new MediaPlayer(musicMedia);
        player.setOnEndOfMedia(() -> player.seek(javafx.util.Duration.ZERO));
        player.play();
        audioFX = true;
        audioMusic = true;
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Object changeScene(URL location) throws IOException {
        FXMLLoader loader = new FXMLLoader(location);
        stage.setScene(new Scene(loader.load()));
        Object controller = loader.getController();
        stage.show();
        return controller;
    }

    public static void changeScene(URL location, ILobby lobby) throws IOException {
        FXMLLoader loader = new FXMLLoader(location);
        stage.setScene(new Scene(loader.load()));
        Controller controller = loader.getController();
        controller.init(lobby);
        stage.show();
    }

    public static void saveSettings(boolean audioFxSetting, boolean audioMusicSetting) {
        audioFX = audioFxSetting;
        audioMusic = audioMusicSetting;
        if (!audioMusic && player != null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.stop();
        } else if (audioMusic && player != null && player.getStatus() == MediaPlayer.Status.STOPPED) {
            player.play();
        }
    }

    public static HashMap getSettings() {
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put(Constants.AUDIOFX_KEY, audioFX);
        settings.put(Constants.AUDIOMUSIC_KEY, audioMusic);
        return settings;
    }

    public static ILobbyAdministration getLobbyAdministration() {
        return lobbyAdministration;
    }

    public static void setUserAccount(UserAccount userAccount) {
        ValleyFX.userAccount = userAccount;
    }


    public static UserAccount getUserAccount() {
        return userAccount;
    }

    public static void showMessageBox(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void startGame(String host, boolean isHost) {
        File jarFile = new File("desktop\\build\\libs", "desktop-1.0.jar");

        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jarFile.getAbsolutePath(), host, userAccount.getUsername(), userAccount.getEmail(), Boolean.toString(isHost));
        try {
            stage.setIconified(true);
            Process p = processBuilder.start(); //Runtime.getRuntime().exec("java -jar \"" + jarFile.getAbsolutePath() + "\"");//processBuilder.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader inErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line, lineErr = null;
            while ((line = in.readLine()) != null || (lineErr = inErr.readLine()) != null) {
                if (line != null) System.out.println(line);
                if (lineErr != null) System.out.println(lineErr);
            }

            p.waitFor();
            Thread.sleep(2000);
            stage.setIconified(false);
            stage.toFront();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
