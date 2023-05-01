/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author bisht
 */
public class Order {
    
    //method if we place order
    public static boolean placeOrder(Customer customer, Product product){
        String group_Order_Id = "SELECT MAX(group_order_id) +1 id FROM orders";
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet result = dbConnection.getQueryTable(group_Order_Id);
            if(result.next()){
                String PlaceOrder = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES ("+result.getInt("id")+","+customer.getId()+","+product.getId()+")";
                return dbConnection.update_Database(PlaceOrder) != 0;
            }
        }
        catch(SQLException e){
        }
        return false;
    }
    
    //method to update the database
    public static int placeMultipleProduct(Customer customer, ObservableList<Product> product_List){
        String group_Order_Id = "SELECT MAX(group_order_id) +1 id FROM orders";
        DbConnection dbConnection = new DbConnection();
        try{
            ResultSet result = dbConnection.getQueryTable(group_Order_Id);
            int count_Product = 0;
            if(result.next()){
                for(Product product : product_List){
                String PlaceOrder = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES ("+result.getInt("id")+","+customer.getId()+","+product.getId()+")";
                count_Product += dbConnection.update_Database(PlaceOrder);
                }
                return count_Product;
            }
        }
        catch(SQLException e){
        }
        return 0;
    }
    
}
