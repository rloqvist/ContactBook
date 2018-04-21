package demo.contacts;

import demo.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rloqvist
 */
public class ContactsDao {

    private static final String strAddRecord =
            "INSERT INTO APP.CONTACT (FULLNAME, CELL) VALUES (?, ?)";
    private static final String strDeleteRecord =
            "DELETE FROM APP.CONTACT WHERE ID = ?";
    private static final String strListRecords =
            "SELECT ID, FULLNAME, CELL FROM APP.CONTACT";

    private Connection dbConnection;

    public ContactsDao() {
        this.dbConnection = new DBConnector().getConnection();
    }

    public int createRecord(String fullname, String cell) {
        int contactId = -1;

        try {
            PreparedStatement stmtCreateContact = dbConnection
                    .prepareStatement(strAddRecord,
                            Statement.RETURN_GENERATED_KEYS);

            stmtCreateContact.clearParameters();
            stmtCreateContact.setString(1, fullname);
            stmtCreateContact.setString(2, cell);
            stmtCreateContact.executeUpdate();
            ResultSet results = stmtCreateContact.getGeneratedKeys();
            if (results.next()) {
                contactId = results.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contactId;
    }

    public List<Contact> listRecords() {
        List<Contact> contacts = new ArrayList<>();



        try {
            PreparedStatement stmtListContacts = dbConnection
                    .prepareStatement(strListRecords);
            stmtListContacts.clearParameters();
            ResultSet results = stmtListContacts.executeQuery();

            while (results.next()) {
                int contactId = results.getInt(1);
                String fullname = results.getString(2);
                String cell = results.getString(3);
                Contact contact = new Contact(contactId, fullname, cell);
                contacts.add(contact);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contacts;
    }

    public boolean deleteRecord(int contactId) {
        boolean bDeleted = false;

        try {
            PreparedStatement stmtDeleteContact = dbConnection
                    .prepareStatement(strDeleteRecord);
            stmtDeleteContact.clearParameters();
            stmtDeleteContact.setInt(1, contactId);
            stmtDeleteContact.executeUpdate();
            bDeleted = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bDeleted;
    }

}
