/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author bisht
 */
public class OrderList {
    
    private Login login_details = new Login();
    
    private TableView<OrderedItem> order_Product;
    
    public VBox createTable(ObservableList<OrderedItem> data){
        //columns
        TableColumn id = new TableColumn("order id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn name = new TableColumn("Product Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn price = new TableColumn("Product Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        TableColumn order_Date = new TableColumn("Order date");
        order_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        TableColumn order_Status = new TableColumn("Status");
        order_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        order_Product = new TableView<>();
        order_Product.setItems(data);
        order_Product.getColumns().addAll(id,name,price , quantity , order_Date , order_Status);
        order_Product.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(order_Product);
        return vBox;
    }
    
    public VBox getAllProducts(int cust_id){ 
        ResultSet result = OrderedItem.getAllOrdered(cust_id);
        ObservableList<OrderedItem> data = FXCollections.observableArrayList();
        try{
            while(result.next()){
                OrderedItem order_product = new OrderedItem(result.getInt("id") , result.getString("name") , result.getDouble("price") , result.getInt("quantity")  ,result.getDate("order_date"), result.getString("order_status"));
                data.add(order_product);
            }
        }
        catch(SQLException e){
        }
        return createTable(data);
    }
    
}
