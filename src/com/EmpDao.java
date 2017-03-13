package com;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDao {
	private Connection con = null;
	
public int insert(EmpDto e){
	System.out.println(e.getName()+ " ---" + e.getEmail() 
	+ " " + e.getPassword()) ;
	int i = 0 ;
	try{
		con = DbConnection.getConnection();
		PreparedStatement ps = 
	con.prepareStatement("insert into abc (name_ , email , pass)"
			+ " values(?,?,?)");
		ps.setString(1, e.getName());
		ps.setString(2, e.getEmail());
		ps.setString(3, e.getPassword());
		i = ps.executeUpdate();
		
		System.out.println(con);
	}catch (Exception e1) {
		System.out.println(e1);
	}
	return i ;
}
	

	public ArrayList<EmpDto> view() { 
		ArrayList<EmpDto> li = new ArrayList<EmpDto>();
		con = DbConnection.getConnection();
		String sql = "select * from abc ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EmpDto e = new EmpDto();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name_"));
				e.setEmail(rs.getString("email"));
				e.setPassword(rs.getString("pass"));
				li.add(e);

			}

			

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return li;
	}
	public EmpDto viewSpecificRecord(String id){
		con=DbConnection.getConnection();
		String query= "select * from abc where id=?";
		EmpDto emp=null;
		 id.replace("/"," ");
		 System.out.println(id);
		try{
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, id);
			ResultSet rs = pr.executeQuery();
			 emp = new EmpDto();
//			
		while(rs.next()){
			 emp.setName(rs.getString("name_"));
			emp.setEmail(rs.getString("email"));
			emp.setPassword(rs.getString("pass"));
			 emp.setId(rs.getInt("id"));
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return emp;
	}
	
	public int delete(String id){
		con=DbConnection.getConnection();
		String query="delete from abc where id=?";
		int i=0;
		try{
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, id);
			 i= ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public int update(String email,String name,String pass,String id){
		con=DbConnection.getConnection();
		String query="update abc set name_=?,email=?, pass=? where id=?";
		int i=0;
		System.out.println(email+name+pass+id);
		try{
			PreparedStatement pr= con.prepareStatement(query);
			pr.setString(1, name);
			pr.setString(2, email);
			pr.setString(3, pass);
			pr.setString(4, id);
			i= pr.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	 
//	public static void main(String[] args) {
//		EmpDto et = new EmpDto("aab", "bbcc", "password");
//		EmpDao e = new EmpDao();
//		e.insert(et);
//	}
}
