/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecommerce;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author bisht
 */
public class Login {
    public Customer customerLogin(String useremail,String password) throws NoSuchAlgorithmException{
        byte[] byte_password = Encryption.getSHA(password);
        String encrypted_Password = Encryption.toHexString(byte_password);
        String login_Query = "SELECT * FROM customer WHERE email = '"+useremail+"' and password ='"+encrypted_Password+"'";
        DbConnection connection = new DbConnection();
        ResultSet result = connection.getQueryTable(login_Query);
        try{
            if(result.next()){
                return new Customer(result.getInt("id") , result.getString("name")
                        , result.getString("email") , result.getString("mobile"));
            }
        }
        catch(SQLException e){
        }
        return null;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Login login = new Login();
        Customer customer = login.customerLogin("","");
        System.out.println("Welcome: " + customer.getName());

    }
}
