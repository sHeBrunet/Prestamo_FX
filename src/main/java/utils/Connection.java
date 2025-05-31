package utils;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection connection;

    public Connection(String url, String user,String pass) throws ClassNotFoundException, SQLException, IOException {
        connection = DriverManager.getConnection(url, user, pass);
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

}
