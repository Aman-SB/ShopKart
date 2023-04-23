/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

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
        
public class ProductList {
    private TableView<Product> product_Table;
    
    public VBox createTable(ObservableList<Product> data){
        //columns
        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       //data -> dummy data

        product_Table = new TableView<>();
        product_Table.setItems(data);
        product_Table.getColumns().addAll(id,name,price);
        product_Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(product_Table);
        return vBox;
    }
    
    //getting all the products in Vertical Box
    public VBox getAllProducts(){ 
        ObservableList<Product> data = Product.getAllProduct();
        return createTable(data);
    }
    
    //here only selected data can be seen
    public Product getSelectedProduct(){
        return product_Table.getSelectionModel().getSelectedItem();
    }
    
    //here cart data can be seen
    public VBox getProductInCart(ObservableList<Product> data){
        return createTable(data);
    }
    
    
}
