/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    NewLoginCustomer newLoginCustomer = new NewLoginCustomer();
    
    VBox Product_Page;
        
    OrderList order_List = new OrderList();
    
    Button place_Order_Button = new Button("Place Order");
    
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    
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
        
        Button create_new_account = new Button("Create Account");
        
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
        login_Page.add(create_new_account,1, 3);
        
        login_Button.setOnAction((ActionEvent t) -> {
            String user_Name = userName_TextField.getText();
            String password = password_PasswordField.getText();
            
            Login login = new Login();
            try {
                LoggedInCustomer = login.customerLogin(user_Name, password);
            } catch (NoSuchAlgorithmException ex) {
                // For specifying wrong message digest algorithms
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        
        //created login for new Account
        create_new_account.setOnAction((ActionEvent t) -> {
            body.getChildren().clear();
            //created functionality to add new user 
            GridPane create_page = new GridPane();

            Button created_Account = new Button("Created Account");

            Text new_user_name = new Text("name");
            Text new_user_email = new Text("email");
            Text new_user_mobile = new Text("Mobile No.");
            Text new_user_address = new Text("Address");
            Text new_user_password = new Text("Password");

            // username text field and password texrt field
            TextField new_user_name_TextField = new TextField("");
            new_user_name_TextField.setPromptText("Type your name here");
            TextField new_user_email_TextField = new TextField("");
            new_user_email_TextField.setPromptText("Type your email here");
            TextField new_user_number_TextField = new TextField("");
            new_user_number_TextField.setPromptText("Type your number here");
            PasswordField new_user_password_PasswordField = new PasswordField();
            new_user_password_PasswordField.setText("");
            new_user_password_PasswordField.setPromptText("Type your passowrd here");
            TextField new_user_address_TextField = new TextField("");
            new_user_address_TextField.setPromptText("Type your address here");

            create_page.setAlignment(Pos.CENTER);
            create_page.setHgap(10);
            create_page.setVgap(10);
            create_page.add(new_user_name, 0, 0);
            create_page.add(new_user_name_TextField , 1 ,0);
            create_page.add(new_user_email , 0, 1);
            create_page.add(new_user_email_TextField, 1, 1);
            create_page.add(new_user_mobile, 0, 2);
            create_page.add(new_user_number_TextField , 1 ,2);
            create_page.add(new_user_address , 0, 3);
            create_page.add(new_user_address_TextField, 1, 3);
            create_page.add(new_user_password, 0, 4);
            create_page.add(new_user_password_PasswordField, 1, 4);

            create_page.add(created_Account,1,5);
            body.getChildren().add(create_page);
            
            created_Account.setOnAction((e) -> {
                NewCustomer newCustomer = new NewCustomer(new_user_name_TextField.getText(),new_user_email_TextField.getText(),new_user_number_TextField.getText(),new_user_password_PasswordField.getText(),new_user_address_TextField.getText());            
                body.getChildren().clear();
                try {
                    NewLoginCustomer.insertNewCustomer(newCustomer);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
                showDialogueSuccess("Account Created Succesfully");
                body.getChildren().add(login_Page);
            });
        });
   }
    
    private void createHeaderBar(){ //creation of header bar
        //Home button
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
        //search bar style
        search_bar.setStyle("-fx-font: normal bold 15px 'serif' ");
        
        //search text button
        Button search_Button = new Button("Search");
        //setting image in the button
        ImageView search_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/pngegg.png");
        search_Button_view.setFitWidth(20);
        search_Button_view.setFitHeight(20);
        search_Button.setGraphic(search_Button_view);
        //setting styling in the button
        search_Button.setOnMouseEntered(e -> search_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        //Sign IN button 
        sign_in_Button = new Button("Sign In");
        //setting image in the button
        ImageView sign_in_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/sign_in.png");
        sign_in_view.setFitWidth(20);
        sign_in_view.setFitHeight(20);
        sign_in_Button.setGraphic(sign_in_view);
        //setting styling in the button
        sign_in_Button.setOnMouseEntered(e -> sign_in_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        welcome_Label = new Label();
        
        //CART BUTTON
        Button cart_Button = new Button("Cart");
        //setting image in the button
        ImageView cart_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/shopping-cart.png");
        cart_Button_view.setFitWidth(20);
        cart_Button_view.setFitHeight(20);
        cart_Button.setGraphic(cart_Button_view);
        //setting styling in the button
        cart_Button.setOnMouseEntered(e -> cart_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        //ORDER BUTTON
        Button order_Button = new Button("Orders");
        //setting image in the button
        ImageView order_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/orders-icon.png");
        order_Button_view.setFitWidth(20);
        order_Button_view.setFitHeight(20);
        order_Button.setGraphic(order_Button_view);
        //setting styling in the button
        order_Button.setOnMouseEntered(e -> order_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        //Header bar
        header_Bar = new HBox();
        header_Bar.setPadding(new Insets(10));
        header_Bar.setSpacing(15);
        header_Bar.setAlignment(Pos.CENTER);
        header_Bar.getChildren().addAll(home_Button, search_bar , search_Button , sign_in_Button , cart_Button , order_Button);
        
        //sign_in button event handler
        if(LoggedInCustomer != null){
            sign_in_Button.setText("Profile");
        }
        sign_in_Button.setOnAction((t) -> {
            footer_Bar.setVisible(false);//all cases need to be handled
            if(LoggedInCustomer == null){
                body.getChildren().clear(); //remove eveything
                body.getChildren().add(login_Page); //put login page 
            }else{
                body.getChildren().clear(); //remove eveything 
                //fetch query data from product table
                
            }
        });
        
        //cart button action
        cart_Button.setOnAction((t) -> {
           body.getChildren().clear();
           VBox product_Page = product_List.createTable(item_In_A_Cart);
           product_Page.setAlignment(Pos.CENTER);
           product_Page.getChildren().add(place_Order_Button);
           body.getChildren().add(product_Page);
           //adding image to place order button
           ImageView place_Order_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/place_orders.png");
           place_Order_Button_view.setFitWidth(20);
           place_Order_Button_view.setFitHeight(20);
           place_Order_Button.setGraphic(place_Order_Button_view);
           //setting styling in the button
           order_Button.setOnMouseEntered(e -> order_Button.setStyle(HOVERED_BUTTON_STYLE));
           footer_Bar.setVisible(false);//all cases need to be handled
        });
        
        //Action on place order button
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
                item_In_A_Cart.clear();
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
        //setting image in the button
        ImageView buy_Now_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/buy_button1.png");
        buy_Now_Button_view.setFitWidth(40);
        buy_Now_Button_view.setFitHeight(30);
        buy_Now_Button.setGraphic(buy_Now_Button_view);
        //setting styling in the button
        buy_Now_Button.setOnMouseEntered(e -> buy_Now_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        //add to cart
        Button add_To_Cart_Button = new Button("Add to Cart");
        //setting image in the button
        ImageView add_To_Cart_Button_view = new ImageView("file:///E:/Java/learning_application/Ecommerce/src/main/icons/add_to_cart.png");
        add_To_Cart_Button_view.setFitWidth(20);
        add_To_Cart_Button_view.setFitHeight(30);
        add_To_Cart_Button.setGraphic(add_To_Cart_Button_view);
        //setting styling in the button
        add_To_Cart_Button.setOnMouseEntered(e -> add_To_Cart_Button.setStyle(HOVERED_BUTTON_STYLE));
        
        footer_Bar = new HBox();
        footer_Bar.setPadding(new Insets(10));
        footer_Bar.setSpacing(15);
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
