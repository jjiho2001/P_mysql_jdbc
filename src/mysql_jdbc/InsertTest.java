package mysql_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest {

	Connection conn = null;
	Scanner scan = new Scanner(System.in);
	PreparedStatement pstmt = null;
	
	void start() {
		// connector build path
		try {
			// 1. Driver 로딩
			// Class 클래스
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DB 연결
			String url = "jdbc:mysql://@127.0.0.1:3306/multi";
			conn = DriverManager.getConnection(url,"root","root1234");
			
			// 데이터 준비
			System.out.print("회원 번호 : ");
			int mem_id = Integer.parseInt(scan.nextLine());
			System.out.print("회원 이름 : ");
			String username = scan.nextLine();
			System.out.print("부서명 : ");
			String depart = scan.nextLine();
			System.out.print("연락처 : ");
			String phone = scan.nextLine();
			System.out.print("email : ");
			String email = scan.nextLine();
			
			// 3. PreparedStatement 객체를 생성 (sql query)
			String sql = "insert into member(mem_id, username, depart, phone, email) "
						+ "values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mem_id);
			pstmt.setString(2, username);
			pstmt.setString(3, depart);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			
			// 4. 실행
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("record 추가");
			} else {
				System.out.println("record 추가 실패");
			}
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. DB 연결 해제
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new InsertTest().start();
	}

}
