import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class JDBCBestseller extends HttpServlet {
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
    }

   public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=res.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
		String sql = "";
		sql="SELECT ProductName, count(*) AS NumbOfOrders FROM Orders WHERE OrderDate BETWEEN #02/03/2017# AND #03/03/2017# GROUP BY ProductName HAVING COUNT(*)=(SELECT MAX(NumbOfOrders) FROM (SELECT ProductName, count(*) AS NumbOfOrders FROM Orders WHERE OrderDate BETWEEN #02/03/2017# AND #03/03/2017#GROUP BY ProductName))";
        System.out.println("sql: " +sql);
		try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString = "";
			String resString1 = "";
            boolean first = true;
            while(result.next()) {
                if (!first) {
                    resString += ",";
					resString1 += ",";
                } else {
                    first = false;
                }
                resString += result.getString("ProductName");
				resString1 += result.getString("NumbOfOrders");
            }
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Bestseller</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='right'>");
			out.println("<H2> This is the bestseller of the company in the last month </H2>");
			out.println("<table border=1>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("Product name");
			out.println("</TD>");
			out.println("<TD>");
			out.println(resString);
			out.println("</TD>");
			out.println("</TR>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("Number of orders");
			out.println("</TD>");
			out.println("<TD>");
			out.println(resString1);
			out.println("</TD>");
			out.println("</TR>");
			out.println("</table>");
			out.println("</body>");
			out.println("</div>");
			out.println("</html>");
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset: " + sql + " Exception: " + e);
		}  
			out.close();
		}
}