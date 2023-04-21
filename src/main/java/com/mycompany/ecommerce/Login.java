/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author bisht
 */
public class Login {
    
    public Customer customerLogin(String userName,String password){
        String login_Query = "SELECT * FROM customer WHERE email = '"+userName+"' and password ='"+password+"'";
        DbConnection connection = new DbConnection();
        ResultSet result = connection.getQueryTable(login_Query);
        try{
            if(result.next()){
                return new Customer(result.getInt("id") , result.getString("name")
                        , result.getString("email") , result.getString("mobile"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        Login login = new Login();
        Customer customer = login.customerLogin("aman@gmail.com", "1234");
        System.out.println("Welcome: " + customer.getName());
    }
}
