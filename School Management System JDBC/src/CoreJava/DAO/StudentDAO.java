package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.StudentDAOI;
import CoreJava.utils.OracleQueries;

public class StudentDAO implements StudentDAOI{

	public Student getStudentByGmail(String email) throws SQLException {
		Student student = new Student();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETSTUDENTBYGMAIL);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			
			if(result != null && result.next() ) {	
				
				student.setStudent_id(result.getInt(1));
				student.setFull_name(result.getString(2));
				student.setEmail(result.getString(3));
				student.setGpa(result.getDouble(4));
				student.setPass(result.getString(5));
				student.setStudent_role(result.getInt(6));
				
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			result.close();
			
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	
		

		
		
		return student;
	}

	
	public boolean validateUser(String passToValidate, String comparablePas) {
		
		boolean same = false;
		
		if(passToValidate.equals(comparablePas)) {
			same = true;
		}
		return same;
	}

	
}
