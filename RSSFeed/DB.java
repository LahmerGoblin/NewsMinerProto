
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB{

public static void main(String[] args) throws Exception {
try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
      System.exit(1);
    }
 

Connection con = DriverManager.getConnection("jdbc:derby:NewsMinerDB;");
Statement stmt = con.createStatement();
String  a = "CREATE TABLE play_evolutions( id Integer NOT NULL PRIMARY KEY, hash VARCHAR(255), applied_at TIMESTAMP NOT NULL, apply_script CLOB, revert_script CLOB, state VARCHAR(255), last_problem CLOB)";
stmt.executeUpdate(a);

}
}


