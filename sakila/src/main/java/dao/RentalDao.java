package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import util.DBUtil;
import vo.FilmList;

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList(int beginRow, int rowPerPage, int storeId, String customerName, String beginDate, String endDate) {

	// 자원 준비
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		//다형성
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	// 디비접속
	conn = DBUtil.getConnection();
	/*
	 SELECT 
	   r.*, 
	   CONCAT(c.first_name,' ',c.last_name) cName, 
	   s.store_id storeId, 
	   i.film_id filmId,
	   f.title
	FROM rental r INNER JOIN customer c
	ON r.customer_id = c.customer_id
	   INNER JOIN staff s
	   ON r.staff_id = s.staff_id
	      INNER JOIN inventory i
	      ON r.inventory_id = i.inventory_id
	         INNER JOIN film f
	         ON i.film_id = f.film_id
	WHERE s.store_id=? AND CONCAT(c.first_name,' ',c.last_name) LIKE ?
	AND r.rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') 
	AND STR_TO_DATE('?','%Y-%m-%d');
	 */
	// SQL문 실행
	try {
		String sql = "SELECT r.*, CONCAT(c.first_name,' ',c.last_name) cName, s.store_id , i.film_id filmId, f.title title FROM rental r INNER JOIN customer c	ON r.customer_id = c.customer_id INNER JOIN staff s ON r.staff_id = s.staff_id INNER JOIN inventory i ON r.inventory_id = i.inventory_id INNER JOIN film f ON i.film_id = f.film_id	WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
		// 최소 날짜 SELECT MIN(rental_date) FROM rental; --> 2005-05-24
		// 최대 날짜 SELECT MAX(rental_date) FROM rental; --> 2006-02-14
		if(beginDate.equals("") && endDate.equals("")) {
			if(storeId != -1) {
				sql += " AND s.store_id = ? ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else {
				sql += " ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			}
		} else if(!beginDate.equals("") && endDate.equals("")) {
			if(storeId != -1) {
				sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('2006-02-14','%Y-%m-%d')  ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else {
				sql += " AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('2006-02-14','%Y-%m-%d')  ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}
		} else if(beginDate.equals("") && !endDate.equals("")) {
			if(storeId != -1) {
				sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('2005-05-24','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')  ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else {
				sql += " AND rental_date BETWEEN STR_TO_DATE('2005-05-24','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')  ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}
		} else if(!beginDate.equals("") && !endDate.equals("")) {
			if(storeId != -1) {
				sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')  ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else {
				sql += " AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d') ORDER BY r.rental_id LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			}
		}
		System.out.println(sql);
		rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("rentalId", rs.getInt("rental_id"));
			map.put("rentalDate", rs.getString("rental_date"));
			map.put("inventoryId", rs.getInt("inventory_id"));
			map.put("customerId", rs.getInt("customer_id"));
			map.put("returnDate", rs.getString("return_date"));
			map.put("staffId", rs.getInt("staff_Id"));
			map.put("lastUpdate", rs.getString("last_update"));
			map.put("customerName", rs.getString("cName"));
			map.put("storeId", rs.getInt("store_Id"));
			map.put("filmId", rs.getInt("filmId"));
			map.put("title", rs.getString("title"));
			list.add(map);
		}
	} catch(SQLException e) {
		e.printStackTrace(); // 예외된 애들 꼭 찝어달라
	}
		return list;
	}
	
	
	// rentalSearchAction 총합 구하기 및 라스트 페이지 설정
		public int rentalSearchTotalRow(int storeId, String customerName, String beginDate, String endDate) {
			int row = 0;
			Connection conn = null;
			PreparedStatement  stmt = null;
			ResultSet rs = null;
			HashMap<String, Object> map = null;
			conn = DBUtil.getConnection();
			String sql = "";
			try {
				sql = "SELECT count(*) cnt FROM rental r INNER JOIN customer c	ON r.customer_id = c.customer_id INNER JOIN staff s ON r.staff_id = s.staff_id INNER JOIN inventory i ON r.inventory_id = i.inventory_id INNER JOIN film f ON i.film_id = f.film_id	WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
				// 최소 날짜 SELECT MIN(rental_date) FROM rental; --> 2005-05-24
				// 최대 날짜 SELECT MAX(rental_date) FROM rental; --> 2006-02-14
				if(beginDate.equals("") && endDate.equals("")) {
					if(storeId != -1) {
						sql += " AND s.store_id = ? ";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setInt(2, storeId);
					} else {
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
					}
				} else if(!beginDate.equals("") && endDate.equals("")) {
					if(storeId != -1) {
						sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('2006-02-14','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setInt(2, storeId);
						stmt.setString(3, beginDate);
					} else {
						sql += " AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('2006-02-14','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setString(2, beginDate);
					}
				} else if(beginDate.equals("") && !endDate.equals("")) {
					if(storeId != -1) {
						sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('2005-05-24','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setInt(2, storeId);
						stmt.setString(3, endDate);
					} else {
						sql += " AND rental_date BETWEEN STR_TO_DATE('2005-05-24','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setString(2, endDate);
					}
				} else if(!beginDate.equals("") && !endDate.equals("")) {
					if(storeId != -1) {
						sql += " AND storeId = ? AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setInt(2, storeId);
						stmt.setString(3, beginDate);
						stmt.setString(4, endDate);
					} else {
						sql += " AND rental_date BETWEEN STR_TO_DATE('?','%Y-%m-%d') AND STR_TO_DATE('?','%Y-%m-%d')";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+customerName+"%");
						stmt.setString(2, beginDate);
						stmt.setString(3, endDate);
					}
				}
				System.out.println(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
				row = (rs.getInt("cnt"));
				}
			} catch(SQLException e) {
				e.printStackTrace(); // 예외된 애들 꼭 찝어달라
				System.out.println("예외발생");
			}finally {//DB자원 반납
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return row;
		}
}
