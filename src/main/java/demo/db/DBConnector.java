package demo.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rloqvist
 */
public class DBConnector {

    public DBConnector() {
        loadDatabaseDriver();
    }

    private void loadDatabaseDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createDatabase() {
        Connection dbConnection = null;
        String strUrl = "jdbc:derby:ContactBook;create=true";

        try {
            dbConnection = DriverManager.getConnection(strUrl);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println(dbConnection);

    }

    private boolean createTables(){

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader("sql/create-table.sql"))) {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String schema = sb.toString();

        Connection dbConnection = getConnection();

        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(schema);
            bCreatedTables = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            dbConnection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return bCreatedTables;

    }

    public void create(){
        createDatabase();
        createTables();
    }

    public Connection getConnection() {

        Connection dbConnection = null;
        String strUrl = "jdbc:derby:ContactBook";
        try {
            dbConnection = DriverManager.getConnection(strUrl);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return dbConnection;
    }

}
