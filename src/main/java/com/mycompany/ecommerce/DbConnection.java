/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

/**
 *
 * @author bisht
 */

import java.sql.*;

public class DbConnection {
    
    private final String database_Url = "jdbc:mysql://localhost:3306/shopkart";
    
    private final String userName = "root" ;
    
    private final String password = "1234" ;
    
    private Statement getStatement(){
        try{
            Connection connection = DriverManager.getConnection(database_Url , userName , password);
            return connection.createStatement();
        }
        catch(SQLException e){
        }
        return null;
    }
    
    public ResultSet getQueryTable(String query){
        try{
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }
        catch(SQLException e){
        }
        return null;
    }
    
    public int update_Database(String query){
        try{
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }
        catch(SQLException e){
        }
        return 0;
    }
    
    public static void main(String[] args) {
        DbConnection connection = new DbConnection();
        ResultSet result = connection.getQueryTable("SELECT * FROM customer");//data coming from the sql database
        if(result != null){
            System.out.println("Connection Succesfull.");
        }
        else{
            System.out.println("Connection Failed.");
        }
        
    }
}
