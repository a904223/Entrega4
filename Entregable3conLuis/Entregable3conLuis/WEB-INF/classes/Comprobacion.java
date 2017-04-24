import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Comprobacion extends HttpServlet {
	Connection connection;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            e.printStackTrace();
        }
		System.out.println("Starting Comprobacion...");
	}
	
	public void destroy() {
		super.destroy();
        System.out.print("Closing connection ...");
        try {
            connection.close();   
			System.out.println("Connection closed");
        } catch(SQLException ex){
            System.out.println("Problem closing connection");
            System.out.println(ex.getMessage());
        }
    }

    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String correo = req.getParameter("correo");
        String answer = req.getParameter("question");
	 

		
		resp.setContentType("text/html");
        PrintWriter toClient = resp.getWriter();

			
        
        Statement stmt = null;
        try {
            stmt=connection.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT Mail, Answer FROM Users WHERE Mail = '"
               + correo + "' AND Answer = '" + answer + "'");

            if(rs.next() == false){
                resp.sendRedirect("error.html"); 
                return;
            } else {
				double x = Math.floor(Math.random()*9999);
				String sql = "UPDATE Users SET Password ="+ x +" WHERE Mail ='" + correo +"'";
				
				try{
					Statement statement = connection.createStatement();
					statement.executeUpdate(sql);
					statement.close();

         
				} catch(SQLException e) {
					e.printStackTrace();
					System.out.println("Error: " + e);
				}
		toClient.println("<!DOCTYPE HTML>");
		toClient.println("<HTML>");
		toClient.println("<HEAD>");
		toClient.println("<title>Recover Password</title>");
		toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
		toClient.println("</head>");
		toClient.println("<BODY >");
		toClient.println("<div class=\"header\">");
		toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
		toClient.println("<h1 align=\"center\">Recover Password</h1>");
		toClient.println("</div>");
		toClient.println("<meta align=\"right\" http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		toClient.println("<TR>");
		toClient.println("<form method=\"get\" action=\"cambio\">");
		toClient.println("<center>");
		toClient.println("<table>");
		toClient.println("<tr>");
		toClient.println("<td align=\"center\"><input type=\"hidden\" name=\"correo\"  value=\"" + correo + "\" />");
		toClient.println("Are you sure your answer is <B>"+answer+"</B>?");
		toClient.println("</tr>");
		toClient.println("</form>");
		toClient.println("<tr>");
		toClient.println("<td align=\"right\">");
		toClient.println("<input type=\"submit\" value=\"Yes\">");
		toClient.println("</td>");
		toClient.println("<td align=\"left\">");
		toClient.println("<form method=\"get\" action=\"Login.html\">");
		toClient.println("<input type=\"submit\" value=\"No\">");
		toClient.println("</td>");
		toClient.println("</tr>");
		toClient.println("</table>");
		toClient.println("</center>");
		toClient.println("</div>");
		toClient.println("</li>");
		toClient.println("</ul>");
		toClient.println("</BODY>");
		toClient.println("</HTML>");
		toClient.flush();
		toClient.close();		
		 
                return;
            }
			
        } catch (SQLException sql) {
            System.out.println("Error creating Statement");
            System.out.println(sql.getMessage());
            return;
        } finally {      
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch(SQLException e) {
                    System.out.println("Error closing Statement");
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } 
    } 

  
}

