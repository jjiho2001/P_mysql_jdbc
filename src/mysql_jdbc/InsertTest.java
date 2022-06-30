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
			// 1. Driver �ε�
			// Class Ŭ����
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. DB ����
			String url = "jdbc:mysql://@127.0.0.1:3306/multi";
			conn = DriverManager.getConnection(url,"root","root1234");
			
			// ������ �غ�
			System.out.print("ȸ�� ��ȣ : ");
			int mem_id = Integer.parseInt(scan.nextLine());
			System.out.print("ȸ�� �̸� : ");
			String username = scan.nextLine();
			System.out.print("�μ��� : ");
			String depart = scan.nextLine();
			System.out.print("����ó : ");
			String phone = scan.nextLine();
			System.out.print("email : ");
			String email = scan.nextLine();
			
			// 3. PreparedStatement ��ü�� ���� (sql query)
			String sql = "insert into member(mem_id, username, depart, phone, email) "
						+ "values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mem_id);
			pstmt.setString(2, username);
			pstmt.setString(3, depart);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			
			// 4. ����
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("record �߰�");
			} else {
				System.out.println("record �߰� ����");
			}
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. DB ���� ����
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
