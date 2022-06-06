package dao;
import java.util.*;
import java.sql.*;
import vo.*;
import util.DBUtil;
import java.sql.*;
public class ActorInfoDao {

	// 페이지 설정 및 목록 나오게 하기
	public ArrayList<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); 		// getConnection을 static으로 썼기때문에 객체없이 사용이 가능하다.
		String sql = "SELECT actor_id actorId, first_name firstName, last_name lastName, film_info filmInfo FROM actor_info ORDER BY actor_id LIMIT ?, ?";
		ArrayList<ActorInfo> list = new ArrayList<ActorInfo>();		//다형성
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println("sql selectActionInfoListByPage : " + stmt);	//디버깅
			// ?값
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			// 데이터변환(가공)
			while(rs.next()) {
				ActorInfo a = new ActorInfo();
				a.setActorId(rs.getInt("actorId"));
				a.setFirstName(rs.getString("firstName"));
				a.setLastName(rs.getString("lastName"));
				a.setFilmInfo(rs.getString("filmInfo"));
				list.add(a);
			}
			
		} catch (Exception e) {
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
	public int selectActoInfoTotalRow() {
		int row = 0;
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT COUNT(*) cnt FROM actor_info";
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println("sql selectActoInfoTotalRow : " + stmt);	//디버깅
			rs = stmt.executeQuery();
			if(rs.next()) {
			row = rs.getInt("cnt");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}finally {
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