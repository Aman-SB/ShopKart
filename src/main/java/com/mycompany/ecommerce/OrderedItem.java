/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.Date;
import java.sql.ResultSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bisht
 */

public class OrderedItem {
    private final SimpleIntegerProperty id;
    
    private final SimpleStringProperty name;
    
    private final SimpleDoubleProperty price;
    
    private final SimpleIntegerProperty quantity;
    
    private final ObjectProperty<Date> date ;
            
    private final SimpleStringProperty status;

    public OrderedItem(int id, String name, Double price , int quantity , Date date , String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.date = new SimpleObjectProperty(date);
        this.status = new SimpleStringProperty(status);
    }
    
    public static ResultSet getAllOrdered(int cust_id){
        String select_All_Orders = "SELECT orders.id , product.name , product.price , orders.quantity , orders.order_date , orders.order_status  FROM orders JOIN product ON orders.product_id = product.id WHERE orders.customer_id = '"+cust_id+"'";
        return fetchProductionDataFromDB(select_All_Orders);
    }
    
    public static ResultSet fetchProductionDataFromDB(String query){
        DbConnection dbConnection = new DbConnection();
        ResultSet result = dbConnection.getQueryTable(query);
        return result;
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

    public int getQuantity() {
        return quantity.get();
    }

    public Date getDate() {
        return date.get();
    }
    
    public String getStatus() {
        return status.get();
    }
    
    
}
