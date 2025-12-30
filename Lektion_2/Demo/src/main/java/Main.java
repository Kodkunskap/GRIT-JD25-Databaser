import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        System.out.println("Demo Dag 2");

        Connection db = Database.getConnection();

        try {

            // executeQuery - returnerar data (från en SQL-fråga som returnerar data)
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");

            System.out.println("Databases");
            System.out.println("---------");
            while (rs.next()) {
                System.out.println(rs.getString("Database"));
            }

            String sql = "INSERT INTO Accounts (name, noOfEmployees, status) VALUES ('Panduro', 323, 'Customer')";
            int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            System.out.println("Affected rows: " + affectedRows);

            // Från en fråga som inte returnerar data (t.ex. INSERT, UPDATE, DELETE mf)
            // använder du executeUpdate() istället för executeQuery().
            // int affectedRows = stmt.executeUpdate("UPDATE <table> SET <field> = <value>");

            // När jag skapar data med t.ex. INSERT så kan ett id automatiskt genereras
            // detta id kan jag använda genom att anropa getGeneratedKeys()

            // ResultSet keys = stmt.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
