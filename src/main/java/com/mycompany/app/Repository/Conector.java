package com.mycompany.app.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    private static String url = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/knuyemdl";
    private static String user = "knuyemdl";
    private static String pass = "0SgDiONi7AMSY1g8Sbg028G9BJfU_IwF";


    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            connection = conectar();
        }

        return connection;
    }

    private static Connection conectar() {

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na conexão " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void fechar() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao fechar " + e.getMessage());
        }
    }
}
