package app;

import java.sql.Connection;

import dao.DataAcessObject;

public class Teste {
    
    public static void main(String[] args) {

        Connection connection = DataAcessObject.getConnection();
        System.out.println("Connection: " + connection);

        DataAcessObject.closeConnection(connection); 
    }
}
