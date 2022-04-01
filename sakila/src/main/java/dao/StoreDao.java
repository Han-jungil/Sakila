package dao;

import java.sql.*;
import java.util.*; 

public class StoreDao {
	
	//selectStoreList
	public List<Map<String, Object>> selectStoreList() {
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
					+ "	s1.store_id storeId,\r\n"
					+ "	s1.manager_staff_id staffId, \r\n"
					+ "	concat(s2.first_name,' ',s2.last_name) staffName,\r\n"
					+ "	s1.address_id addressId,\r\n"
					+ "	CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress,\r\n"
					+ "	s1.last_update lastUpdate\r\n"
					+ "FROM store s1 \r\n"
					+ "INNER JOIN staff s2\r\n"
					+ "INNER JOIN address a\r\n"
					+ "ON s1.manager_staff_id = s2.staff_id \r\n"
					+ "AND s1.address_id = a.address_id";
			stmt = conn.prepareStatement(sql);
			// 값 반환
			rs = stmt.executeQuery();
			// Map에 넣기
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();	//다형성
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffname", rs.getString("staffname"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("staffAddress", rs.getString("staffAddress"));
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
	// selectStoreList 테스트
	public static void main(String[] args) {
		StoreDao dao = new StoreDao();
		List<Map<String, Object>> list = dao.selectStoreList();
		for(Map m : list) {
			System.out.println(m.get("storeId"));
			System.out.println(m.get("staffId"));
			System.out.println(m.get("staffname"));
			System.out.println(m.get("addressId"));
			System.out.println(m.get("staffAddress"));
			System.out.println(m.get("lastUpdate"));
		}
	}
}
