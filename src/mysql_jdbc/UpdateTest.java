package mysql_jdbc;

public class UpdateTest extends DBConnection{

	public UpdateTest() {
		
	}

	public void updateStart() {
		try {
			// 1. Driver loading 은 되어있음
			// 2. DB 연결
			getConnection();
			
			// 3. PreparedStatement
			sql = "update member set depart = ?, phone = ?, email = ? where mem_id = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, "관리부");
			pstmt.setString(2, "010-1234-1234");
			pstmt.setString(3, "abcd@google.com");
			pstmt.setInt(4, 2222);
			
			// 4. 실행
			int cnt = pstmt.executeUpdate(); // insert, update, delete
			if(cnt > 0) {
				System.out.println("회원정보 수정 완료");
			} else {
				System.out.println("회원정보 수정 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose();
		}
	}
	
	public static void main(String[] args) {
		new UpdateTest().updateStart();

	}

}
