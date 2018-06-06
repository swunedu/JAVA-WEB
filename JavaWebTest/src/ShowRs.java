import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowRs extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf8");
		PrintWriter out = resp.getWriter();
		
		out.println("<table border=1>");
		out.println("<tr><td>Content:</td></tr>");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydata?user=root&password=150604tt&serverTimezone=GMT%2B8&useSSL=false");//&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from article");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getString("title")+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					//Object clone = stmt.clone();
					//stmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
	}

}
