package com.groeps33.valley.gui;/**
 * Created by Dennis on 25/05/16.
 */

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ValleyFX extends Application {
//    private static Parent root;
//    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.print(getClass().getResource("login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        Scene scene = new Scene(root);
//        //stage = primaryStage;
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void changeScene(URL location) throws IOException {
//        root = FXMLLoader.load(location);
//        Scene scene = new Scene(root);
//        3
//        stage.show();
    }
}
