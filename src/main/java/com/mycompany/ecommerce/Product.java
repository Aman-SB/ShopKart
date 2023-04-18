/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.ResultSet;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

/**
 *
 * @author bisht
 */
public class Product {
    private SimpleIntegerProperty id;
    
    private SimpleStringProperty name;
    
    private SimpleDoubleProperty price;

    public Product(int id, String name, Double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    
    public static ObservableList<Product> getAllProduct(){
        String select_All_Products = "SELECT * FROM product;";
        return fetchProductionDataFromDB(select_All_Products);
    }
    
    public static ObservableList<Product> fetchProductionDataFromDB(String query){
        ObservableList<Product> data = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet result = dbConnection.getQueryTable(query);
            while(result.next()){
                Product product = new Product(result.getInt("id") , result.getString("name") , result.getDouble("price"));
                data.add(product);
            }
            return data;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    } 
    
    
}
