import java.sql.*;

public class DatabaseConnector {
    public static void main( String[] args ) throws SQLException {
        //create connection for a server installed in localhost, with a user "root" with no password
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gym_management_database_system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", null)) {

            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM gym_management_database_system.attends");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));

        }
            conn.close();
        }
        }
    }


