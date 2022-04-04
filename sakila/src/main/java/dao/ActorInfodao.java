package dao;
import java.util.*;
import java.sql.*;
import vo.*;
import util.DBUtil;

public class ActorInfodao {
	
	public List<ActorInfo> selectActorInfoListByPage(int beginRow, int rowPerPage) {
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM actor_info ORDER BY actor_id LIMIT ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			// ?
			rs = stmt.executeQuery();
			// rs.next() ... list.add
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close(); stmt.close(); conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
