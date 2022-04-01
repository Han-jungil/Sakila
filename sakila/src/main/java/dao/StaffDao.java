package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaffDao {
	//selectStaffList
		public List<Map<String, Object>> selectStaffList() {
			// ArrayList는 List 인터페이스의 구현체 중 하나이다. 즉 부모다
			// HashMap은 Map 인터페이스의 구현체 중 하나이다. 즉 부모!
			List<Map<String, Object>> list = new ArrayList<>();		//다형성
			
			// DB자원
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				// 마리아DB 접속
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
				// SQL문
				String sql = "SELECT \r\n"
						+ "s1.staff_id staffId,\r\n"
						+ "concat(s1.first_name,' ',s1.last_name) staffName,\r\n"
						+ "CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,\r\n"
						+ "s1.email email,\r\n"
						+ "s1.store_id storeId,\r\n"
						+ "IF(s1.active, _utf8mb4'active',_utf8mb4' ') notes,\r\n"
						+ "s1.username username,\r\n"
						+ "s1.password passoword,\r\n"
						+ "s1.last_update lastUpdate\r\n"
						+ "FROM staff s1 \r\n"
						+ "INNER JOIN store s2\r\n"
						+ "INNER JOIN address a\r\n"
						+ "ON s1.store_id = s2.store_id\r\n"
						+ "AND s1.address_id = a.address_id";
				stmt = conn.prepareStatement(sql);
				// 값 반환
				rs = stmt.executeQuery();
				// Map에 넣기
				while(rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();	//다형성
					map.put("staffId", rs.getInt("staffId"));
					map.put("staffName", rs.getString("staffName"));
					map.put("staffAddress", rs.getString("staffAddress"));
					map.put("email", rs.getString("email"));
					map.put("storeId", rs.getInt("storeId"));
					map.put("notes", rs.getString("notes"));
					map.put("username", rs.getString("username"));
					map.put("passoword", rs.getString("passoword"));
					map.put("lastUpdate", rs.getString("lastUpdate"));
					list.add(map);
				}
				
			} catch ( Exception e) {	// ClassNotFountException, SQLExcption 두개의 예외를 부모타입 Exception으로 처리 -> 다형성
				e.printStackTrace();
				System.out.println("예외발생");
			} finally {
				try {
					conn.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		// selectStaffList 테스트
		public static void main(String[] args) {
			StaffDao dao = new StaffDao();
			List<Map<String, Object>> list = dao.selectStaffList();
			for(Map m : list) {
				System.out.println(m.get("staffId"));
				System.out.println(m.get("staffName"));
				System.out.println(m.get("staffAddress"));
				System.out.println(m.get("email"));
				System.out.println(m.get("storeId"));
				System.out.println(m.get("notes"));
				System.out.println(m.get("username"));
				System.out.println(m.get("passoword"));
				System.out.println(m.get("lastUpdate"));
		}
	}
}