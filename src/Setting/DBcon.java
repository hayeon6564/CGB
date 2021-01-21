package Setting;

import java.sql.*;
public class DBcon {

	static Statement stmt = null;
	Connection con = null;

	public DBcon() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"cgb", "1234");
			stmt = con.createStatement();
//			System.out.println("연결 성공!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return con;

	}

	public static ResultSet executeQuery(String sql)throws Exception{
		ResultSet rs = null;
		rs = stmt.executeQuery(sql);
		return rs;
	}

}
