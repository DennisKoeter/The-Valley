package com.groeps33.gui;/**
 * Created by Dennis on 25/05/16.
 */

import com.groep33.server.Constants;
import com.groep33.shared.GlobalServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ValleyFX extends Application {
    private static Parent root;
    private static Stage stage;
    private static GlobalServer globalServer;

    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            lookupServer();
        } catch (ConnectException e) {
            //todo messagebox met melding dat server niet aan staat
        }
        launch(args);
    }

    private static void lookupServer() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", Constants.PORT_NUMBER);
        globalServer = (GlobalServer) registry.lookup(Constants.BINDING_NAME);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("com/groeps33/gui/login/login.fxml"));
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

    public static GlobalServer getGlobalServer() {
        return globalServer;
    }
}
