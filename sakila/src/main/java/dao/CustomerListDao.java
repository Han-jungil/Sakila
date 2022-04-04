package dao;
import java.util.*;
import java.sql.*;
import vo.*;
import util.DBUtil;
import java.sql.*;
public class CustomerListDao {

	// 페이지 설정 및 목록 나오게 하기
	public ArrayList<CustomerList> selectCustomerListListByPage(int beginRow, int rowPerPage) {
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); 		// getConnection을 static으로 썼기때문에 객체없이 사용이 가능하다.
		String sql = "SELECT ID customerListId, name name, address, zip_code zipCode, phone, city, country, notes, SID sid  FROM customer_list ORDER BY ID LIMIT ?, ?";
		ArrayList<CustomerList> list = new ArrayList<CustomerList>();		//다형성
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println("sql selectCustomerListListByPage : " + stmt);	//디버깅
			// ?값
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			// 데이터변환(가공)
			while(rs.next()) {
				CustomerList c = new CustomerList();
				c.setCostomerListId(rs.getInt("customerListId"));
				c.setName(rs.getString("Name"));
				c.setAddress(rs.getString("address"));
				c.setZipCode(rs.getString("zipCode"));
				c.setPhone(rs.getString("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("city"));
				c.setNotes(rs.getString("notes"));
				c.setSid(rs.getString("sid"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("예외발생");
		} finally {
			try {
				rs.close(); stmt.close(); conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// 총합 구하기 및 라스트 페이지 설정
	public int selectCustomerListTotalRow() {
		int row = 0;
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT COUNT(*) cnt FROM customer_list";
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println("sql selectCustomerListListRow : " + stmt);	//디버깅
			rs = stmt.executeQuery();
			if(rs.next()) {
			row = rs.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}finally {
			try {
				rs.close(); stmt.close(); conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
}
