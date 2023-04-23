package com.mycompany.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX ShopKart
 */
public class ShopKart extends Application {

    UserInterface userInterface =  new UserInterface();
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(userInterface.createContent( ));
        stage.setTitle("ShopKart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}