/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author bisht
 */
public class NewLoginCustomer {
    
    //method to update the database
    public static void insertNewCustomer(NewCustomer customer) throws NoSuchAlgorithmException{
        byte[] byte_password = Encryption.getSHA(customer.getPassword());
        String encrypted_Password = Encryption.toHexString(byte_password);
        try{
            DbConnection connection = new DbConnection();
            PreparedStatement psmt= connection.getPreparedStatementNewLogin();
            psmt.setString(1, customer.getName());
            psmt.setString(2, customer.getEmail());
            psmt.setString(3, customer.getMobile());
            psmt.setString(4, encrypted_Password);
            psmt.setString(5, customer.getAddress());
            psmt.executeUpdate();
        }catch(SQLException e){
        }
    }
    
}
