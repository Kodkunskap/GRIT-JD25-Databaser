
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static Connection db = null;

    private Database() {}

    static Connection getConnection(){

        if(db != null) return db;

        String username = "";
        String password = "";
        String host = "";
        String port = "";
        String database = "";

        // Load properties from props-file
        Properties props = new Properties();
        try(InputStream input = Database.class.getResourceAsStream("/database.properties")) {
            props.load(input);
            username = props.getProperty("db.user");
            password = props.getProperty("db.password");
            host = props.getProperty("db.host");
            port = props.getProperty("db.port");
            database = props.getProperty("db.name");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            String url = "jdbc:mysql://" + host + (port.isBlank() ? "" : ":" + port);
            url += (database.isBlank() ? "/" : "/" + database + "?useSSL=false");

            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            SQLExceptionPrint(ex, true);
            return null;
        }
        return db;
    }

    static void SQLExceptionPrint(SQLException sqle) {
        SQLExceptionPrint(sqle, false);
    }

    static void SQLExceptionPrint(SQLException sqle, Boolean printStackTrace) {
        while (sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState: " + (sqle).getSQLState());
            System.out.println("Severity: " + (sqle).getErrorCode());
            System.out.println("Message: " + (sqle).getMessage());
            if(printStackTrace)  sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }


}