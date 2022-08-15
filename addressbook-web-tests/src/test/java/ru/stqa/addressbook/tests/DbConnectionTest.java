package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            Groups groups = new Groups();
            ResultSet result = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            while (result.next()) {
                groups.add(new GroupData().withId(result.getInt("group_id")).withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
            result.close();
            st.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
