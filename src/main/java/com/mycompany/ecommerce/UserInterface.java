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
    
    HBox footer_Bar;
    
    VBox body;
    
    Button sign_in_Button;
    
    Customer LoggedInCustomer;
    
    Label welcome_Label;
    
    ProductList product_List = new ProductList();
    
    VBox Product_Page;
        
    public BorderPane createContent(){
        //main pane (screen)
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);
        root.setTop(header_Bar);
//        root.setCenter(login_Page); 
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        Product_Page = product_List.getAllProducts();
        body.getChildren().add(Product_Page);
        root.setBottom(footer_Bar);
        
        return root;
    }

    public UserInterface() {
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }
    
    private void createLoginPage(){ // this function is creating shopKart login page gui
        // username text 
        Text user_Name_Text = new Text("UserName");
        Text user_Password = new Text("Password");
        
        // username text field and password texrt field
        TextField userName_TextField = new TextField("aman@gmail.com"); 
        userName_TextField.setPromptText("Type your username here");
        PasswordField password_PasswordField = new PasswordField();
        password_PasswordField.setText("1234");
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
                welcome_Label.setText("Welcome " + LoggedInCustomer.getName());
                header_Bar.getChildren().add(welcome_Label);
                body.getChildren().clear();
                body.getChildren().add(Product_Page);
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
        search_bar.setPrefWidth(200);
        
        //search text button
        Button search_Button = new Button("Search");
        
        sign_in_Button = new Button("Sign In");
        
        welcome_Label = new Label();
        
        header_Bar = new HBox();
        header_Bar.setPadding(new Insets(10));
        header_Bar.setSpacing(10);
        header_Bar.setAlignment(Pos.CENTER);
        header_Bar.getChildren().addAll(search_bar , search_Button , sign_in_Button);
        
        sign_in_Button.setOnAction((t) -> {
            body.getChildren().clear(); //remove eveything
            body.getChildren().add(login_Page); //put login page
            header_Bar.getChildren().remove(sign_in_Button);
        });
   }
    
    private void createFooterBar(){ //creation of footer bar
                
        //search text button
        Button buy_Now_Button = new Button("Buy");
        
        footer_Bar = new HBox();
        footer_Bar.setPadding(new Insets(10));
        footer_Bar.setSpacing(10);
        footer_Bar.setAlignment(Pos.CENTER);
        footer_Bar.getChildren().addAll(buy_Now_Button);
        
        sign_in_Button.setOnAction((t) -> {
            body.getChildren().clear(); //remove eveything
            body.getChildren().add(login_Page); //put login page
            header_Bar.getChildren().remove(sign_in_Button);
        });
   }
}
