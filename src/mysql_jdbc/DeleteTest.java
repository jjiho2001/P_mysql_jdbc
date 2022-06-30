package mysql_jdbc;

import java.util.Scanner;

public class DeleteTest extends DBConnection{

	Scanner scan = new Scanner(System.in);
	SelectTest st = new SelectTest();
	
	public DeleteTest() {
		
	}

	void start() {
		try {
			st.start();
			System.out.print("삭제 번호 : ");
			int mem_id = scan.nextInt();
			
			getConnection();
			
			sql = "delete from member where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_id);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("삭제 완료");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
	}
	
	public static void main(String[] args) {
		new DeleteTest().start();

	}

}
