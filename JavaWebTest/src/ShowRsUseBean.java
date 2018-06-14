import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowRsUseBean extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf8");
		PrintWriter out = resp.getWriter();
		
		out.println("<table border=1>");
		out.println("<tr><td>Content:</td></tr>");
		
		Connection conn = DB.getConn();
		java.sql.Statement stmt = DB.getStatement(conn);
		String sql = "select * from article";
		ResultSet rs = DB.getResultSet(stmt, sql);
		try {
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getString("title")+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(rs);
		}
	}

}
