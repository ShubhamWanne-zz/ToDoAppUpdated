/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author shubham_wanne
 */
public class ToDoApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        
        String css = this.getClass().getResource("style.css").toExternalForm(); 
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        
        stage.setTitle("To Do App");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:icons8_To_Do_48px.png"));
        stage.setX(825);
        stage.setY(225);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
