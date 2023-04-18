package com.mycompany.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * JavaFX ShopKart
 */
public class ShopKart extends Application {

    private Pane createContent(){
        Pane root = new Pane();
        return root;
    }
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("ShopKart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}