package progect4;

import progect1.DBhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExcelExporter {
    public void export() {
        String sqlTables = "SHOW TABLES";
        try (Connection conn = DBhandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rsTables = stmt.executeQuery(sqlTables)) {

            while (rsTables.next()) {
                String table = rsTables.getString(1);
                String query = "SELECT * FROM " + table + " INTO OUTFILE 'C:/Users/1/Desktop/sql/" + table + ".csv'";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.executeQuery();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}