package kr.co.saramin.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.saramin.emaillist.vo.EmailListVo;

public class EmailListDao {
	
	
	public void  insert(EmailListVo vo){
		
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		
		ResultSet rs=null;
		
		
		try {
			
			 // 1. driver loading
			Class.forName("com.mysql.jdbc.Driver");
			String url= "jdbc:mysql://localhost/webdb";
		
			// 2. connection 
			 conn=  DriverManager.getConnection(url , "webdb", "webdb");
			 
			// 3.  sql
			
			String sql= " insert into emaillist values( null, ? , ? , ? ) ";
			
			// 3. create statement
			 pstmt = conn.prepareStatement(sql);
			
			 
			 // 4. binding
			 pstmt.setString(1, vo.getFirstName());
			 pstmt.setString(2, vo.getLastName());
			 pstmt.setString(3, vo.getEmail());
			

			// 5. execute
			 pstmt.executeUpdate();
			
			
			 
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Driver loading fail");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("DB connection fail=>"+e.getMessage()  );
					
			
		}finally {
			
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
  
		
	}
	
	
	public List getList(){
		
		List<EmailListVo> list = new ArrayList<EmailListVo>();
		//List list = new ArrayList();
		
		Connection conn= null;
		Statement stmt = null;
		
		ResultSet rs=null;
		
		
		try {
			
			 // 1. driver loading
			Class.forName("com.mysql.jdbc.Driver");
			String url= "jdbc:mysql://localhost/webdb";
		
			// 2. connection 
			 conn=  DriverManager.getConnection(url , "webdb", "webdb");
			
			// 3. create statement
			 stmt = conn.createStatement();
			
			// 4. excute sql
			
			String sql= " select * from emaillist";
			
			 rs= stmt.executeQuery(sql);
			
			// 5. restore result 
			
			
			
			while(rs.next()){
				
				Long no = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName= rs.getString(3);
				String email = rs.getString(4);
				
				EmailListVo vo = new EmailListVo();
				
				HashMap map= new HashMap();

				map.put("no", no);
				map.put("firstName", firstName);
				map.put("lastName", lastName);
				map.put("email", email);
				
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				System.out.println(vo.toString());
				
				list.add(vo);
				
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Driver loading fail");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("DB connection fail=>"+e.getMessage()  );
					
			
		}finally {
			
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return list;
		
	}
	
	
	
	

}
