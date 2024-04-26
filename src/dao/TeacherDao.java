//package dao;

//import bean.Maemura
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;

//public class TeacherDAO extends Dao {

	//public Teacher search(String login,String password)
		//throws Exception {
        //Teacher teacher=null;

		//Connection con=getConnection();

		//PreparedStatement st;
		//st=con.prepareStatement(
			//"select * from teacher where login=? and password=?");
		//st.setString(1, login);
		//st.setString(2, password);
		//ResultSet rs=st.executeQuery();

		//while (rs.next()) {
			//teacher=new Teacher();
			//teacher.setId(rs.getInt("id"));
			//teacher.setPassword(rs.getInt("password"));
			//teacher.setName(rs.getString("name"));
			//teacher.setSchool_cd(rs.getInt("school_cd"));
		//}

		//st.close();
		//con.close();
        //return teacher;
	//}
//}