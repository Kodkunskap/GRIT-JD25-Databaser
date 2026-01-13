import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDAO {

    public Contact getContactById(Long id) {
        Connection conn = Database.getConnection();
        // SELECT id, fullname, email, accountsId Id FROM Contacts WHERE id = 1;
        String sql = "SELECT id, fullname, email, accountsId FROM Contacts WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Contact(
                        rs.getLong("id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getLong("accountsId")
                );
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public int saveContact(Contact contact) {
        /*
        INSERT INTO Contacts (fullname, email, accountsId) VALUES ('FULLNAME', 'EMAIL', 10);
        UPDATE Contacts SET fullname='NEWFULLNAME', email='NEWEMAIL', accountsId=10 WHERE id = 9;
         */
        String sql = "";
        if(contact.getId() == null) {
            sql = "INSERT INTO Contacts (fullname, email, accountsId) VALUES (?, ?, ?)";
        } else {
            sql = "UPDATE Contacts SET fullname=?, email=?, accountsId=? WHERE id = ?";
        }

        try {
            PreparedStatement ps = Database.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFullname());
            ps.setString(2, contact.getEmail());
            ps.setLong(3, contact.getAccountId());
            if(contact.getId() != null) {
                ps.setLong(4, contact.getId());
            }

            int numberOfChangedRows = ps.executeUpdate();

            // If new contact fetch the generated ID and update the contact
            if(contact.getId() == null) {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) {
                    contact.setId(rs.getLong(1));
                }
            }
            return numberOfChangedRows;

        } catch (SQLException e) {
            return 0;
        }
    }

}
