package util;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() {
		// 마리아DB 접속
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://3.39.254.208/sakila","root","mariadb1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}