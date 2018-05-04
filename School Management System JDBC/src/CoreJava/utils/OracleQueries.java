package CoreJava.utils;

public class OracleQueries {

	
	public static final String GETSTUDENTBYGMAIL = "SELECT * FROM STUDENT "
			+ "WHERE EMAIL =  ? ";
	public static final String GETINSTRUCTORBYGMAIL = "SELECT * FROM INSTRUCTOR "
			+ "WHERE EMAIL =  ? ";
	
	public static final String GETALLINSTRUCTORS = "SELECT * FROM INSTRUCTOR ";
	
	public static final String GETALLCOURSES = "SELECT * FROM COURSE ";
	
	public static final String GETALLCOURSESBYINSTRUCTOR = "SELECT * " + 
			"FROM COURSE c JOIN  TEACHING t ON c.course_id = t.course_id " + 
			"WHERE t.INSTRUCTOR_ID = ?";
	
	public static final String GETALLATTENDINGBYSTUDENT ="SELECT C.COURSE_NAME, I.full_name, I.email " + 
			"FROM ATTENDING A JOIN COURSE C ON A.COURSE_ID = C.COURSE_ID JOIN TEACHING T " + 
			"ON C.COURSE_ID = T.COURSE_ID JOIN INSTRUCTOR I ON T.INSTRUCTOR_ID = I.INSTRUCTOR_ID " + 
			"WHERE A.STUDENT_ID = ? ";
	
	public static final String REGISTERSTUDENTTOCOURSE = "INSERT INTO ATTENDING "
			+ "(COURSE_ID, STUDENT_ID) "
			+ "VALUES(?, ?) ";
	
	public static final String ASSIGNINSTRUCTORTOCOURSE = "INSERT INTO TEACHING "
			+ "(COURSE_ID, INSTRUCTOR_ID) "
			+ "VALUES(?, ?) ";
	public static final String GETINSTRUCTORCOURSES ="SELECT C.COURSE_NAME, C.MINIMUN_GPA, I.FULL_NAME, I.EMAIL " + 
			"FROM COURSE C JOIN TEACHING T ON C.COURSE_ID = T.COURSE_ID JOIN INSTRUCTOR I " + 
			"ON T.INSTRUCTOR_ID = I.INSTRUCTOR_ID  ";
}
