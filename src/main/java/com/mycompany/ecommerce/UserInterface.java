/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author bisht
 */
public class UserInterface {
    
    GridPane login_Page;
    
    HBox header_Bar;
    
    Customer LoggedInCustomer;
    
    ProductList product_List = new ProductList();
    
    VBox Product_Page;
        
    public BorderPane createContent(){
        //main pane (screen)
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        root.setTop(header_Bar);
//        root.setCenter(login_Page); 
        
        Product_Page = product_List.createTable();
        root.setCenter(Product_Page);
        
        return root;
    }

    public UserInterface() {
        createLoginPage();
        createHeaderBar();
    }
    
    private void createLoginPage(){ // this function is creating shopKart login page gui
        // username text 
        Text user_Name_Text = new Text("UserName");
        Text user_Password = new Text("Password");
        
        // username text field and password texrt field
        TextField userName_TextField = new TextField(); 
        userName_TextField.setPromptText("Type your username here");
        PasswordField password_PasswordField = new PasswordField();
        password_PasswordField.setPromptText("Type your passowrd here");
        
        Label message_label = new Label("Hi");
        
        Button login_Button = new Button("Login");
        
        login_Page = new GridPane();
        //adding basisc utilities to login page
        login_Page.setAlignment(Pos.CENTER);
        login_Page.setHgap(10);
        login_Page.setVgap(10);
        login_Page.add(user_Name_Text, 0, 0);
        login_Page.add(userName_TextField , 1 ,0);
        login_Page.add(user_Password , 0, 1);
        login_Page.add(password_PasswordField, 1, 1);
        login_Page.add(message_label, 0, 2);
        login_Page.add(login_Button, 1, 2);
        
        login_Button.setOnAction((t) -> {
            String user_Name = userName_TextField.getText();
            String password = password_PasswordField.getText();
            
            Login login = new Login();
            LoggedInCustomer = login.customerLogin(user_Name, password);
            if(LoggedInCustomer != null){
                message_label.setText("Welcome : " + LoggedInCustomer.getName());
            }
            else{
                message_label.setText("Login Failed !! please give correct user name and password.");
            }
        });
   }
    
    private void createHeaderBar(){ //creation of header bar
        //search text field
        TextField search_bar = new TextField();
        search_bar.setPromptText("Search here");
        search_bar.setPrefWidth(180);
        
        //search text button
        Button search_Button = new Button();
        search_Button.setText("Search");
        
        header_Bar = new HBox();
        header_Bar.setPadding(new Insets(10));
        header_Bar.setSpacing(10);
        header_Bar.setAlignment(Pos.CENTER);
        header_Bar.getChildren().addAll(search_bar , search_Button);
   }
}
