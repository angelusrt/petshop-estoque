package com.mycompany.app.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    String url = "";
    String user = "root";
    String pass = "root";
    Connection conn = null;

    public void conectar() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na conexão " + e.getMessage());
        } 
    }
    
    public void fechar() {
        try {
            conn.close();  
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao fechar " + e.getMessage());
        } 
    }
}
