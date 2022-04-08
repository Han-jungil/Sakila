package dao;

import java.util.List;
import java.util.*;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatsDataDao {
	public List<Map<String, Object>> amountByCoustomer() {
	     List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	     Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT p.customer_id customerId,"
	           + "      concat(c.first_name,' ', c.last_name) name,"
	           + "      sum(p.amount) total"
	           + "      FROM payment p INNER JOIN customer c"
	           + "      ON p.customer_id = c.customer_id"
	           + "      GROUP BY p.customer_id"
	           + "		HAVING sum(p.amount) >= 180"
	           + "      ORDER BY SUM(p.amount) DESC";
	     try {
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	           Map<String, Object> m = new HashMap<>();
	           m.put("customerId",rs.getInt("customerId"));
	           m.put("name",rs.getString("name"));
	           m.put("total",rs.getInt("total"));
	           list.add(m);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
	        try {
	           rs.close();
	           stmt.close();
	           conn.close();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	     }
	     return list;
	  }
	public List<Map<String, Object>> rentalRateBycount() {
	     List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	     Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT rental_rate, COUNT(*) cnt"
	     		+ " FROM film"
	     		+ " GROUP BY rental_rate"
	     		+ " ORDER BY COUNT(*) DESC";
	     try {
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	           Map<String, Object> m = new HashMap<>();
	           m.put("rentalRate",rs.getInt("rental_rate"));
	           m.put("cnt",rs.getInt("cnt"));
	           list.add(m);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
	    	 try {
			     rs.close();
			     stmt.close();
			     conn.close();
			     } catch (SQLException e) {
			         e.printStackTrace();
			     }
	     }
	     return list;
	  }
	public List<Map<String, Object>> ratingByCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	     Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT rating, COUNT(*) cnt"
	     		+ " FROM film"
	     		+ " GROUP BY rating"
	     		+ " ORDER BY COUNT(*) DESC";
	     try {
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	           Map<String, Object> m = new HashMap<>();
	           m.put("rating",rs.getString("rating"));
	           m.put("cnt",rs.getInt("cnt"));
	           list.add(m);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
		        try {
			           rs.close();
			           stmt.close();
			           conn.close();
			        } catch (SQLException e) {
			           e.printStackTrace();
			    }
		}
	     return list;
	  }
	public List<Map<String, Object>> languageByCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	     Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT l.name, COUNT(*) cnt "
	     		+ "FROM film f INNER JOIN language l "
	     		+ "ON f.language_id = l.language_id "
	     		+ "GROUP BY l.name;";
	     try {
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	           Map<String, Object> m = new HashMap<>();
	           m.put("name",rs.getString("name"));
	           m.put("cnt",rs.getInt("cnt"));
	           list.add(m);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
		        try {
			           rs.close();
			           stmt.close();
			           conn.close();
			        } catch (SQLException e) {
			           e.printStackTrace();
			    }
		}
	     return list;
	  }
	public List<Map<String, Object>> languageByLengthCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	     Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT t.length2 length, COUNT(*) cnt "
	     		+ "FROM(SELECT title, LENGTH, "
	     		+ "	CASE WHEN LENGTH <= 60 THEN 'less60' "
	     		+ "		  WHEN LENGTH BETWEEN 61 AND 120 THEN 'between61and120' "
	     		+ "		  WHEN LENGTH between 121 AND 180 THEN 'beween121and180' "
	     		+ "		  ELSE 'more180' "
	     		+ "	END LENGTH2 "
	     		+ "FROM film) t "
	     		+ "GROUP BY t.length2;";
	     try {
	        stmt = conn.prepareStatement(sql);
	        rs = stmt.executeQuery();
	        while(rs.next()) {
	           Map<String, Object> m = new HashMap<>();
	           m.put("length",rs.getString("length"));
	           m.put("cnt",rs.getInt("cnt"));
	           list.add(m);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally {
		        try {
			           rs.close();
			           stmt.close();
			           conn.close();
			        } catch (SQLException e) {
			           e.printStackTrace();
			    }
		}
	     return list;
	  }
}

