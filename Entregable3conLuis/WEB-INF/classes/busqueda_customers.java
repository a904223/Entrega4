import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class busqueda_customers extends HttpServlet {
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

   public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String htmlString = request.getParameter("htmlString");
            
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
			
			String sql = "SELECT CustomerName FROM Customers WHERE CustomerName LIKE ?";
			String sql2 = "SELECT Count(CustomerName) AS cuenta FROM Customers WHERE CustomerName LIKE ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps.setString(1, "%" + htmlString + "%");
            ps2.setString(1, "%" + htmlString + "%");
            
			ResultSet result = ps.executeQuery();
            ResultSet result2 = ps2.executeQuery();
            
            result2.next();
            String strCont = result2.getString("cuenta");
            int Cont = Integer.parseInt(strCont);
            
            int i = 0;
            if (Cont !=0){
                String SqlCustomer = "";
                out.println("<table border='1' id='t1' style=\"cursor: pointer;\">");
                while (result.next()){
                    SqlCustomer = result.getString("CustomerName");
                    i++;
                    out.println("<tr><td>" + SqlCustomer + "</td></tr>");
                }
                out.println("</table>");
            }
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
		}
}