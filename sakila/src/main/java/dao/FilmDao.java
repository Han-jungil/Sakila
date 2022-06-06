package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.DBUtil;
import vo.Category;
import vo.FilmList;


public class FilmDao {
	public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actor) {		
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			// 동적쿼리
			String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ? ";
			if(price==-1 && length==-1 && rating.equals("")) {
				if(!category.equals("")) {
					sql += " AND category LIKE ? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%"); 
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				} else {
					sql += " ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%"); 
					stmt.setString(2, "%"+actor+"%");
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			} else if(price==-1 && length!=-1 && rating.equals("")) {
				if(length == 0) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length<60 ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);
					} else {
						sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setInt(3, beginRow);
						stmt.setInt(4, rowPerPage);
					}
				} else if(length == 1) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length>=60 ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);
					} else {
						sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setInt(3, beginRow);
						stmt.setInt(4, rowPerPage);
					}
				}
			} else if(rating.equals("") && price!=-1 && length==-1) {
				if(!category.equals("")) {
					sql += " AND category LIKE ? AND price=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setDouble(4, price);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
				} else {
					sql += " AND price=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setDouble(3, price);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
			}
			} else if(!rating.equals("") && price==-1 && length==-1) {
				if(!category.equals("")) {
					sql += " AND category LIKE ? AND rating=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setString(4, rating);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
				} else {
					sql += " AND rating=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				}
			} else if (price!=-1 && length!=-1 && rating.equals("")) {
				if(length == 0) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setDouble(4, price);
						stmt.setInt(5, beginRow);
						stmt.setInt(6, rowPerPage);
					} else {
						sql += " AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);
					}
				} else if(length == 1) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setDouble(4, price);
						stmt.setInt(5, beginRow);
						stmt.setInt(6, rowPerPage);
					} else {
						sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);
					}
				}
			} else if (price==-1 && length!=-1 && !rating.equals("")) {
				if(length == 0) {
					if(!category.equals("")) {
					sql += " AND category LIKE ? AND length<60 AND rating=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setString(4, rating);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
					} else {
						sql += " AND length<60 AND rating=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);
					}
				} else if(length == 1) {
					if(!category.equals("")) {
					sql += " AND category LIKE ? AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setString(4, rating);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
					} else {
						sql += " AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setInt(4, beginRow);
						stmt.setInt(5, rowPerPage);	
					}
				}
			} else if (price!=-1 && length==-1 && !rating.equals("")) {
				if(!category.equals("")) {
					sql += " AND category LIKE ? AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, "%"+category+"%");
					stmt.setString(4, rating);
					stmt.setDouble(5, price);
					stmt.setInt(6, beginRow);
					stmt.setInt(7, rowPerPage);
				} else {
					sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setString(3, rating);
					stmt.setDouble(4, price);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
				}
				
			} else if (price!=-1 && length!=-1 && !rating.equals("")) {
				if(length == 0) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length<60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
						stmt.setDouble(5, price);
						stmt.setInt(6, beginRow);
						stmt.setInt(7, rowPerPage);
					} else {
						sql += " AND length<60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setDouble(4, price);
						stmt.setInt(5, beginRow);
						stmt.setInt(6, rowPerPage);
					}
				} else if(length == 1) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND length>=60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
						stmt.setDouble(5, price);
						stmt.setInt(6, beginRow);
						stmt.setInt(7, rowPerPage);
					} else {
						sql += " AND length>=60 AND rating=? AND price=? ORDER BY fid LIMIT ?, ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setDouble(4, price);
						stmt.setInt(5, beginRow);
						stmt.setInt(6, rowPerPage);
					}
				}
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFid(rs.getInt("fid"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				list.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		}
	
	public List<Double> selectfilmPriceDistinctList() {
		// 자원준비
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = -1;
		// DB접속
		conn = DBUtil.getConnection();
		String sql ="SELECT DISTINCT price FROM film_list ORDER BY price";
		// SQL문실행
		try {
			stmt = conn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getDouble("price"));
			}
		} catch(Exception e) {
			e.printStackTrace(); // 예외된 애들 꼭 찝어달라
		}finally {
			try {	// 자원반납
				rs.close(); stmt.close(); conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// filmSearchAction 총합 구하기 및 라스트 페이지 설정
		public int FilmListSearchTotalRow(String category, String rating, double price, int length, String title, String actor) {
			int row = 0;
			Connection conn = null;
			PreparedStatement  stmt = null;
			ResultSet rs = null;
			conn = DBUtil.getConnection();
			String sql = "";
			try {
				// 동적쿼리
				sql = "SELECT count(*) cnt FROM film_list WHERE title LIKE ? AND actors LIKE ?";
				if(price==-1 && length==-1 && rating.equals("")) {
					if(!category.equals("")) {
						sql += " AND category LIKE ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%"); 
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
					} else {
						sql += "";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%"); 
						stmt.setString(2, "%"+actor+"%");
					}
				} else if(price==-1 && length!=-1 && rating.equals("")) {
					if(length == 0) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length<60";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
						} else {
							sql += " AND length<60";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
						}
					} else if(length == 1) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length>=60";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
						} else {
							sql += " AND length>=60";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
						}
					}
				} else if(rating.equals("") && price!=-1 && length==-1) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND price=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setDouble(4, price);
					} else {
						sql += " AND price=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setDouble(3, price);
				}
				} else if(!rating.equals("") && price==-1 && length==-1) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND rating=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
					} else {
						sql += " AND rating=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
					}
				} else if (price!=-1 && length!=-1 && rating.equals("")) {
					if(length == 0) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length<60 AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
							stmt.setDouble(4, price);
							
						} else {
							sql += " AND length<60 AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
						}
					} else if(length == 1) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length>=60 AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
							stmt.setDouble(4, price);
						} else {
							sql += " AND length>=60 AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setDouble(3, price);
						}
					}
				} else if (price==-1 && length!=-1 && !rating.equals("")) {
					if(length == 0) {
						if(!category.equals("")) {
						sql += " AND category LIKE ? AND length<60 AND rating=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
						} else {
							sql += " AND length<60 AND rating=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
						}
					} else if(length == 1) {
						if(!category.equals("")) {
						sql += " AND category LIKE ? AND length>=60 AND rating=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
						} else {
							sql += " AND length>=60 AND rating=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
						}
					}
				} else if (price!=-1 && length==-1 && !rating.equals("")) {
					if(!category.equals("")) {
						sql += " AND category LIKE ? AND rating=? AND price=?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, "%"+category+"%");
						stmt.setString(4, rating);
						stmt.setDouble(5, price);
					} else {
						sql += " AND rating=? AND price= ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+title+"%");
						stmt.setString(2, "%"+actor+"%");
						stmt.setString(3, rating);
						stmt.setDouble(4, price);
					}
					
				} else if (price!=-1 && length!=-1 && !rating.equals("")) {
					if(length == 0) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length<60 AND rating=? AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
							stmt.setString(4, rating);
							stmt.setDouble(5, price);
						} else {
							sql += " AND length<60 AND rating=? AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
							stmt.setDouble(4, price);
						}
					} else if(length == 1) {
						if(!category.equals("")) {
							sql += " AND category LIKE ? AND length>=60 AND rating=? AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, "%"+category+"%");
							stmt.setString(4, rating);
							stmt.setDouble(5, price);
						} else {
							sql += " AND length>=60 AND rating=? AND price=?";
							stmt = conn.prepareStatement(sql);
							stmt.setString(1, "%"+title+"%");
							stmt.setString(2, "%"+actor+"%");
							stmt.setString(3, rating);
							stmt.setDouble(4, price);
						}
					}
				}
				rs = stmt.executeQuery();
				while(rs.next()) {
				row = (rs.getInt("cnt"));
				}
			} catch(Exception e) {
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
	
	public Map<String, Object> filmInStockCall(int filmId, int storeId) {
		// 자원준비
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행 
		CallableStatement stmt = null;
		ResultSet rs = null;
		// select inventory_id .... 
		List<Integer> list = new ArrayList<>();
		// select count(inventroy_id) ....
		Integer count = 0;
		// 디비 접속
		conn = DBUtil.getConnection();
		// SQL문 실행
		try {
			stmt = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
			}
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map; // 값 리턴
	}
	
	// test
	public static void main(String[] args) {
		FilmDao fd = new FilmDao();
		int filmId = 7;
		int storeId = 2;
		Map<String, Object> map = fd.filmInStockCall(filmId, storeId);
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count");
		
		System.out.println(filmId + "번 영화는 "+ storeId +"번 가게에 "+count+"개 남음");
		for(int id : list) {
			System.out.println(id);
		}
	}
}
