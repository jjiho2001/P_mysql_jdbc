package mysql_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectTest {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public SelectTest() {
		
	}

	void start() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://@localhost:3306/multi";
			conn = DriverManager.getConnection(url, "root", "root1234");
			
			String sql = "select mem_id, username, depart, phone, date_format(writedate, '%Y-%m-%d') writedate, email "
					+ "from member order by username";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int mem_id = rs.getInt(1); // rs.getInt("mem_id")
				String username = rs.getString(2);
				String depart = rs.getString(3);
				String phone = rs.getString(4);
				String email = rs.getString(6);
				String writedate = rs.getString(5);
				System.out.printf("%d%10s%10s%15s%20s%30s\n", mem_id, username, depart, phone, email, writedate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	public static void main(String[] args) {
		new SelectTest().start();

	}
*/
}
