package com.fedaruk;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("VATXMLCalculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
