package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import model.ExampleDTO;
import model.LankingDTO;

public class GameDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "scott";
	String passwd = "tiger";

	public GameDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 글 조회
	public ExampleDTO selectExample(String lan) {
		ExampleDTO result = new ExampleDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM (SELECT * FROM EXAMPLE WHERE lan = ? ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM = 1";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, lan);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result.setIdx(rs.getInt("idx"));
				result.setLan(rs.getString("lan"));
				result.setContent(rs.getString("content"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 해당 글 기록 조회
	public Map<String, LankingDTO> selectLanking(Integer writeidx) {
		Map<String, LankingDTO> result = new LinkedHashMap<String, LankingDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM LANKING WHERE writeidx = ? ORDER BY duration ASC";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, writeidx);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				LankingDTO dto = new LankingDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setWriteidx(rs.getInt("writeidx"));
				dto.setNickname(rs.getString("nickname"));
				dto.setDuration(rs.getLong("duration"));
				result.put(dto.getNickname(), dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 기록 추가
	public Map<String, LankingDTO> insertLanking(Integer writeidx, String nickname, Long duration) {
		Map<String, LankingDTO> result = new LinkedHashMap<String, LankingDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO LANKING(idx,writeidx,nickname,duration) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, getMaxIdx());
			pstmt.setInt(2, writeidx);
			pstmt.setString(3, nickname);
			pstmt.setLong(4, duration);
			
			// 성공 시 다시 조회
			if(pstmt.executeUpdate() > 0) {
				result = selectLanking(writeidx);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 기록 업데이트
	public Map<String, LankingDTO> updateLanking(Integer idx, Integer writeidx, Long duration) {
		Map<String, LankingDTO> result = new LinkedHashMap<String, LankingDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "UPDATE LANKING SET duration = ? WHERE idx = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, duration);
			pstmt.setInt(2, idx);
			
			// 성공 시 다시 조회
			if(pstmt.executeUpdate() > 0) {
				result = selectLanking(writeidx);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
	// idx 생성
	private int getMaxIdx() {
		int maxIdx = 1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM (SELECT idx FROM LANKING ORDER BY idx DESC) WHERE ROWNUM = 1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxIdx = rs.getInt("idx") + 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxIdx;
	}
}
