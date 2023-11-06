package com.mycompany.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    protected String url = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/knuyemdl";
    protected String user = "knuyemdl";
    protected String pass = "0SgDiONi7AMSY1g8Sbg028G9BJfU_IwF";
    protected Connection conn = null;

    public void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na conexão " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
