package com.groeps33.gui.application;/**
 * Created by Dennis on 25/05/16.
 */

import com.groeps33.gui.screens.lobby.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import com.groeps33.server.shared.ILobby;
import com.groeps33.server.shared.ILobbyAdministration;
import com.groeps33.server.shared.UserAccount;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class    ValleyFX extends Application {
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
            //todo messagebox met melding dat server niet aan staat
        }

        launch(args);
    }

    private static void lookupServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Constants.RMI_IP, com.groeps33.server.application.Constants.PORT_NUMBER);
        lobbyAdministration = (ILobbyAdministration) registry.lookup(com.groeps33.server.application.Constants.BINDING_NAME);
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

    public static void changeScene(URL location) throws IOException {
        root = FXMLLoader.load(location);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void changeScene(URL location, ILobby lobby) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setLobby(lobby);
        Scene scene = new Scene(root);
        stage.setScene(scene);
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


    public static UserAccount getUserAccount(){
        return userAccount;
    }

    public static ILobby createLobby(String name, int maxPlayers, String password) throws RemoteException {
        return lobbyAdministration.registerLobby(userAccount, name);
    }
}
