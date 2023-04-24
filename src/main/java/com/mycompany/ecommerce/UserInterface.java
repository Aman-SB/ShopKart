/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
    OrderList order_List = new OrderList();
    
    Button place_Order_Button = new Button("Place Order");
    
    //extracting data of product
    ObservableList<Product> item_In_A_Cart = FXCollections.observableArrayList();
        
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
        TextField userName_TextField = new TextField(""); 
        userName_TextField.setPromptText("Type your username here");
        PasswordField password_PasswordField = new PasswordField();
        password_PasswordField.setText("");
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
        
        Button home_Button = new Button();
        Image image = new Image("file:///E:/Java/learning_application/Ecommerce/src/main/image/shopkart.png");
        
        ImageView image_view = new ImageView();
        image_view.setImage(image);
        image_view.setFitWidth(80);
        image_view.setFitHeight(20);
        home_Button.setGraphic(image_view);
        home_Button.setAlignment(Pos.TOP_LEFT);
        //search text field
        TextField search_bar = new TextField();
        search_bar.setPromptText("Search here");
        search_bar.setPrefWidth(200);
        
        //search text button
        Button search_Button = new Button("Search");
        
        sign_in_Button = new Button("Sign In");
        
        welcome_Label = new Label();
        
        Button cart_Button = new Button("Cart");
        
        Button order_Button = new Button("Orders");
        
        header_Bar = new HBox();
        header_Bar.setPadding(new Insets(10));
        header_Bar.setSpacing(10);
        header_Bar.setAlignment(Pos.CENTER);
        header_Bar.getChildren().addAll(home_Button, search_bar , search_Button , sign_in_Button , cart_Button , order_Button);
        
        //sign_in button event handler
        sign_in_Button.setOnAction((t) -> {
            body.getChildren().clear(); //remove eveything
            body.getChildren().add(login_Page); //put login page
            header_Bar.getChildren().remove(sign_in_Button);
        });
        
        //cart button action
        cart_Button.setOnAction((t) -> {
           body.getChildren().clear();
           VBox product_Page = product_List.createTable(item_In_A_Cart);
           product_Page.setAlignment(Pos.CENTER);
           product_Page.getChildren().add(place_Order_Button);
           body.getChildren().add(product_Page);
           footer_Bar.setVisible(false);//all cases need to be handled
        });
        
        place_Order_Button.setOnAction((t) -> {
            //need list of product
       
            if(item_In_A_Cart == null){
                //please select a product to placed a order
                showDialogueError("please add some product in the cart to placed order!");
                return;
            }
            if(LoggedInCustomer == null){
                showDialogueError("please login first to placed a order!");
                return;
            }
            int order_Count = Order.placeMultipleProduct(LoggedInCustomer, item_In_A_Cart);
            
            if(order_Count != 0){
                showDialogueSuccess("Order for "+order_Count+" products placed Successfully!!");
            }
            else{
                showDialogueError("Order Failed!!");
            }
        });
        
        //home button action
        home_Button.setOnAction((t) -> {
           body.getChildren().clear();
           body.getChildren().add(Product_Page);
           footer_Bar.setVisible(true);
            if (LoggedInCustomer == null&& header_Bar.getChildren().indexOf(sign_in_Button) == -1) {
                header_Bar.getChildren().add(sign_in_Button);
            }
        });
        
        //order button action
        order_Button.setOnAction((var t) -> {
            if(LoggedInCustomer == null){
                showDialogueError("please login first to placed a order!");
                return;
            }
            body.getChildren().clear();
            //extracting data from database and setting into vertical box
            VBox ordered_Page_Extraction = order_List.getAllProducts(LoggedInCustomer.getId());
            
            if(ordered_Page_Extraction == null)
            {
                showDialogueError("Please ordered something !!");
                return;
            }
            body.getChildren().add(ordered_Page_Extraction);
            footer_Bar.setVisible(false);
        });
   }
    
    private void createFooterBar(){ //creation of footer bar
                
        //buy button
        Button buy_Now_Button = new Button("Buy");
        //add to cart
        Button add_To_Cart_Button = new Button("Add to Cart");
        
        footer_Bar = new HBox();
        footer_Bar.setPadding(new Insets(10));
        footer_Bar.setSpacing(10);
        footer_Bar.setAlignment(Pos.CENTER);
        footer_Bar.getChildren().addAll(buy_Now_Button, add_To_Cart_Button);
        
        buy_Now_Button.setOnAction((t) -> {
            Product product = product_List.getSelectedProduct();
            if(product == null){
                //please select a product to placed a order
                showDialogueError("please select a product to placed a order!");
                return;
            }
            if(LoggedInCustomer == null){
                showDialogueError("please login first to placed a order!");
                return;
            }
            boolean order_Status = Order.placeOrder(LoggedInCustomer, product);
            
            if(order_Status){
                showDialogueSuccess("Order placed Successfully!!");
            }
            else{
                showDialogueError("Order Failed!!");
            }
        });
        
        add_To_Cart_Button.setOnAction((t) -> {
            Product product = product_List.getSelectedProduct();
            if(product == null){
                //please select a product to placed a order
                showDialogueError("please select a product first to add it to cart!");
                return;
            }
            item_In_A_Cart.add(product);
            showDialogueSuccess("Selected Item has been added to the cart Succesfully.");
        });
   }
    
    //dialouge box apperance when product not selected
    private void showDialogueError(String message){ 
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
    
    private void showDialogueSuccess(String message){ 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}
