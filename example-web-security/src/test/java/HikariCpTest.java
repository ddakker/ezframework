import java.sql.SQLException;

import org.junit.Test;

public class HikariCpTest {

	public static void main(String[] args) throws SQLException {
		new HikariCpTest().driverTest1();
	}

	@Test
	public void driverTest1() throws SQLException
	   {/*
	      HikariConfig config = new HikariConfig();
	      config.setMinimumIdle(1);
	      config.setMaximumPoolSize(1);
	      config.setConnectionTestQuery("VALUES 1");
	      config.setDriverClassName("oracle.jdbc.pool.OracleDataSource");
	      config.setJdbcUrl("jdbc:oracle:thin:@222.231.44.177:1521:ezwel");
	      config.addDataSourceProperty("user", "tams");
	      config.addDataSourceProperty("password", "roqkf_0606");

	      HikariDataSource ds = new HikariDataSource(config);
	      System.out.println("ds: " + ds);


	      Connection connection = ds.getConnection();

	      System.out.println("connection: " + connection);

	      Statement stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery("select * from ez_user where rownum <= 1");
	      if (rs.next()) {
	    	  do {
	    		  System.out.println(rs.getString("user_nm"));
	    	  } while (rs.next());
	      }

	      connection.close();
*/
	   }
}
