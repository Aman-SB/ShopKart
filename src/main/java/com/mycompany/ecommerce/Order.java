/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.ResultSet;

/**
 *
 * @author bisht
 */
public class Order {
    
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
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}