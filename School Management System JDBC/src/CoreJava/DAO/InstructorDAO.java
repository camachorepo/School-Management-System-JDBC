package CoreJava.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CoreJava.Models.Instructor;
import CoreJava.Models.Student;
import CoreJava.systemsInterfaces.InstructorDAOI;
import CoreJava.utils.OracleQueries;

public class InstructorDAO implements InstructorDAOI {


	public List<Instructor> getAllInstructors() throws SQLException {
		List<Instructor> instructors = new ArrayList<Instructor>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETALLINSTRUCTORS);
			result = stmt.executeQuery();
			
			while(result != null && result.next() ) {
				
				Instructor current = new Instructor();
				
				current.setInstructor_id(result.getInt(1));
				current.setFull_name(result.getString(2));
				current.setEmail(result.getString(3));
				current.setSpeciality(result.getString(4));
				current.setAdmin_role(result.getInt(5));
				current.setPass(result.getString(6));

				
				
				instructors.add(current);
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
	
		return instructors;
	}


	public Instructor getInstructorByGmail(String email) throws SQLException {
		Instructor instructor = new Instructor();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETINSTRUCTORBYGMAIL);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			
			if(result != null && result.next() ) {	
				
				instructor.setInstructor_id(result.getInt(1));
				instructor.setFull_name(result.getString(2));
				instructor.setEmail(result.getString(3));
				instructor.setSpeciality(result.getString(4));
				instructor.setAdmin_role(result.getInt(5));
				instructor.setPass(result.getString(6));
				
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
	
		
		return instructor;
	}

	public String validateUser(Instructor i, String comparablePas) {
		
		String role = " ";
		
		if(i.getPass().equals(comparablePas)) {
			
			if(i.getAdmin_role() == 1)
				role = "Admin";
			else 
				role = "Instructor";
		}
		else 
			role = "Wrong Credentials";
		return role;
	}
	
	
	
	
}
