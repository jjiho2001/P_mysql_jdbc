package mysql_jdbc;

public class UpdateTest extends DBConnection{

	public UpdateTest() {
		
	}

	public void updateStart() {
		try {
			// 1. Driver loading �� �Ǿ�����
			// 2. DB ����
			getConnection();
			
			// 3. PreparedStatement
			sql = "update member set depart = ?, phone = ?, email = ? where mem_id = ?";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, "������");
			pstmt.setString(2, "010-1234-1234");
			pstmt.setString(3, "abcd@google.com");
			pstmt.setInt(4, 2222);
			
			// 4. ����
			int cnt = pstmt.executeUpdate(); // insert, update, delete
			if(cnt > 0) {
				System.out.println("ȸ������ ���� �Ϸ�");
			} else {
				System.out.println("ȸ������ ���� ����");
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
