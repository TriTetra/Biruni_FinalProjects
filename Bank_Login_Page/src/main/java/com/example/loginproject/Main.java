package com.example.loginproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.File;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            primaryStage.setTitle("Welcome Pig Bank");
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(icon());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Image icon(){
        File iconFile = new File("C:\\Users\\abdul\\OneDrive\\Masaüstü\\Java\\LoginProject\\src\\main\\resources\\Images\\pig.png");
        return new Image(iconFile.toURI().toString());
    }


    public static void main(String[] args) {
        launch();
    }
}